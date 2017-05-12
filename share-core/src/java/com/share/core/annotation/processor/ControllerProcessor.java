package com.share.core.annotation.processor;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.share.core.exception.HttpRequestMethodNotDefineException;
import com.share.core.exception.URLNotDefineException;

/**
 * 控制器注解解析器<br>
 * 用来限制有使用@RequestMapping注解的方法一定要有value和method两个值	
 * @author ruan
 */
public class ControllerProcessor extends AnnotationProcessor {
	/**
	 * 私有构造函数，只能通过spring实例化
	 */
	private ControllerProcessor() {
	}

	protected void resolve(Object object, Class<?> clazz) {
		for (Method method : clazz.getDeclaredMethods()) {
			// 只对有使用@RequestMapping注解的方法有限制
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			if (requestMapping == null) {
				continue;
			}
			if (!RequestMapping.class.equals(requestMapping.annotationType())) {
				continue;
			}
			if (requestMapping.value().length <= 0) {
				throw new URLNotDefineException("method  " + method + " not defined url");
			}

			RequestMethod[] requestMethod = requestMapping.method();
			if (requestMethod.length <= 0) {
				throw new HttpRequestMethodNotDefineException("method " + method + " not defined method");
			}
		}
	}

	protected void resolve(Object object, Method method) {
		throw new RuntimeException();
	}
}