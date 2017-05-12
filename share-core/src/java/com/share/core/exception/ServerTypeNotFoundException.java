package com.share.core.exception;

/**
 * 无法找到服务器类型异常
 * @author ruan
 */
public class ServerTypeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -312782006462119853L;

	/**
	 * 构造函数
	 * @param message
	 */
	public ServerTypeNotFoundException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public ServerTypeNotFoundException(Throwable cause) {
		super(cause);
	}
}
