package com.share.core.exception;

/**
 * 未定义注解异常
 * @author ruan
 *
 */
public class NoDefineAnnotationException extends RuntimeException {
	private static final long serialVersionUID = 8695027744006277830L;

	/**
	 * 构造函数
	 * @param message
	 */
	public NoDefineAnnotationException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public NoDefineAnnotationException(Throwable cause) {
		super(cause);
	}
}
