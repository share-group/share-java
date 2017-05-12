package com.share.core.annotation.processor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.share.core.exception.ClassTypeException;
import com.share.core.protocol.protocol.ProtocolRequest;
import com.share.core.protocol.protocol.ProtocolResponse;

/**
 * 协议注解解析器
 * @author ruan
 *
 */
public final class ProtocolProcessor extends AnnotationProcessor {
	/**
	 * 通过协议号获取protocol对象
	 */
	private static Map<Integer, Class<?>> protocolNumber2Object = new ConcurrentHashMap<>();
	/**
	 * 允许出现的数据类型
	 */
	private static Set<Class<?>> allowdType = new HashSet<Class<?>>();

	// 只允许这些类型出现
	static {
		allowdType.add(byte.class);
		allowdType.add(short.class);
		allowdType.add(int.class);
		allowdType.add(long.class);
		allowdType.add(boolean.class);
		allowdType.add(float.class);
		allowdType.add(double.class);
		allowdType.add(byte[].class);
		allowdType.add(short[].class);
		allowdType.add(int[].class);
		allowdType.add(long[].class);
		allowdType.add(boolean[].class);
		allowdType.add(float[].class);
		allowdType.add(double[].class);
		allowdType.add(ProtocolRequest.class);
		allowdType.add(ProtocolResponse.class);
		allowdType.add(ProtocolRequest[].class);
		allowdType.add(ProtocolResponse[].class);
	}

	/**
	 * 私有构造函数，只能通过spring实例化
	 */
	private ProtocolProcessor() {
	}

	protected void resolve(Object object, Class<?> clazz) {
		try {
			// 检查父类的父类是不是继承ProtocolRequest或者ProtocolResponse
			Class<?> superCLass = clazz.getSuperclass();
			if (!ProtocolRequest.class.equals(superCLass) && !ProtocolResponse.class.equals(superCLass)) {
				throw new ClassTypeException("class " + clazz.getName() + "'s must extends " + ProtocolRequest.class.getName() + " or " + ProtocolResponse.class.getName());
			}

			// 检查是否有非允许类型出现
			Field[] fieldList = clazz.getDeclaredFields();
			for (Field field : fieldList) {
				checkFieldType(field, field.getType());
			}

			int protocol = Integer.parseInt(clazz.getMethod("getProtocol").invoke(object).toString());
			if (protocol == 0) {
				return;
			}

			protocolNumber2Object.put(protocol, clazz);
			logger.info("set protocol {} => {}", protocol, clazz);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	protected void resolve(Object object, Method method) {
		throw new RuntimeException();
	}

	/**
	 * 根据协议号获取协议对象
	 * @author ruan
	 * @param protocol 协议号
	 */
	public Class<?> getProtocolObject(int protocol) {
		return protocolNumber2Object.get(protocol);
	}

	/**
	 * 检查field的类型
	 * @author ruan
	 * @param field
	 * @param type
	 */
	private void checkFieldType(Field field, Class<?> type) {
		if (field == null) {
			throw new NullPointerException();
		}
		if (type.isArray()) {
			type = type.getComponentType();
			if (type.isArray()) {
				checkFieldType(field, type);
			} else {
				isAllowdType(field, type);
			}
		} else {
			isAllowdType(field, type);
		}
	}

	/**
	 * 判断此类型是否为可允许类型
	 * @author ruan
	 * @param field
	 * @param type
	 */
	private void isAllowdType(Field field, Class<?> type) {
		if (allowdType.contains(type)) {
			return;
		}
		if (allowdType.contains(type.getSuperclass())) {
			return;
		}
		throw new ClassTypeException("field " + field.getName() + " can not use this type: " + field.getType().getSimpleName() + ", you just can use: " + getAllowdClassName());
	}

	/**
	 * 获取允许出现的类名
	 * @author ruan
	 * @return
	 */
	private String getAllowdClassName() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (Class<?> clazz : allowdType) {
			str.append(clazz.getSimpleName());
			str.append(",");
		}
		int len = str.length();
		str.delete(len - 1, len);
		str.append("]");
		return str.toString();
	}
}