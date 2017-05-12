package com.share.core.exception;

/**
 * URL未定义异常
 * @author ruan
 *
 */
public class URLNotDefineException extends RuntimeException {
	private static final long serialVersionUID = 1261273537495170236L;

	/**
	 * 构造函数
	 * @param message
	 */
	public URLNotDefineException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public URLNotDefineException(Throwable cause) {
		super(cause);
	}
}
