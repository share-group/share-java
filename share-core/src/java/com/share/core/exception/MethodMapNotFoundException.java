package com.share.core.exception;

/**
 * 找不到方法map异常
 * @author ruan
 */
public class MethodMapNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7950839942181364661L;

	/**
	 * 构造函数
	 * @param message
	 */
	public MethodMapNotFoundException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public MethodMapNotFoundException(Throwable cause) {
		super(cause);
	}
}
