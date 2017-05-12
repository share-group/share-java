package com.share.core.memory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.share.core.util.StringUtil;

/**
 * 内存数据映射
 */
@Component
public final class Memory extends DMap {
	private static final long serialVersionUID = -623055920767054007L;
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	private Memory() {
	}

	/**
	 * 根据类名和条件获取数据
	 * @author ruan
	 * @param clazz 数据类名
	 * @param keys 无限条件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(T clazz, Object... keys) {
		DMap map = this.getMap(clazz);
		if (map == null) {
			return null;
		}
		for (Object key : keys) {
			Object data = map.get(key);
			if (data instanceof DMap) {
				map = (DMap) data;
			} else {
				return (T) map.get(key);
			}
		}
		return null;
	}

	/**
	 * 根据类名和条件获取整个DMap
	 * @author ruan
	 * @param clazz 数据类名
	 * @param keys 无限条件
	 * @return
	 */
	public DMap getMap(Class<?> clazz, Object... keys) {
		DMap map = this.getMap(clazz);
		if (map == null) {
			return null;
		}
		for (Object key : keys) {
			Object data = map.get(key);
			if (data instanceof DMap) {
				map = (DMap) data;
			} else {
				return null;
			}
		}
		return map;
	}

	/**
	 * 把数据写入DMap
	 * @author ruan
	 * @param clazz 实体类
	 * @param dataList 数据
	 * @param KeysDefine 分层key定义
	 */
	public void putMap(Class<?> clazz, List<List<String>> dataList, String... KeysDefine) {
		synchronized (this) {
			DMap map = this.getMap(clazz);
			if (map == null) {
				map = new DMap();
				this.put(clazz, map);
			}
			try {
				for (List<String> data : dataList) {
					Object t = clazz.newInstance();
					Class<?> cla = t.getClass();
					int i = 0;
					for (Field field : cla.getDeclaredFields()) {
						Class<?> fieldType = field.getType();
						Method method = cla.getMethod(getSetter(field.getName()), fieldType);
						Object value = null;
						if (fieldType == byte.class) {
							value = StringUtil.getByte(data.get(i++));
						} else if (fieldType == short.class) {
							value = StringUtil.getShort(data.get(i++));
						} else if (fieldType == int.class) {
							value = StringUtil.getInt(data.get(i++));
						} else if (fieldType == long.class) {
							value = StringUtil.getLong(data.get(i++));
						} else if (fieldType == boolean.class) {
							value = StringUtil.getBoolean(data.get(i++));
						} else if (fieldType == String.class) {
							value = StringUtil.getString(data.get(i++));
						} else {
							value = StringUtil.getString(data.get(i++));
						}
						method.invoke(t, value);
					}
					if (KeysDefine.length == 1) {
						// 如果只是一层
						Object subKeyValue = clazz.getMethod(getGetter(KeysDefine[0]), new Class[] {}).invoke(t, new Object[] {});
						map.put(subKeyValue, t);
					} else {
						// 多层情况的处理
						DMap tmpMap = null;
						int iTmp = 0;
						for (String subKey : KeysDefine) {
							Object subKeyValue = clazz.getMethod(getGetter(subKey), new Class[] {}).invoke(t, new Object[] {});
							iTmp += 1;
							if (iTmp == 1) {
								// 第一层
								tmpMap = map.getMap(subKeyValue);
								if (tmpMap == null) {
									tmpMap = new DMap();
									map.putMap(subKeyValue, tmpMap);
								}
							} else if (iTmp < KeysDefine.length) {
								// 中间层
								DMap tmp = tmpMap.getMap(subKeyValue);
								if (tmp == null) {
									tmp = new DMap();
									tmpMap.putMap(subKeyValue, tmp);
								}
								tmpMap = tmp;
							} else {
								// 最后一层
								tmpMap.put(subKeyValue, t);
							}
						}
					}
				}
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | InstantiationException e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 根据属性名，获取Getter方法名
	 * @param field
	 * @return
	 */
	private String getGetter(String field) {
		return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
	}

	/**
	 * 根据属性名，获取setter方法名
	 * @param field
	 * @return
	 */
	private String getSetter(String field) {
		return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
	}
}