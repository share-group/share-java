package com.share.core.memory;

import java.util.concurrent.ConcurrentHashMap;

import com.share.core.interfaces.DSuper;

/**
 * DMap
 */
public class DMap extends ConcurrentHashMap<Object, Object> {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3323062999854404799L;

	/**
	 * 获取DMap数据
	 * 
	 * @param key
	 * @return DMap
	 */
	public DMap getMap(Object key) {
		return (DMap) super.get(key);
	}

	/**
	 * 根据类名获取整个DMap
	 * @author ruan
	 * @param clazz
	 * @return
	 */
	public DMap getMap(Class<?> clazz) {
		return (DMap) super.get(clazz);
	}

	/**
	 * 把DSuper数据写入DMap
	 * 
	 * @param key
	 * @param value
	 * @return DSuper
	 */
	public DSuper put(Object key, DSuper value) {
		if (value == null) {
			return null;
		}
		super.put(key, value);
		return value;
	}

	/**
	 * 把DMap数据写入DMap
	 * 
	 * @param key
	 * @param value
	 * @return DMap
	 */
	public DMap putMap(Object key, DMap value) {
		if (value == null) {
			return null;
		}
		super.put(key, value);
		return value;
	}
}