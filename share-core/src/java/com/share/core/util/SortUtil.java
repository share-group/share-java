package com.share.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用排序类
 * @param <V>
 */
public final class SortUtil<K, V> {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(SortUtil.class);
	/**
	 * 装载已经用过的规则 实现类似单例模式
	 */
	@SuppressWarnings("rawtypes")
	private static Map<String, SortUtil> sortMap = new HashMap<String, SortUtil>();
	/**
	 * 方法名数组
	 */
	private Method[] methodArr = null;
	/**
	 * 正序、反序
	 */
	private int[] typeArr = null;
	/**
	 * 排序方式
	 */
	private Order order = null;
	/**
	 * 根据什么排序
	 */
	private OrderBy orderBy = null;

	/**
	 * 构造函数 并保存该规则
	 * 
	 * @param clazz
	 * @param args
	 */
	private <T> SortUtil(Class<T> clazz, String... args) {
		methodArr = new Method[args.length];
		typeArr = new int[args.length];
		try {
			for (int i = 0; i < args.length; i++) {
				String key = args[i].split("#")[0];
				methodArr[i] = clazz.getMethod(key, new Class[] {});
				typeArr[i] = Integer.valueOf(args[i].split("#")[1]);
			}
		} catch (IllegalArgumentException | NoSuchMethodException | SecurityException e) {
			logger.error("", e);
		}
	}

	/**
	 * 构造函数
	 * @param order 排序规则
	 */
	private <T> SortUtil(Order order) {
		this.order = order;
	}

	/**
	 * 构造函数
	 * @param order 排序规则
	 * @param orderBy 根据排序
	 */
	private <T> SortUtil(Order order, OrderBy orderBy) {
		this.order = order;
		this.orderBy = orderBy;
	}

