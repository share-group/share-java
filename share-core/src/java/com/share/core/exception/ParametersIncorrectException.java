package com.share.core.exception;

/**
 * 参数错误异常
 * @author ruan
 */
public class ParametersIncorrectException extends RuntimeException {
	private static final long serialVersionUID = 4903284891648824933L;

	/**
	 * 构造函数
	 * @param message
	 */
	public ParametersIncorrectException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public ParametersIncorrectException(Throwable cause) {
		super(cause);
	}
}