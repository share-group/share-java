package com.share.core.exception;

/**
 * 注解重复异常
 * @author ruan
 *
 */
public class DuplicateAnnotationException extends RuntimeException {
	private static final long serialVersionUID = 7363619415766773878L;

	/**
	 * 构造函数
	 * @param message
	 */
	public DuplicateAnnotationException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public DuplicateAnnotationException(Throwable cause) {
		super(cause);
	}
}
