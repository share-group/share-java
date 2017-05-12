package com.share.core.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务器接口
 * @author ruan
 */
public abstract class AbstractServer {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 启动服务器
	 */
	public abstract void start();
}