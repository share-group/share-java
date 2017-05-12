package com.share.core.interfaces;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 频道初始化，因为netty要实现4个类，所以特意封装一下，简化实现
 * @author ruan
 */
public abstract class AbstractSocketServerInitializer<SEND, RECV> extends ChannelInitializer<SocketChannel> {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 单次接收数据最大长度
	 */
	private final static int socketBufferMaxLength = 1048576;
	/**
	 * 定义包头长度
	 */
	protected final static int bufferHeadLength = 4;

	/**
	 * 接收消息
	 * @author ruan
	 * @param ctx
	 * @param recv
	 */
	protected abstract void recvMessage(ChannelHandlerContext ctx, RECV recv);

	/**
	 * 异常捕获
	 * @author ruan
	 * @param ctx
	 * @param e
	 */
	public abstract void catchException(ChannelHandlerContext ctx, Throwable e);

	/**
	 * 连接成功
	 * @author ruan
	 * @param ctx
	 */
	public abstract void connectSuccess(ChannelHandlerContext ctx);

	/**
	 * 连接断开
	 * @author ruan
	 * @param ctx
	 */
	public abstract void disconnected(ChannelHandlerContext ctx);

	/**
	 * 编码
	 * @author ruan
	 * @param ctx
	 * @param send
	 * @param buffer
	 * @throws Exception
	 */
	protected abstract void encodeData(ChannelHandlerContext ctx, SEND send, ByteBuf buffer) throws Exception;

	/**
	 * 解码
	 * @author ruan	
	 * @param ctx
	 * @param buffer
	 * @param out
	 */
	protected abstract void decodeData(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out);

	/**
	 * 初始化管道
	 */
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("decoder", new Decoder());
		pipeline.addLast("encoder", new Encoder());
		pipeline.addLast("handler", new Handler());
	}

	private class Decoder extends ByteToMessageDecoder {
		protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
			int readableBytes = buffer.readableBytes();
			if (readableBytes < bufferHeadLength) {
				return;
			}

			int dataLength = buffer.getInt(buffer.readerIndex());
			if (dataLength <= 0 || dataLength > socketBufferMaxLength) {
				// 当数据长度小于等于0或者大于指定长度时，清空缓冲区
				buffer.clear();
				return;
			}

			if (readableBytes < dataLength + bufferHeadLength) {
				return;
			}

			// 跳过这4字节
			buffer.skipBytes(bufferHeadLength);
			decodeData(ctx, buffer, out);
			if (logger.isInfoEnabled()) {
				if (!out.isEmpty()) {
					Object obj = out.get(0);
					logger.info("recv: {} {}", obj.getClass().getSimpleName(), obj);
				}
			}
		}
	}

	private class Encoder extends MessageToByteEncoder<SEND> {
		protected void encode(ChannelHandlerContext ctx, SEND send, ByteBuf buffer) throws Exception {
			encodeData(ctx, send, buffer);
			buffer = null;
			if (logger.isInfoEnabled()) {
				logger.info("send: {} {}", send.getClass().getSimpleName(), send);
			}
		}
	}

	/**
	 * handler
	 * @author ruan
	 */
	private class Handler extends SimpleChannelInboundHandler<RECV> {
		/**
		 * 接收消息
		 */
		protected void messageReceived(ChannelHandlerContext ctx, RECV recv) throws Exception {
			recvMessage(ctx, recv);
		}

		/**
		 * 异常捕获
		 */
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
			catchException(ctx, e);
		}

		/**
		 * 连接成功
		 */
		public void channelActive(ChannelHandlerContext ctx) {
			connectSuccess(ctx);
		}

		/**
		 * 连接断开
		 */
		public void channelInactive(ChannelHandlerContext ctx) {
			disconnected(ctx);
			ctx = null;
		}
	}
}