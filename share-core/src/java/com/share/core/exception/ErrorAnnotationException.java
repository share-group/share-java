package com.share.core.exception;

/**
 * 错误的注解异常
 * @author ruan
 *
 */
public class ErrorAnnotationException extends RuntimeException {
	private static final long serialVersionUID = 7748877269617415117L;

	/**
	 * 构造函数
	 * @param message
	 */
	public ErrorAnnotationException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public ErrorAnnotationException(Throwable cause) {
		super(cause);
	}
}
