package com.share.core.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 验证类接口
 * @author ruan
 */
public abstract class Verification {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 各自业务实现验证逻辑
	 * @param request
	 * @param response
	 */
	public abstract boolean check(HttpServletRequest request, HttpServletResponse response);
}