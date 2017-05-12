package com.share.core.exception;

/**
 * redis命令不支持异常
 * @author ruan
 *
 */
public class RedisCommandNotSupportException extends RuntimeException {
	private static final long serialVersionUID = 4839784351772893942L;

	/**
	 * 构造函数
	 * @param message
	 */
	public RedisCommandNotSupportException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public RedisCommandNotSupportException(Throwable cause) {
		super(cause);
	}
}
