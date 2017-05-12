package com.share.core.util;

import java.util.regex.Pattern;

/**
 * 正则表达式管理
 * @author ruan
 */
public class PatternUtil {
	/**
	 * 支付密码正则判断
	 */
	private final static Pattern paymentPasswordPattern = Pattern.compile("^\\d{6}$");
	/**
	 * 手机号码正则判断
	 */
	private final static Pattern numberPattern = Pattern.compile("^\\-?[0-9]+(\\.[0-9]+)?$");
	/**
	 * 标点符号正则判断
	 */
	private final static Pattern punctuationPattern = Pattern.compile("[\\pP\\pZ\\pS\\pM\\pC]", 2);

	private PatternUtil() {
	}

	public static Pattern getPaymentpasswordpattern() {
		return paymentPasswordPattern;
	}

	public static Pattern getNumberpattern() {
		return numberPattern;
	}

	public static Pattern getPunctuationpattern() {
		return punctuationPattern;
	}
}