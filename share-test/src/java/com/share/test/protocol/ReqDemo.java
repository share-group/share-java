package com.share.test.protocol;

import com.share.core.annotation.Protocol;
import com.share.core.protocol.protocol.ProtocolRequest;

import io.netty.buffer.ByteBuf;

@Protocol
public class ReqDemo extends ProtocolRequest {
	private int id;
	private byte[] name = new byte[10];
	private byte[] email = new byte[10];

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return new String(name).trim();
	}

	public void setName(String name) {
		//StringUtil.copyToBytes(name, this.name);
	}

	public String getEmail() {
		return new String(email).trim();
	}

	public void setEmail(String email) {
		//StringUtil.copyToBytes(email, this.email);
	}

	public void setName(byte[] name) {
		this.name = name;
	}

	public void setEmail(byte[] email) {
		this.email = email;
	}

	public void loadFromBuffer(ByteBuf buffer) {
		id = buffer.readInt();
		buffer.readBytes(name);
		buffer.readBytes(email);
	}

	public void convert2Buffer(ByteBuf buffer) {
		buffer.writeInt(id);
		buffer.writeBytes(name);
		buffer.writeBytes(email);
	}
}
