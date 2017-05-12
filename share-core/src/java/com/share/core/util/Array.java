package com.share.core.util;

/**
 * 数组工具
 * 
 * @author ruan 2013-8-1
 */
public final class Array {
	private Array() {
	}

	/**
	 * 把数组元素组合为一个字符串
	 * 
	 * @author ruan 2013-8-1
	 * @param array
	 * @param separator
	 */
	public final static <T> String implode(T[] array, String separator) {
		if (array == null || array.length <= 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (T t : array) {
			sb.append(t);
			sb.append(separator);
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	/**
	 * 把字符串分割为数组
	 * 
	 * @author ruan 2013-8-1
	 * @param str
	 * @param separator
	 * @return
	 */
	public final static Object[] explode(String str, String separator) {
		String[] arr = str.split(separator);
		Object[] t = new Object[arr.length];
		int i = 0;
		for (String s : arr) {
			t[i] = s;
			i += 1;
		}
		return t;
	}
}
