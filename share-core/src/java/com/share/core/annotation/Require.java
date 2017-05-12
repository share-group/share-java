package com.share.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作为协议字段，指定是否为必传参数
 * @author ruan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Require {
	/**
	 * 是否必须
	 */
	boolean require() default true;
}