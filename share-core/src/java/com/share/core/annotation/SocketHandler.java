package com.share.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * socket要处理的方法
 * @author ruan
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface SocketHandler {
	/**
	 * 来自哪个请求协议类
	 */
	Class<?> clazz();

	/**
	 * 请求时间间隔限制(单位约定为毫秒，默认为0，不限制)
	 * @return
	 */
	long interval() default 0;
}