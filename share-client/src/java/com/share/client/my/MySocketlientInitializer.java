package com.share.client.my;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import com.share.core.interfaces.AbstractSocketServerInitializer;
import com.share.core.protocol.protocol.ProtocolRequest;
import com.share.core.protocol.protocol.ProtocolResponse;
import com.share.core.util.SerialUtil;
import com.share.test.protocol.ResDemo;

public class MySocketlientInitializer extends AbstractSocketServerInitializer<ProtocolRequest, ProtocolResponse> {

	@Override
	protected void recvMessage(ChannelHandlerContext ctx, ProtocolResponse response) {
		MyClient.echo(response);
	}

	@Override
	public void catchException(ChannelHandlerContext ctx, Throwable e) {
		logger.error("", e);
	}

	@Override
	public void connectSuccess(ChannelHandlerContext ctx) {
		if (logger.isInfoEnabled()) {
			logger.info("connect to server " + ctx.channel().remoteAddress().toString() + " success.");
		}
		MyClient.ctx = ctx;
	}

	@Override
	public void disconnected(ChannelHandlerContext ctx) {
		if (logger.isInfoEnabled()) {
			logger.info("disconnect server " + ctx.channel().remoteAddress().toString() + ".");
		}
		ctx.disconnect();
	}

	@Override
	protected void encodeData(ChannelHandlerContext ctx, ProtocolRequest request, ByteBuf buffer) throws Exception {
		byte[] bytes = SerialUtil.toBytes(request);
		buffer.writeInt(bytes.length);
		buffer.writeBytes(bytes);
	}

	@Override
	protected void decodeData(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.readBytes(bytes);
		ResDemo res = SerialUtil.fromBytes(bytes, ResDemo.class);
		out.add(res);
	}

}
