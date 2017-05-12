package com.share.core.exception;

/**
 * 类名错误异常
 * @author ruan
 */
public class ClassNameException extends RuntimeException {
	private static final long serialVersionUID = 2081114582277424011L;

	/**
	 * 构造函数
	 * @param message
	 */
	public ClassNameException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public ClassNameException(Throwable cause) {
		super(cause);
	}
}
