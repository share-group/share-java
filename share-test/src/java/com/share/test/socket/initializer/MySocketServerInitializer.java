package com.share.test.socket.initializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.share.core.annotation.processor.SocketHandlerProcessor;
import com.share.core.interfaces.AbstractSocketServerInitializer;
import com.share.core.interfaces.AbstractTask;
import com.share.core.protocol.protocol.ProtocolRequest;
import com.share.core.protocol.protocol.ProtocolResponse;
import com.share.core.util.SerialUtil;
import com.share.test.protocol.ReqDemo;
import com.share.test.socket.handler.DemoHandler;

public final class MySocketServerInitializer extends AbstractSocketServerInitializer<ProtocolResponse, ProtocolRequest> {
	/**
	 * 线程池
	 */
	private ExecutorService handlerExecutor = null;
	/**
	 * socket注解解析器
	 */
	private SocketHandlerProcessor socketHandlerProcessor = new SocketHandlerProcessor();

	/**
	 * 构造函数
	 * @param threadsNum 线程数	
	 */
	public MySocketServerInitializer(int threadsNum) {
		handlerExecutor = Executors.newFixedThreadPool(threadsNum);
		logger.info("init " + threadsNum + " threads...");
	}

	protected void recvMessage(ChannelHandlerContext ctx, ProtocolRequest request) {
		long interval = socketHandlerProcessor.getInterval(request.getProtocol());
		Future<?> future = null;
		try {
			future = handlerExecutor.submit(new Task(request, ctx));
			if (interval > 0) {
				future.get(interval, TimeUnit.MILLISECONDS);
			}
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			future.cancel(true);
			logger.error("", e);
		}
	}

	public void catchException(ChannelHandlerContext ctx, Throwable e) {
		logger.error("", e);
	}

	public void connectSuccess(ChannelHandlerContext ctx) {
		if (logger.isInfoEnabled()) {
			logger.info("proxy " + ctx.channel().hashCode() + " in.");
		}
	}

	public void disconnected(ChannelHandlerContext ctx) {
		if (logger.isInfoEnabled()) {
			logger.info("proxy " + ctx.channel().hashCode() + " out.");
		}
		ctx.disconnect();
	}

	protected void encodeData(ChannelHandlerContext ctx, ProtocolResponse response, ByteBuf buffer) throws Exception {
		byte[] bytes = SerialUtil.toBytes(response);
		buffer.writeInt(bytes.length);
		buffer.writeBytes(bytes);
	}

	protected void decodeData(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.readBytes(bytes);
		ReqDemo req = SerialUtil.fromBytes(bytes, ReqDemo.class);
		out.add(req);
	}

	/**
	 * 任务
	 * @author ruan
	 */
	private class Task extends AbstractTask {
		private ProtocolRequest request;
		private ChannelHandlerContext ctx;

		/**
		 * 构造函数
		 * @param request 协议号
		 * @param ctx ctx
		 */
		public Task(ProtocolRequest request, ChannelHandlerContext ctx) {
			this.request = request;
			this.ctx = ctx;
		}

		public void run() {
			DemoHandler demoHandler = new DemoHandler();
			demoHandler.demo1(null);
		}
	}
}