	/**
	 * 对象排序规则
	 */
	private final Comparator<Object> comparatorObject = new Comparator<Object>() {
		@Override
		public int compare(Object o1, Object o2) {
			int result = 0;
			try {
				for (int i = 0; i < methodArr.length; i++) {
					Object value1 = methodArr[i].invoke(o1);
					Object value2 = methodArr[i].invoke(o2);
					result = SortUtil.compare(value1, value2);
					if (result == 0) {
						continue;
					}
					return typeArr[i] == 1 ? result : -result;
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.error("", e);
			}
			return result;
		}
	};

	/**
	 * 非对象排序规则
	 */
	private final Comparator<Object> comparatorValue = new Comparator<Object>() {
		@Override
		public int compare(Object o1, Object o2) {
			int result = SortUtil.compare(o1, o2);
			return order.equals(Order.ASC) ? result : -result;
		}
	};

	/**
	 * map排序规则
	 */
	private final Comparator<Entry<K, V>> comparatorMap = new Comparator<Entry<K, V>>() {
		public int compare(Entry<K, V> a1, Entry<K, V> a2) {
			if (orderBy == null) {
				return 0;
			}
			int result = 0;
			switch (orderBy) {
			case KEY:
				result = SortUtil.compare(a1.getKey(), a2.getKey());
				break;
			case VALUE:
				result = SortUtil.compare(a1.getValue(), a2.getValue());
				break;
			default:
				return 0;
			}
			return order.equals(Order.ASC) ? result : -result;
		}
	};

	/**
	 * 比较两个值的大小
	 * @param o1
	 * @param o2
	 */
	private final static int compare(Object o1, Object o2) {
		int result = 0;
		if (o1 instanceof Integer) {
			result = Integer.parseInt(o1.toString()) >= Integer.parseInt(o2.toString()) ? 1 : -1;
		} else if (o1 instanceof Boolean) {
			boolean boolean1 = Boolean.parseBoolean(o1.toString());
			boolean boolean2 = Boolean.parseBoolean(o2.toString());
			if (boolean1 && !boolean2) {
				result = 1;
			} else if (!boolean1 && boolean2) {
				result = -1;
			}
		} else if (o1 instanceof Double) {
			result = Double.parseDouble(o1.toString()) >= Double.parseDouble(o2.toString()) ? 1 : -1;
		} else if (o1 instanceof Float) {
			result = Float.parseFloat(o1.toString()) >= Float.parseFloat(o2.toString()) ? 1 : -1;
		} else if (o1 instanceof Long) {
			result = Long.parseLong(o1.toString()) >= Long.parseLong(o2.toString()) ? 1 : -1;
		} else if (o1 instanceof Short) {
			result = Short.parseShort(o1.toString()) >= Short.parseShort(o2.toString()) ? 1 : -1;
		} else if (o1 instanceof Byte) {
			result = Byte.parseByte(o1.toString()) >= Byte.parseByte(o2.toString()) ? 1 : -1;
		} else {
			result = o1.toString().compareTo(o2.toString()) >= 0 ? 1 : -1;
		}
		return result;
	}

	/**
	 * 获取排序规则
	 * 
	 * @return SortUtil
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final static <T> SortUtil getSort(Class<T> clazz, String... args) {
		String key = clazz.getName() + Arrays.toString(args);
		SortUtil sort = sortMap.get(key);
		if (sort == null) {
			sort = new SortUtil(clazz, args);
			sortMap.put(key, sort);
		}
		return sort;
	}

	/**
	 * 获取排序规则
	 * @author ruan
	 * @param clazz
	 * @param order
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private final static <T> SortUtil getSort(Class<T> clazz, Order order) {
		String key = clazz.getName() + order;
		SortUtil sort = sortMap.get(key);
		if (sort == null) {
			sort = new SortUtil(order);
			sortMap.put(key, sort);
		}
		return sort;
	}

	/**
	 * 获取排序规则
	 * @param clazz	
	 * @param order
	 * @param orderBy
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private final static <K, V> SortUtil getSort(Map<K, V> map, Order order, OrderBy orderBy) {
		String key = map.toString() + order + orderBy;
		SortUtil sort = sortMap.get(key);
		if (sort == null) {
			sort = new SortUtil(order, orderBy);
			sortMap.put(key, sort);
		}
		return sort;
	}

	/**
	 * 对对象数组进行排序
	 * <pre>
	 * 首先会在容器中，根据class+规则去找。如果没有见则new 
	 * 调用方式 SortUtil.sort(list,"方法名#升序(1)/降序(-1)","..","..")
	 * 后面字符串参数：比如："getMark#1","getAge#-1"
	 * 表示先按照getMark的值按照升序排，如果相等再按照getAge的降序排
	 * 如果返回值是true类型，若按照true先排："isOnline#1" ,若按照false先排："isOnline#-1"
	 * </pre>
	 * 
	 * @param list
	 * @param args
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final static <T> void sort(List<T> list, String... args) {
		SortUtil sort = getSort(list.get(0).getClass(), args);
		Collections.sort(list, sort.comparatorObject);
	}

	/**
	 * 对非对象数组进行排序(多用于数值型)
	 * @author ruan
	 * @param list 数组
	 * @param order 排序方式
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final static <T> void sort(List<T> list, Order order) {
		SortUtil sort = getSort(list.get(0).getClass(), order);
		Collections.sort(list, sort.comparatorValue);
	}

	/**
	 * 给Map进行排序 对map的value进行排序(对象)
	 * 
	 * @param map 被排序的map
	 * @param args 排序方法条件：方法名x#1升序-1倒序, 方法名y#-1倒序
	 * @return List<T>
	 */
	public final static <K, V> List<V> sortMap(Map<K, V> map, String... args) {
		List<V> list = new ArrayList<V>();
		if (map == null || map.isEmpty()) {
			return list;
		}
		list.addAll(map.values());
		sort(list, args);
		return list;
	}

	/**
	 * 给Map进行排序 对map的value进行排序(非对象)
	 * 
	 * @param map 被排序的map
	 * @param order 排序方式
	 * @return List<T>
	 */
	public final static <K, V> List<V> sortMap(Map<K, V> map, Order order) {
		List<V> list = new ArrayList<V>();
		if (map == null || map.isEmpty()) {
			return list;
		}
		list.addAll(map.values());
		sort(list, order);
		return list;
	}

	/**
	 * 根据map的value排序(非对象)
	 * @param map 被排序的map
	 * @param order 排序方式
	 * @param orderBy 根据什么排序(key or value)	
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final static <K, V> Map<K, V> sortMap(Map<K, V> map, Order order, OrderBy orderBy) {
		ArrayList<Entry<K, V>> list = new ArrayList<Entry<K, V>>(map.entrySet());
		SortUtil sort = getSort(map, order, orderBy);
		Collections.sort(list, sort.comparatorMap);
		Map<K, V> newMap = new LinkedHashMap<K, V>();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			newMap.put(list.get(i).getKey(), list.get(i).getValue());
		}
		return newMap;
	}

	/**
	 * 给Map进行排序 对map的kay进行排序
	 * 
	 * @param map 被排序的map
	 * @param order 排序方式
	 * @return List<T>
	 */
	public final static <K, V> List<K> sortMapKey(Map<K, V> map, Order order) {
		List<K> list = new ArrayList<K>();
		if (map == null || map.isEmpty()) {
			return list;
		}
		for (K k : map.keySet()) {
			list.add(k);
		}
		sort(list, order);
		return list;
	}

	/**
	 * 排序方式
	 * 
	 * @author ruan
	 * 
	 */
	public enum Order {
		/**
		 * 升序
		 */
		ASC, /**
				 * 反序
				 */
		DESC;
	}

	/**
	 * 根据什么来排序
	 * @author ruan
	 * 
	 */
	public enum OrderBy {
		/**
		 * 根据key来排序
		 */
		KEY, /**
				 * 根据value来排序
				 */
		VALUE;
	}
}