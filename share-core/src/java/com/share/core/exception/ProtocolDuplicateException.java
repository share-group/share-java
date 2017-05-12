package com.share.core.exception;

/**
 * 协议重复异常
 * @author ruan
 *
 */
public class ProtocolDuplicateException extends RuntimeException {
	private static final long serialVersionUID = -1805162632856624148L;

	/**
	 * 构造函数
	 * @param message
	 */
	public ProtocolDuplicateException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public ProtocolDuplicateException(Throwable cause) {
		super(cause);
	}
}
