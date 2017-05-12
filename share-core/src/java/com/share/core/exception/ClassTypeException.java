package com.share.core.exception;

/**
 * 类类型异常
 * @author ruan
 */
public class ClassTypeException extends RuntimeException {
	private static final long serialVersionUID = -4005986537987389676L;

	/**
	 * 构造函数
	 * @param message
	 */
	public ClassTypeException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public ClassTypeException(Throwable cause) {
		super(cause);
	}
}
