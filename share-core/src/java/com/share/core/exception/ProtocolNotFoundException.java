package com.share.core.exception;

/**
 * 获取不了协议的异常
 * @author ruan
 */
public class ProtocolNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3339030486253314863L;

	/**
	 * 构造函数
	 * @param message
	 */
	public ProtocolNotFoundException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * @param cause
	 */
	public ProtocolNotFoundException(Throwable cause) {
		super(cause);
	}
}
