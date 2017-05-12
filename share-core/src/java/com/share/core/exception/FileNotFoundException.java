package com.share.core.exception;

/**
 * 非法端口异常
 * @author ruan
 */
public class FileNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6994893185222726935L;

	/**
	 * 构造函数
	 * @param message
	 */
	public FileNotFoundException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public FileNotFoundException(Throwable cause) {
		super(cause);
	}
}
