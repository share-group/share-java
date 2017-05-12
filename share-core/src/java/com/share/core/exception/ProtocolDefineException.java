package com.share.core.exception;

/**
 * 协议号定义异常
 * @author ruan
 *
 */
public class ProtocolDefineException extends RuntimeException {
	private static final long serialVersionUID = 8855213937685228384L;

	/**
	 * 构造函数
	 * @param message
	 */
	public ProtocolDefineException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public ProtocolDefineException(Throwable cause) {
		super(cause);
	}
}
