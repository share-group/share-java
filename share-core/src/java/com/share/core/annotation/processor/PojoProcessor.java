package com.share.core.annotation.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.share.core.annotation.AsMongoId;
import com.share.core.exception.ClassNameException;
import com.share.core.exception.ClassTypeException;
import com.share.core.exception.DuplicateAnnotationException;
import com.share.core.interfaces.DSuper;

/**
 * 数据库pojo对象解析器
 * @author ruan
 */
public final class PojoProcessor extends AnnotationProcessor {
	/**
	 * pojo类对应的字段&set方法map
	 */
	private Map<Class<?>, Map<String, Method>> pojoClassSetMethodMap = new ConcurrentHashMap<>();
	/**
	 * pojo类对应的字段&get方法map
	 */
	private Map<Class<?>, Map<String, Method>> pojoClassGetMethodMap = new ConcurrentHashMap<>();

	/**
	 * 私有构造函数，只能通过spring实例化
	 */
	private PojoProcessor() {
	}

	protected void resolve(Object object, Class<?> clazz) {
		// 检查是不是继承DSuper这个类
		if (!DSuper.class.equals(clazz.getSuperclass())) {
			throw new ClassTypeException("class " + clazz.getName() + " must extends " + DSuper.class.getName());
		}

		// 一定要D开头
		if (!clazz.getSimpleName().substring(0, 1).equals("D")) {
			throw new ClassNameException(clazz + "'s name must define D in the class name beginning, like D" + clazz.getSimpleName());
		}

		// 预先做好反射，到后面直接取方法即可
		Set<Annotation> annotationSet = new HashSet<Annotation>(1);
		for (Field field : clazz.getDeclaredFields()) {
			addPojoClass(clazz, field);

			// 如果使用了AsMongoId注解，那么只可以用一次
			Annotation asMongoIdAnnotation = field.getAnnotation(AsMongoId.class);
			if (asMongoIdAnnotation == null) {
				continue;
			}
			if (!annotationSet.add(asMongoIdAnnotation)) {
				throw new DuplicateAnnotationException(clazz + ", field:" + field.getName() + ", duplicate annotation:" + asMongoIdAnnotation.toString());
			}
		}

		logger.info("reflect pojo {}", clazz);
	}

	protected void resolve(Object object, Method method) {
		throw new RuntimeException();
	}

	/**
	 * 添加一个pojo类
	 * @author ruan
	 * @param pojoClass pojo类
	 * @param field 字段
	 */
	private void addPojoClass(Class<?> pojoClass, Field field) {
		//setter
		synchronized (pojoClassSetMethodMap) {
			try {
				Map<String, Method> methodMap = pojoClassSetMethodMap.get(pojoClass);
				if (methodMap == null) {
					methodMap = new ConcurrentHashMap<>();
					pojoClassSetMethodMap.put(pojoClass, methodMap);
				}
				methodMap.put(field.getName(), pojoClass.getMethod(getSetter(field), field.getType()));
			} catch (Exception e) {
				logger.error("", e);
				System.exit(0);
			}
		}

		//getter
		synchronized (pojoClassGetMethodMap) {
			try {
				Map<String, Method> methodMap = pojoClassGetMethodMap.get(pojoClass);
				if (methodMap == null) {
					methodMap = new ConcurrentHashMap<>();
					pojoClassGetMethodMap.put(pojoClass, methodMap);
				}
				methodMap.put(field.getName(), pojoClass.getMethod(getGetter(field)));
			} catch (Exception e) {
				logger.error("", e);
				System.exit(0);
			}
		}
	}

	/**
	 * 根据pojo类获取它的set方法map
	 * @author ruan
	 * @param pojoClass pojo类
	 */
	public Map<String, Method> getSetMethodMapByClass(Class<?> pojoClass) {
		return pojoClassSetMethodMap.get(pojoClass);
	}

	/**
	 * 根据pojo类获取它的get方法map
	 * @author ruan
	 * @param pojoClass pojo类
	 */
	public Map<String, Method> getGetMethodMapByClass(Class<?> pojoClass) {
		return pojoClassGetMethodMap.get(pojoClass);
	}

	/**
	 * 根据属性名，获取setter方法名
	 * @param field
	 * @return
	 */
	private String getSetter(Field field) {
		String fieldName = field.getName();
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * 根据属性名，获取getter方法名
	 * @param field
	 * @return
	 */
	private String getGetter(Field field) {
		String fieldName = field.getName();
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
}