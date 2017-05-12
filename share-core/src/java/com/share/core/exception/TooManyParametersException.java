package com.share.core.exception;

/**
 * 太多参数异常
 * @author ruan
 */
public class TooManyParametersException extends RuntimeException {
	private static final long serialVersionUID = 4946697320466877076L;

	/**
	 * 构造函数
	 * @param message
	 */
	public TooManyParametersException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public TooManyParametersException(Throwable cause) {
		super(cause);
	}
}
