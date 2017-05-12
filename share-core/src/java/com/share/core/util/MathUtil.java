package com.share.core.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 数学工具类
 * 
 * @author ruan
 * 
 */
public final class MathUtil {
	/**
	 * numberFormat
	 */
	private final static NumberFormat numberFormat = NumberFormat.getInstance();

	/**
	 * n的阶乘(n!)
	 * 
	 * @author ruan
	 * @param n
	 * @return
	 */
	public final static long factorial(long n) {
		if (n <= 0) {
			return 0;
		}
		long result = 1;
		while (n > 0) {
			result *= n;
			n -= 1;
		}
		return result;
	}

	/**
	 * 数字格式化
	 * @author ruan
	 * @param number 任意数字
	 * @return
	 */
	public final static String numberFormat(long number) {
		return numberFormat.format(number);
	}

	/**
	 * 以base为底求x的对数
	 * @author ruan
	 * @param base 底数
	 * @param x 对数
	 * @return
	 */
	public final static double log(double base, double x) {
		return Math.log(x) / Math.log(base);
	}

	/**
	 * 把double按照四舍五入的方式保留n位小数
	 * @author ruan 
	 * @param number
	 * @param n
	 */
	public final static double round(double number, int n) {
		StringBuilder str = new StringBuilder("0.");
		for (int i = 0; i < n; i++) {
			str.append("0");
		}
		return StringUtil.getDouble(new DecimalFormat(str.toString()).format(number / 1.0));
	}
}