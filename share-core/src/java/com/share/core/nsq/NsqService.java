package com.share.core.nsq;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.share.core.annotation.NsqCallback;
import com.share.core.exception.ParametersIncorrectException;
import com.share.core.exception.UnimplementsException;
import com.share.core.interfaces.NsqMessageHandler;
import com.share.core.util.FileSystem;
import com.share.core.util.Ip;
import com.share.core.util.SpringUtil;
import com.share.core.util.SystemUtil;
import com.trendrr.nsq.NSQConsumer;
import com.trendrr.nsq.NSQLookup;
import com.trendrr.nsq.NSQMessage;
import com.trendrr.nsq.NSQMessageCallback;
import com.trendrr.nsq.NSQProducer;
import com.trendrr.nsq.lookup.NSQLookupDynMapImpl;

public final class NsqService {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * nsq生产者
	 */
	private NSQProducer producer;
	/**
	* 要扫描的包名
	*/
	private String packageName;
	/**
	 * 线程池大小
	 */
	private int poolSize = Runtime.getRuntime().availableProcessors() * 2;

	@Value("${nsq.nsqd}")
	private String nsqd;
	@Value("${nsq.nsqlookupd}")
	private String nsqlookupd;

	/**
	 * 构造函数
	 */
	private NsqService() {
	}

	/**
	 * 设置要扫描的包名
	 * @author ruan
	 * @param packageName 包名
	 */
	public void setScanPackage(String packageName) {
		if (!packageName.matches("^[a-zA-Z\\.,]+$")) {
			throw new ParametersIncorrectException("scan package define incorrect! you can only input 26 case-insensitive and ',' and '.'");
		}
		this.packageName = packageName;
	}

	/**
	 * 设置线程池大小
	 * @param poolSize
	 */
	public void setPoolSize(int poolSize) {
		if (poolSize <= 0) {
			return;
		}
		this.poolSize = poolSize;
	}

	/**
	 * 初始化
	 * @author ruan
	 */
	public void init() {
		producer = new NSQProducer();
		for (String server : nsqd.trim().split(",")) {
			producer.addAddress(server.trim(), 4150, /*解决win系统osx系统连接nsq慢的问题*/(FileSystem.isWindows() || FileSystem.isMacOSX()) ? 1 : poolSize);
		}
		producer.start();

		NSQLookup lookup = new NSQLookupDynMapImpl();
		for (String server : nsqlookupd.trim().split(",")) {
			lookup.addAddr(server.trim(), 4161);
		}

		// 如果没定义NsqCallback，就直接return
		if (packageName == null || packageName.isEmpty()) {
			return;
		}

		try {
			for (String pkgName : packageName.split(",")) {
				for (Class<?> clazz : SystemUtil.getClasses(pkgName)) {
					Class<?>[] interfaces = clazz.getInterfaces();
					if (interfaces.length != 1) {
						throw new UnimplementsException(clazz + " have no implements any interface, must implements " + NsqMessageHandler.class);
					}
					if (!interfaces[0].equals(NsqMessageHandler.class)) {
						throw new UnimplementsException(clazz + " must implements " + NsqMessageHandler.class);
					}
					for (Method method : clazz.getDeclaredMethods()) {
						NsqCallback nsqCallback = method.getAnnotation(NsqCallback.class);
						if (nsqCallback == null) {
							continue;
						}

						String channel = nsqCallback.channel();
						if (!nsqCallback.onlyChannel()) {
							// 这样可以保证在同一台机器上不同进程都可以每台都收到
							String project = FileSystem.getProjectName();
							project = project.substring(project.indexOf("-") + 1);
							channel += "_" + project + "_" + Ip.getValidIPAddress("") + "_" + FileSystem.getPropertyInt("http." + project + ".port");
						}

						// 获取bean
						new NSQConsumer(lookup, nsqCallback.topic(), channel, new DefaultNSQMessageCallback((NsqMessageHandler) SpringUtil.getBean(clazz))).start();
						logger.warn("find nsq callback, bind channel: {}, method: {}", channel, method);
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	/**
	 * 发送一条消息
	 * @param topic
	 * @param message
	 */
	public void produce(String topic, byte[] message) {
		try {
			producer.produce(topic, message);
			logger.warn("produce {}  {}", topic, message);
		} catch (Exception e) {
			logger.error(topic + "," + message, e);
		}
	}

	/**
	 * 批量发送消息
	 * @param topic
	 * @param message
	 */
	public void produceBatch(String topic, byte[] message) {
		try {
			producer.produceBatch(topic, message);
			logger.warn("produceBatch {}  {}", topic, message);
		} catch (Exception e) {
			logger.error(topic + "," + message, e);
		}
	}

	/**
	 * 发送多条消息
	 * @param topic
	 * @param message
	 */
	public void produceMulti(String topic, List<byte[]> message) {
		try {
			producer.produceMulti(topic, message);
			logger.warn("produceMulti {}  {}", topic, message);
		} catch (Exception e) {
			logger.error(topic + "," + message, e);
		}
	}

	private final class DefaultNSQMessageCallback implements NSQMessageCallback {
		private NsqMessageHandler nsqMessageHandler;

		private DefaultNSQMessageCallback(NsqMessageHandler nsqMessageHandler) {
			this.nsqMessageHandler = nsqMessageHandler;
		}

		public void message(NSQMessage message) {
			boolean b = false;
			try {
				b = nsqMessageHandler.handle(message.getMessage());
			} catch (Exception e) {
				b = false;
				logger.error("", e);
			}
			if (b) {
				message.finished();
			}
		}

		public void error(Exception paramException) {
			throw new RuntimeException(paramException);
		}
	}
}