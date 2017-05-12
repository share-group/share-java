package com.share.core.exception;

/**
 * mysql连接异常
 * @author ruan
 */
public class MysqlConnectException extends RuntimeException {
	private static final long serialVersionUID = -474443623584366339L;

	/**
	 * 构造函数
	 * @param message
	 */
	public MysqlConnectException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public MysqlConnectException(Throwable cause) {
		super(cause);
	}
}
