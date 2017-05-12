package com.share.core.util;

/**
 * 变量检查类
 * 
 * @author ruan
 * 
 */
public final class Check {
	/**
	 * 判断一个字符串是否为一个数字
	 * 
	 * @author ruan
	 * @param value
	 * @return
	 */
	public final static boolean isNumber(Object value) {
		if (value == null) {
			return false;
		}
		return value.toString().trim().matches("\\-?[0-9]+(\\.[0-9]+)?");
	}

	/**
	 * 判断一个字符串是否为布尔类型
	 * 
	 * @author ruan
	 * @param value
	 * @return
	 */
	public final static boolean isBool(Object value) {
		if (value == null) {
			return false;
		}
		String v = value.toString().trim().toLowerCase();
		return v.equals("true") || v.equals("false");
	}

	/**
	 * 判断一个数字是否为合法端口
	 * @param port
	 * @return
	 */
	public final static boolean isPort(int port) {
		return port > 0 && port <= 65535;
	}

	/**
	 * 判断该字符串是否为ip
	 * @param ip
	 * @return
	 */
	public final static boolean isIp(String ip) {
		return ip.trim().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
	}

	/**
	 * 检查输入是否为英文
	 * @param english 英文字符串
	 */
	public final static boolean isEnglish(String english) {
		return english.matches("^[a-zA-Z]+$");
	}

	/**
	 * 检查是否输入为汉字
	 * @param chinese 中文字符串
	 */
	public final static boolean isChinese(String chinese) {
		return chinese.matches("^[\\u4E00-\\u9FA5]+$");
	}

	/**
	 * 邮箱地址合法性检查
	 * @param email 邮箱地址
	 */
	public final static boolean isEmail(String email) {
		return email.matches("^[\\w\\.]+[@]\\w+([.][a-zA-Z]+)+$");
	}
	
	/**
	 * 验证是否是网站地址
	 * @author ruan 
	 * @param url
	 */
	public final static boolean isURL(String url) {
		return url.matches("^(?i)(http(s)?://)?(\\w+\\.)?\\w+\\.(\\w+\\.)?\\w+/?(\\?)?.*$");
	}
}
