package com.share.core.exception;

/**
 * http请求方法未定义异常
 * @author ruan
 *
 */
public class HttpRequestMethodNotDefineException extends RuntimeException {
	private static final long serialVersionUID = -1629508218377468336L;

	/**
	 * 构造函数
	 * @param message
	 */
	public HttpRequestMethodNotDefineException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public HttpRequestMethodNotDefineException(Throwable cause) {
		super(cause);
	}
}
