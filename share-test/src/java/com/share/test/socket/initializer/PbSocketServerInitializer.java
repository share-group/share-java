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

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.share.core.interfaces.AbstractSocketServerInitializer;
import com.share.core.interfaces.AbstractTask;
import com.share.core.protocol.protobuf.demo.Demo.ReqDemo;
import com.share.core.util.Time;
import com.share.test.socket.handler.DemoHandler;

public final class PbSocketServerInitializer extends AbstractSocketServerInitializer<GeneratedMessageLite, GeneratedMessageLite> {
	/**
	 * 线程池
	 */
	private ExecutorService handlerExecutor = null;

	/**
	 * 构造函数
	 * @param threadsNum 线程数	
	 */
	public PbSocketServerInitializer(int threadsNum) {
		handlerExecutor = Executors.newFixedThreadPool(threadsNum);
		logger.info("init " + threadsNum + " threads...");
	}

	protected void recvMessage(ChannelHandlerContext ctx, GeneratedMessageLite request) {
		long interval = 111111;
		Future<?> future = null;
		try {
			future = handlerExecutor.submit(new Task());
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

	protected void encodeData(ChannelHandlerContext ctx, GeneratedMessageLite response, ByteBuf buffer) throws Exception {
		byte[] bytes = response.toByteArray();
		buffer.writeInt(bytes.length);
		buffer.writeBytes(bytes);
	}

	protected void decodeData(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.readBytes(bytes);
		try {
			long t = System.nanoTime();
			ReqDemo req = ReqDemo.parseFrom(bytes);
			System.err.println(Time.showTime(System.nanoTime() - t));
			out.add(req);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 任务
	 * @author ruan
	 */
	private class Task extends AbstractTask {
		public void run() {
			DemoHandler demoHandler = new DemoHandler();
			demoHandler.demo1(null);
		}
	}
}