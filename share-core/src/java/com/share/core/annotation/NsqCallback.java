package com.share.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * nsq消息消费
 * @author ruan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NsqCallback {
	/**
	 * topic
	 */
	String topic();
	/**
	 * channel
	 */
	String channel();
	/**
	 * 是否只让一台消费
	 */
	boolean onlyChannel() default false;
}