package com.share.core.exception;

/**
 * 文件找不到异常
 * @author ruan
 */
public class IllegalPortException extends RuntimeException {
	private static final long serialVersionUID = 8612906069735052915L;

	/**
	 * 构造函数
	 * @param message
	 */
	public IllegalPortException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public IllegalPortException(Throwable cause) {
		super(cause);
	}
}
