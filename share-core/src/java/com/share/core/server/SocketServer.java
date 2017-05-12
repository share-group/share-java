package com.share.core.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.share.core.exception.IllegalPortException;
import com.share.core.interfaces.AbstractServer;
import com.share.core.interfaces.AbstractSocketServerInitializer;
import com.share.core.util.Check;
import com.share.core.util.FileSystem;

/**
 * netty socket 服务器
 * @author ruan
 */
public final class SocketServer extends AbstractServer {
	/**
	 * netty serverBootstrap 对象
	 */
	private ServerBootstrap serverBootstrap;

	/**
	 * 构造函数
	 * @param port 端口
	 * @param initializer 实现频道初始化这个接口
	 */
	public <SEND, RECV> SocketServer(int port, AbstractSocketServerInitializer<SEND, RECV> initializer) {
		if (!Check.isPort(port)) {
			throw new IllegalPortException("Illegal port: " + port);
		}
		serverBootstrap = new ServerBootstrap();

		// boss线程和worker线程不需要太多
		serverBootstrap.group(new NioEventLoopGroup(4), new NioEventLoopGroup(4));
		serverBootstrap.channel(NioServerSocketChannel.class);
		serverBootstrap.localAddress(port);
		serverBootstrap.option(ChannelOption.SO_BACKLOG, 100);
		serverBootstrap.option(ChannelOption.TCP_NODELAY, true);
		serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		serverBootstrap.childHandler(initializer);
		logger.info("socket server bind port {}", port);
		logger.info(serverBootstrap.toString());
	}

	/**
	 * 启动服务器
	 * @author ruan
	 */
	public void start() {
		try {
			serverBootstrap.bind().sync();
			FileSystem.loadSpringConfig();
		} catch (InterruptedException e) {
			logger.error("", e);
			System.exit(0);
		} finally {
			logger.info("socket server started...");
		}
	}
}