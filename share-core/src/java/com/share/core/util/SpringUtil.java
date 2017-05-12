package com.share.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring获取bean类(一般情况下用@Autowired不建议使用本类，实在想不到办法的情况下才使用)
 */
public class SpringUtil implements ApplicationContextAware {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(SpringUtil.class);
	/**
	 * 当前IOC
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 设置当前上下文环境，此方法由spring自动装配
	 */
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext = arg0;
		logger.warn("init spring util ...");
	}

	/**
	 * 从当前IOC获取bean
	 * @param clazz bean的类
	 */
	public final static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
}