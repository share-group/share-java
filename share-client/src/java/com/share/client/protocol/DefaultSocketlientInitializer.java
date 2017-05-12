package com.share.client.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import com.share.core.interfaces.AbstractSocketServerInitializer;
import com.share.core.protocol.protocol.ProtocolBase;
import com.share.core.protocol.protocol.ProtocolRequest;
import com.share.core.protocol.protocol.ProtocolResponse;
import com.share.test.protocol.ResDemo;

public class DefaultSocketlientInitializer extends AbstractSocketServerInitializer<ProtocolRequest, ProtocolResponse> {

	@Override
	protected void recvMessage(ChannelHandlerContext ctx, ProtocolResponse response) {
		ProtocolClient.echo(response);
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
		ProtocolClient.ctx = ctx;
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
		ByteBuf buf = Unpooled.buffer();
		request.convert2Buffer(buf);
		buffer.writeInt(buf.readableBytes());
		buffer.writeInt(request.getProtocol());
		buffer.writeBytes(buf);
	}

	@Override
	protected void decodeData(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
		int protocol = buffer.readInt();
		ProtocolBase res = new ResDemo();
		res.loadFromBuffer(buffer);
		out.add(res);
	}

}
