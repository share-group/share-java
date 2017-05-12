package com.share.core.exception;

/**
 * 方法参数不足异常
 * @author ruan
 */
public class MethodParametersNotEnoughException extends RuntimeException {
	private static final long serialVersionUID = 4903284891648824933L;

	/**
	 * 构造函数
	 * @param message
	 */
	public MethodParametersNotEnoughException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public MethodParametersNotEnoughException(Throwable cause) {
		super(cause);
	}
}