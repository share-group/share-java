package com.share.core.annotation.processor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.share.core.annotation.SocketHandler;
import com.share.core.exception.ClassTypeException;
import com.share.core.exception.MethodParamTypeNotInstenceofException;
import com.share.core.exception.MethodParametersNotEnoughException;
import com.share.core.exception.ProtocolDuplicateException;
import com.share.core.exception.ProtocolNotFoundException;
import com.share.core.interfaces.BaseHandler;
import com.share.core.protocol.protocol.ProtocolBase;
import com.share.core.protocol.protocol.ProtocolRequest;
import com.share.core.util.StringUtil;

/**
 * socket注解解析器
 * @author ruan
 */
public final class SocketHandlerProcessor extends AnnotationProcessor {
	/**
	 * 协议类对应map
	 */
	private static Map<Integer, String> protocolMap = new ConcurrentHashMap<>();
	/**
	 * 协议号对应类map
	 */
	private static Map<Integer, Object> protocolClassMap = new ConcurrentHashMap<>();
	/**
	 * 协议号对应方法map
	 */
	private static Map<Integer, Method> protocolMethodMap = new ConcurrentHashMap<>();
	/**
	 * 协议号对应访问间隔
	 */
	private static Map<Integer, Long> protocolIntervalMap = new ConcurrentHashMap<>();
	/**
	 * context
	 */
	@Autowired
	private ApplicationContext context;

	/**
	 * 解析逻辑
	 */
	protected void resolve(Object object, Method method) {
		try {
			String superClassName = ProtocolRequest.class.getName().trim();
			SocketHandler socketHandler = method.getAnnotation(SocketHandler.class);
			Class<?> clazz = socketHandler.clazz();
			String className = clazz.getName().trim();
			if (!superClassName.equals(clazz.getSuperclass().getName())) {
				throw new ClassTypeException("error class type! protocol must extends: " + superClassName + "!");
			}

			// 获取所有handler的实例
			Map<String, BaseHandler> classesMap = context.getBeansOfType(BaseHandler.class);
			if (classesMap == null || classesMap.isEmpty()) {
				return;
			}
			String handlerClassName = object.getClass().getSimpleName();
			handlerClassName = handlerClassName.substring(0, 1).toLowerCase() + handlerClassName.substring(1);
			BaseHandler baseHandler = classesMap.get(handlerClassName);
			if (baseHandler == null) {
				throw new ClassNotFoundException("can not found class " + handlerClassName);
			}
			ProtocolBase protocol = (ProtocolBase) Class.forName(className).newInstance();
			set(protocol, baseHandler, method, socketHandler);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	/**
	 * 解析逻辑
	 */
	protected void resolve(Object object, Class<?> clazz) {
		throw new RuntimeException();
	}

	/**
	 * 保存"类->方法"对
	 * @param protocol 协议对象
	 * @param object 类对象
	 * @param method 已加入该注解的方法
	 * @param socketHandler 此注解
	 */
	private void set(ProtocolBase protocol, Object object, Method method, SocketHandler socketHandler) {
		// 检查方法定义的参数数量是否足够
		Class<?>[] parameterTypesArray = method.getParameterTypes();
		if (parameterTypesArray.length <= 0) {
			throw new MethodParametersNotEnoughException("method " + object.getClass().getName() + "." + method.getName() + " parameters not enough...");
		}

		// 检查参数是否继承ProtocolBase
		if (!parameterTypesArray[0].getName().trim().equals(protocol.getClass().getName().trim())) {
			throw new MethodParamTypeNotInstenceofException("method " + object.getClass().getName() + "." + method.getName() + "' first parameter's type must instanceof " + protocol.getClass().getName().trim());
		}

		// 检查协议号是否有重复
		if (protocolClassMap.containsKey(protocol.getProtocol()) || protocolMethodMap.containsKey(protocol.getProtocol()) || protocolIntervalMap.containsKey(protocol.getProtocol())) {
			throw new ProtocolDuplicateException("protocol: " + protocol.getProtocol() + "'s handle method is duplicate! " + object.getClass().getName() + "." + method.getName() + ", " + protocolClassMap.get(protocol.getProtocol()).getClass().getName() + "." + protocolMethodMap.get(protocol.getProtocol()).getName());
		}

		protocolMap.put(protocol.getProtocol(), protocol.getClass().getName());
		protocolClassMap.put(protocol.getProtocol(), object);
		protocolMethodMap.put(protocol.getProtocol(), method);
		protocolIntervalMap.put(protocol.getProtocol(), socketHandler.interval());

		logger.info("mapped protocol " + protocol.getProtocol() + " => " + method);
	}

	/**
	 * 通过协议号获得方法
	 * @param protocol 协议号
	 * @return
	 */
	public Object getClass(int protocol) {
		return protocolClassMap.get(protocol);
	}

	/**
	 * 通过协议号获得方法
	 * @param protocol 协议号
	 * @return
	 */
	public Method getMethod(int protocol) {
		return protocolMethodMap.get(protocol);
	}

	/**
	 * 通过协议号获取访问间隔
	 * @param protocol 协议号
	 * @return
	 */
	public long getInterval(int protocol) {
		return StringUtil.getLong(protocolIntervalMap.get(protocol));
	}

	/**
	 * 通过协议号获取协议类(已实例化)
	 * @param protocol 协议号
	 * @return
	 */
	public ProtocolBase getProtocol(int protocol) {
		String className = StringUtil.getString(protocolMap.get(protocol));
		try {
			return (ProtocolBase) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new ProtocolNotFoundException(e);
		}
	}
}