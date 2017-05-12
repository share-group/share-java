package com.share.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 菜单注解，一般是做后台用，支持一级二级菜单<br>
 * 主要是用来做后台菜单显示用，<br>
 * 配合@RequestMapping一起用<br>
 * 例子：<br>
 * <a>@RequestMapping(value = "/admin/1", method = RequestMethod.GET)<br>
 * <a>@Menu(menu = "admin1", parentMenu = "admin")<br>
 * public void admin1() {<br>
 * 		// do you business<br>
 * }
 * @author ruan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Menu {
	/**
	 * 菜单
	 */
	String menu();

	/**
	 * 父菜单(二级菜单用)
	 */
	String parentMenu() default "";

	/**
	 * 是否为内部使用(即不显示在菜单栏上，默认显示)
	 */
	boolean isInner() default false;
}