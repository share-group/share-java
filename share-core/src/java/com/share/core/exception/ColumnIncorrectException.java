package com.share.core.exception;

/**
 * 字段顺序错误异常
 * @author ruan
 */
public class ColumnIncorrectException extends RuntimeException {
	private static final long serialVersionUID = -7921997031685867630L;

	/**
	 * 构造函数
	 * @param message
	 */
	public ColumnIncorrectException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public ColumnIncorrectException(Throwable cause) {
		super(cause);
	}
}
