package com.share.test.socket.initializer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.share.core.annotation.processor.SocketHandlerProcessor;
import com.share.core.interfaces.AbstractSocketServerInitializer;
import com.share.core.interfaces.AbstractTask;
import com.share.core.protocol.protocol.ProtocolBase;
import com.share.core.protocol.protocol.ProtocolRequest;
import com.share.core.protocol.protocol.ProtocolResponse;
import com.share.core.threadPool.DefaultThreadPool;

public final class DefaultSocketServerInitializer extends AbstractSocketServerInitializer<ProtocolResponse, ProtocolRequest> {
	/**
	 * 线程池
	 */
	private DefaultThreadPool threadPool = null;
	/**
	 * socket注解解析器
	 */
	private final static SocketHandlerProcessor socketHandlerProcessor = new SocketHandlerProcessor();

	/**
	 * 构造函数
	 * @param threadsNum 线程数	
	 */
	public DefaultSocketServerInitializer(int threadsNum) {
		threadPool = new DefaultThreadPool();
		threadPool.changeName(getClass().getName());
		threadPool.setPoolSize(threadsNum);
		threadPool.init();
	}

	protected void recvMessage(ChannelHandlerContext ctx, ProtocolRequest request) {
		long interval = socketHandlerProcessor.getInterval(request.getProtocol());
		threadPool.execute(new Task(request, ctx), interval, TimeUnit.MILLISECONDS);
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
		ByteBuf buf = Unpooled.buffer();
		response.convert2Buffer(buf);
		buffer.writeInt(buf.readableBytes() + 4);
		buffer.writeInt(response.getProtocol());
		buffer.writeBytes(buf);
	}

	protected void decodeData(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
		int protocol = buffer.readInt();
		ProtocolBase req = socketHandlerProcessor.getProtocol(protocol);
		req.loadFromBuffer(buffer);
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
			Object clazz = socketHandlerProcessor.getClass(request.getProtocol());
			Method method = socketHandlerProcessor.getMethod(request.getProtocol());
			if (clazz == null || method == null) {
				return;
			}
			Object object = null;
			try {
				object = method.invoke(clazz, request);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.error("", e);
			} finally {
				if (!(object instanceof ProtocolResponse)) {
					return;
				}
				ctx.writeAndFlush(object);
				object = null;
			}
		}
	}
}