package com.share.soa.thrift.server;

import java.lang.reflect.Constructor;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;

import com.share.core.exception.IllegalPortException;
import com.share.core.interfaces.AbstractServer;
import com.share.core.util.Check;
import com.share.core.util.FileSystem;
import com.share.core.util.SystemUtil;

/**
 * thrift socket 服务器
 */
public class ThriftSocketServer extends AbstractServer {
	private TServer server;
	private int port;

	/**
	 * 构造函数
	 * @param port 端口
	 * @param serviceClass 接口类
	 * @param tProtocolClass 通讯协议类	
	 * @param handlerClass 处理器类
	 */
	public ThriftSocketServer(int port, Class<?> serviceClass, Class<? extends TProtocol> tProtocolClass, Class<?> handlerClass) {
		if (!Check.isPort(port)) {
			throw new IllegalPortException("Illegal port: " + port);
		}
		this.port = port;

		try {
			// 设置传输通道，普通通道  
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);

			// 初始化通讯协议
			TProtocolFactory protocolFactory = (TProtocolFactory) Class.forName(tProtocolClass.getName() + "$Factory").newInstance();

			// 初始化Iface
			Class<?> IfaceClazz = Class.forName(serviceClass.getName() + "$Iface");

			// 初始化处理器
			Constructor<?> constructor = Class.forName(serviceClass.getName() + "$Processor").getConstructor(new Class<?>[] { IfaceClazz });
			TProcessor tProcessor = (TProcessor) constructor.newInstance(handlerClass.newInstance());

			// 构造连接参数
			Args args = new Args(serverTransport);
			args.transportFactory(new TFramedTransport.Factory());
			args.protocolFactory(protocolFactory);
			args.processor(tProcessor);

			// 设置selector线程和worker线程
			int selectorThreads = FileSystem.getPropertyInt("thrift.socket.selectorThreads");
			int workerThreads = FileSystem.getPropertyInt("thrift.socket.workerThreads");
			
			// 如果没有设置，就用cpu核心数x2
			if (selectorThreads <= 0) {
				selectorThreads = SystemUtil.getCore();
			}
			if (workerThreads <= 0) {
				workerThreads = SystemUtil.getCore();
			}

			args.selectorThreads(selectorThreads);
			args.workerThreads(workerThreads);
			logger.info("selectorThreads: {}, workerThreads: {}", selectorThreads, workerThreads);

			// 创建服务器 
			server = new TThreadedSelectorServer(args);
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	/**
	 * 启动服务器
	 * @author ruan
	 */
	public void start() {
		logger.info("start thrift socket server on port {} ...", port);
		server.serve();
	}
}