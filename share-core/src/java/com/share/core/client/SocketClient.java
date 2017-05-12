package com.share.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.core.exception.IllegalPortException;
import com.share.core.interfaces.AbstractSocketServerInitializer;
import com.share.core.util.Check;

/**
 * socket客户端
 * @author ruan
 */
public final class SocketClient {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * netty bootstrap 对象
	 */
	private Bootstrap bootstrap;
	/**
	 * 地址
	 */
	private String host;
	/**
	 * 端口
	 */
	private int port;

	/**
	 * 构造函数
	 * @param <SEND>
	 * @param <RECV>
	 * @param host 地址
	 * @param port 端口
	 * @param threadsNum 处理器线程数
	 * @param initializer 管道初始化
	 */
	public <SEND, RECV> SocketClient(String host, int port, int threadsNum, AbstractSocketServerInitializer<SEND, RECV> initializer) {
		if (host == null) {
			throw new NullPointerException();
		}

		if (!Check.isPort(port)) {
			throw new IllegalPortException("Illegal port: " + port);
		}

		this.host = host;
		this.port = port;

		bootstrap = new Bootstrap();
		bootstrap.group(new NioEventLoopGroup(threadsNum));
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.handler(initializer);

		//logger.info("netty bufferMaxLength: " + FileSystem.getSize(ShareConfig.getInt("socket.bufferMaxLength")));
		logger.info(bootstrap.toString());
	}

	/**
	 * 连接
	 */
	public void connect() {
		try {
			bootstrap.connect(host, port).sync();
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}
}