package com.share.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配置表用的注解<br>
 * 系统启动时会自动把配置文件加载到内存中<br>
 * 使用此注解一定要继承 com.share.core.interfaces.DSuper 这个类
 * @author ruan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Config {
	/**
	 * 用于分层的key
	 * @author ruan
	 * @return
	 */
	String[] key();

	/**
	 * 配置表格式，默认csv(例如：csv、xml等等)
	 * @author ruan
	 * @return
	 */
	String mode() default "csv";

	/**
	 * 配置文件读取路径(默认是当前相对路径)<br>
	 * 要求：文件名，大小写敏感，除C之外，文件名和类名要一致并且符合java峰驼式的规范<br>
	 * 例如：userConfig.csv对应的类名就是CUserConfig
	 * @author ruan
	 * @return
	 */
	String path() default "config/";
}