package com.share.core.annotation.processor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.share.core.annotation.Config;
import com.share.core.exception.ClassNameException;
import com.share.core.exception.ClassTypeException;
import com.share.core.exception.ColumnIncorrectException;
import com.share.core.exception.NoDefineAnnotationException;
import com.share.core.interfaces.DSuper;
import com.share.core.memory.Memory;
import com.share.core.util.FileSystem;

/**
 * 配置注解解析器
 * @author ruan
 *
 */
public final class ConfigProcessor extends AnnotationProcessor {
	/**
	 * 允许出现的数据类型
	 */
	private static Set<Class<?>> allowdType = new HashSet<Class<?>>();
	/**
	 * 内存数据映射
	 */
	@Autowired
	private Memory memory;

	// 只允许这些类型出现
	static {
		allowdType.add(byte.class);
		allowdType.add(short.class);
		allowdType.add(int.class);
		allowdType.add(long.class);
		allowdType.add(boolean.class);
		allowdType.add(float.class);
		allowdType.add(double.class);
		allowdType.add(String.class);
	}

	/**
	 * 私有构造函数，只能通过spring实例化
	 */
	private ConfigProcessor() {
	}

	protected void resolve(Object object, Method method) {
		throw new RuntimeException();
	}

	protected void resolve(Object object, Class<?> clazz) {
		// 检查是不是继承DSuper这个类
		if (!DSuper.class.equals(clazz.getSuperclass())) {
			throw new ClassTypeException("class " + clazz.getName() + " must extends " + DSuper.class.getName());
		}

		// 一定要C开头
		if (!clazz.getSimpleName().substring(0, 1).equals("C")) {
			throw new ClassNameException(clazz + "'s name must define C in the class name beginning, like C" + clazz.getSimpleName());
		}

		// 检查是否有非允许类型出现
		Field[] fieldList = clazz.getDeclaredFields();
		for (Field field : fieldList) {
			if (!allowdType.contains(field.getType())) {
				throw new ClassTypeException("clsss " + clazz.getSimpleName() + " can not use this type: " + field.getType().getSimpleName() + ", you just can use: " + getAllowdClassName());
			}
		}

		// 判断有没有使用注解
		Config config = clazz.getAnnotation(Config.class);
		if (config == null) {
			throw new NoDefineAnnotationException("not define annotation " + Config.class);
		}

		// 获取配置文件路径
		String path = getFilePath(object);

		// 读取文件
		List<List<String>> list = FileSystem.readCSV(FileSystem.getSystemDir() + path);

		// 检查字段名称和顺序是否一致
		List<String> column = list.remove(0);
		int columnSize = column.size();
		for (int i = 0; i < columnSize; i++) {
			if (!column.get(i).equals(fieldList[i].getName())) {
				throw new ColumnIncorrectException("incorrect column! please see " + clazz.getName() + " and " + path);
			}
		}

		// 写入DMap
		memory.putMap(clazz, list, config.key());

		logger.info("loading config {} into {}", path.replace(config.path(), ""), clazz);
	}

	/**
	 * 获取类的名字
	 * @author ruan
	 * @param object 类对象
	 * @return
	 */
	private String getClassName(Object object) {
		String className = object.getClass().getSimpleName().trim();
		className = className.substring(1);
		return className.substring(0, 1).toLowerCase().trim() + className.substring(1).trim();
	}

	/**
	 * 获取文件路径
	 * @author ruan
	 * @param object 类对象
	 * @return
	 */
	private String getFilePath(Object object) {
		Class<?> clazz = object.getClass();
		Config config = clazz.getAnnotation(Config.class);
		return config.path().trim() + getClassName(object) + "." + config.mode().trim();
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