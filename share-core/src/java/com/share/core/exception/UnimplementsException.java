package com.share.core.exception;

/**
 * 类方法未被子类实现异常
 * @author ruan
 *
 */
public class UnimplementsException extends RuntimeException {
	private static final long serialVersionUID = 7938731567588957310L;

	/**
	 * 构造函数
	 * @param message
	 */
	public UnimplementsException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public UnimplementsException(Throwable cause) {
		super(cause);
	}
}
