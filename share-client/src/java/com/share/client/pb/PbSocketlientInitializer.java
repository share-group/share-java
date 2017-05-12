package com.share.client.pb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.share.core.interfaces.AbstractSocketServerInitializer;
import com.share.core.protocol.protobuf.demo.Demo.ReqDemo;
import com.share.core.protocol.protocol.ProtocolBase;
import com.share.core.protocol.protocol.ProtocolRequest;
import com.share.core.protocol.protocol.ProtocolResponse;
import com.share.test.protocol.ResDemo;

public class PbSocketlientInitializer extends AbstractSocketServerInitializer<GeneratedMessageLite, GeneratedMessageLite> {

	@Override
	protected void recvMessage(ChannelHandlerContext ctx, GeneratedMessageLite response) {
		PbClient.echo(response);
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
		PbClient.ctx = ctx;
	}

	@Override
	public void disconnected(ChannelHandlerContext ctx) {
		if (logger.isInfoEnabled()) {
			logger.info("disconnect server " + ctx.channel().remoteAddress().toString() + ".");
		}
		ctx.disconnect();
	}

	@Override
	protected void encodeData(ChannelHandlerContext ctx, GeneratedMessageLite request, ByteBuf buffer) throws Exception {
		byte[] bytes = request.toByteArray();
		buffer.writeInt(bytes.length);
		buffer.writeBytes(bytes);
	}

	@Override
	protected void decodeData(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.readBytes(bytes);
		try {
			ReqDemo req = ReqDemo.parseFrom(bytes);
			out.add(req);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
	}

}
