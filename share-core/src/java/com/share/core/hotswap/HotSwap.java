package com.share.core.hotswap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 代码热替换
 * @author ruan
 *
 */
public class HotSwap {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(HotSwap.class);
	/**
	 * ctClass对象池
	 */
	private final static ClassPool ctClassPool = ClassPool.getDefault();
	/**
	 * thisCtClass
	 */
	private CtClass thisCtClass;
	/**
	 * ctFieldMap
	 */
	private Map<String, CtField> ctFieldMap = new HashMap<String, CtField>();

	/**
	 * 新建一个类
	 * @param className
	 */
	public void makeClass(String className) {
		thisCtClass = ctClassPool.makeClass(className);
	}

	/**
	 * 获取一个已存在的类
	 * @param clazz
	 */
	public void getClass(Class<?> clazz) {
		getClass(clazz.getName());
	}

	/**
	 * 获取一个已存在的类
	 * @param className
	 */
	public void getClass(String className) {
		try {
			thisCtClass = ctClassPool.getCtClass(className);
		} catch (NotFoundException e) {
			logger.error("", e);
		} finally {
			// 解冻这个类，才可以修改它
			thisCtClass.defrost();
		}
	}

	/**
	 * 新建一个构造函数
	 * @param parameters 构造函数的参数
	 */
	public void makeConstructor(CtClass... parameters) {
		makeConstructor("", parameters);
	}

	/**
	 * 新建一个构造函数
	 * @param bodySrc 函数体
	 * @param parameters 构造函数的参数
	 */
	public void makeConstructor(String bodySrc, CtClass... parameters) {
		try {
			CtConstructor ctConstructor = new CtConstructor(parameters, thisCtClass);
			if (!bodySrc.isEmpty()) {
				ctConstructor.setBody(bodySrc);
			}
			thisCtClass.addConstructor(ctConstructor);
		} catch (CannotCompileException e) {
			logger.error("", e);
		}
	}

	/**
	 * 新建一个方法
	 * @param modifier 修饰符
	 * @param returnType 返回类型
	 * @param methodName 方法名 
	 * @param parameters 方法参数
	 */
	public void makeMethod(int modifier, CtClass returnType, String methodName, CtClass... parameters) {
		makeMethod(modifier, returnType, methodName, "", parameters);
	}

	/**
	 * 新建一个方法
	 * @param modifier 修饰符
	 * @param returnType 返回类型
	 * @param methodName 方法名 
	 * @param bodySrc 方法体 
	 * @param parameters 方法参数
	 */
	public void makeMethod(int modifier, CtClass returnType, String methodName, String bodySrc, CtClass... parameters) {
		try {
			CtMethod ctMethod = new CtMethod(CtClass.voidType, methodName, parameters, thisCtClass);
			ctMethod.setModifiers(modifier);
			if (!bodySrc.isEmpty()) {
				ctMethod.setBody("{" + bodySrc + "}");
			}
			thisCtClass.addMethod(ctMethod);
		} catch (CannotCompileException e) {
			logger.error("", e);
		}
	}

	/**
	 * 修改一个已经存在的方法
	 * @param modifier 修饰符
	 * @param methodName 方法名 
	 * @param newNethodName 新方法名 
	 * @param bodySrc 方法体 
	 */
	public void modifyMethod(int modifier, String methodName, String newNethodName, String bodySrc) {
		try {
			CtMethod ctMethod = thisCtClass.getDeclaredMethod(methodName);
			ctMethod.setModifiers(modifier);
			if (!newNethodName.isEmpty()) {
				ctMethod.setName(newNethodName);
			}
			if (!bodySrc.isEmpty()) {
				ctMethod.setBody("{" + bodySrc + "}");
			}
		} catch (NotFoundException | CannotCompileException e) {
			logger.error("", e);
		}
	}

	/**
	 * 新建一个字段
	 * @param modifier 修饰符
	 * @param type 字段类型
	 * @param name 字段名字
	 */
	public void makeField(int modifier, CtClass type, String name) {
		try {
			CtField enoField = new CtField(type, name, thisCtClass);
			enoField.setModifiers(modifier);
			thisCtClass.addField(enoField);
			ctFieldMap.put(name, enoField);
		} catch (CannotCompileException e) {
			logger.error("", e);
		}
	}

	/**
	 * 给field新建一个getter方法
	 * @param fieldName field名字
	 */
	public void makeGetter(String fieldName) {
		try {
			String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			thisCtClass.addMethod(CtNewMethod.getter(methodName, ctFieldMap.get(fieldName)));
		} catch (CannotCompileException e) {
			logger.error("", e);
		}
	}

	/**
	 * 给field新建一个setter方法
	 * @param fieldName field名字
	 */
	public void makeSetter(String fieldName) {
		try {
			String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			thisCtClass.addMethod(CtNewMethod.setter(methodName, ctFieldMap.get(fieldName)));
		} catch (CannotCompileException e) {
			logger.error("", e);
		}
	}

	/**
	 * 生成.class文件
	 * @param directoryName 文件目录
	 */
	public void writeFile(String directoryName) {
		try {
			thisCtClass.writeFile(directoryName);
		} catch (IOException | CannotCompileException e) {
			logger.error("", e);
		}
	}

	/**
	 * 编译这个类
	 */
	public Class<?> compile() {
		try {
			return new HotSwapClassLoader(thisCtClass.toBytecode()).findClass(thisCtClass.getName());
		} catch (IOException | CannotCompileException e) {
			logger.error("", e);
		} finally {
			thisCtClass.detach();
		}
		return null;
	}
}