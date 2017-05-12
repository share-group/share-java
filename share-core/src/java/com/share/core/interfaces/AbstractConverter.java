package com.share.core.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 数据转换器
 * @author ruan
 *
 */
public abstract class AbstractConverter implements HandlerMethodArgumentResolver {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public boolean supportsParameter(MethodParameter parameter) {
		return true;
	}

	public abstract Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory);
}