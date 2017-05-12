package com.share.core.exception;

/**
 * 方法参数类型异常
 * @author ruan
 */
public class MethodParamTypeNotInstenceofException extends RuntimeException {
	private static final long serialVersionUID = 7136140630819077182L;

	/**
	 * 构造函数
	 * @param message
	 */
	public MethodParamTypeNotInstenceofException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public MethodParamTypeNotInstenceofException(Throwable cause) {
		super(cause);
	}
}
