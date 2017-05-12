package com.share.core.annotation.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.core.exception.ParametersIncorrectException;
import com.share.core.util.SystemUtil;

/**
 * 注解解析器
 * @author ruan
 *
 */
abstract class AnnotationProcessor {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 要扫描的包名
	 */
	protected String packageName;
	/**
	 * 要扫描的注解类名
	 */
	protected Class<?> annotationClass;

	/**
	 * 设置要扫描的包名
	 * @author ruan
	 * @param packageName 包名
	 */
	public void setScanPackage(String packageName) {
		if (!packageName.matches("^[a-zA-Z\\.,]+$")) {
			throw new ParametersIncorrectException("scan package define incorrect! you can only input 26 case-insensitive and ',' and '.'");
		}
		this.packageName = packageName;
	}

	/**
	 * 设置要扫描的注解类名
	 * @author ruan
	 * @param annotationClass 注解类名
	 */
	public void setAnnotationClass(String annotationClass) {
		try {
			this.annotationClass = Class.forName(annotationClass);
		} catch (ClassNotFoundException e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	/**
	 * 初始化
	 * @author ruan
	 */
	public void init() {
		if (packageName == null) {
			throw new ParametersIncorrectException("you must define the scanPackage!");
		}
		if (annotationClass == null) {
			throw new ParametersIncorrectException("you must define the annotationClass!");
		}
		try {
			for (String pkgName : packageName.split(",")) {
				Set<Class<?>> classSet = SystemUtil.getClasses(pkgName);
				if (classSet == null || classSet.isEmpty()) {
					throw new RuntimeException("package " + pkgName + " is empty");
				}
				for (Class<?> c : classSet) {
					if (c.getName().indexOf("$") > -1) {
						// 过滤内部类
						continue;
					}
					Object o = c.newInstance();

					// 首先判断类有没有目标注解
					for (Annotation a : c.getAnnotations()) {
						if (!annotationClass.equals(a.annotationType())) {
							continue;
						}
						// 如果目标类有注解，解析
						resolve(o, c);
					}

					// 类和方法都可以解析该注解
					for (Method m : c.getDeclaredMethods()) {
						for (Annotation a : m.getDeclaredAnnotations()) {
							if (!annotationClass.equals(a.annotationType())) {
								continue;
							}
							resolve(o, m);
						}
					}

				}
			}
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	/**
	 * 自定义实现注解解析逻辑
	 * @param object 类对象
	 * @param clazz 已加入该注解的类
	 */
	protected abstract void resolve(Object object, Class<?> clazz);

	/**
	 * 自定义实现注解解析逻辑
	 * @param object 类对象
	 * @param method 已加入该注解的方法
	 */
	protected abstract void resolve(Object object, Method method);
}