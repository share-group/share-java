package com.share.core.exception;

/**
 * 类实现接口异常
 * @author ruan
 */
public class ClassInterfacesException extends RuntimeException {
	private static final long serialVersionUID = -20356233028840474L;

	/**
	 * 构造函数
	 * @param message
	 */
	public ClassInterfacesException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public ClassInterfacesException(Throwable cause) {
		super(cause);
	}
}
