package com.share.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化工具类
 */
public class SerialUtil {
	private final static Logger logger = LoggerFactory.getLogger(SerialUtil.class);
	//private final static MessagePack pack = new MessagePack();

	/**
	 * 对象序列化成字节
	 *
	 * @param object java对象
	 * @return 序列化后的数组
	 */
	public static <T> byte[] toBytes(T t) {
		//		try {
		//			return pack.write(t);
		//		} catch (Exception e) {
		//			logger.error("", e);
		//		}
		return null;
	}

	/**	
	 * 反序列化
	 *
	 * @param bytes 已经被序列化的数组
	 * @param klass 需要反序列化成的类型
	 * @return 反序列化后的实例
	 */
	public static <T> T fromBytes(byte[] bytes, Class<T> klass) {
		//		if (bytes == null || bytes.length <= 0) {
		//			return null;
		//		}
		//		try {
		//			return pack.read(bytes, klass);
		//		} catch (Exception e) {
		//			logger.error("", e);
		//		}
		return null;
	}
}