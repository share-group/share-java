package com.share.core.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;		

/**
 * 服务器接口
 * @author ruan
 */
public abstract class AbstractServer {
	/**
	 * logger
	 */
	protected Logger logger = LogManager.getLogger(getClass());

	/**
	 * 启动服务器
	 */
	public abstract void start();
}