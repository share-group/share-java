package com.share.core.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程任务基类
 */
public abstract class AbstractTask implements Runnable {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * toString方法
	 */
	public String toString() {
		return getClass().getSimpleName();
	}
}