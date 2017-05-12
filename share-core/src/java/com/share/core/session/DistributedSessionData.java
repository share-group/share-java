package com.share.core.session;

/**
 * 内部使用的数据结构
 * @author ruan
 */

public class DistributedSessionData {
	private byte[] data;

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}
}
