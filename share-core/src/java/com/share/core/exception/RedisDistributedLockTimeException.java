package com.share.core.exception;

/**
 * 获取分布式锁超时异常
 * @author ruan
 */
public class RedisDistributedLockTimeException extends RuntimeException {
	private static final long serialVersionUID = -7699377282652449679L;

	/**
	 * 构造函数
	 * @param message
	 */
	public RedisDistributedLockTimeException(String message) {
		super(message);
	}

	/**
	* 构造函数
	* @param cause
	*/
	public RedisDistributedLockTimeException(Throwable cause) {
		super(cause);
	}
}
