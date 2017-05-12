package com.share.core.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

/**
 * 概率
 * @author ruan 2013-8-1
 */
public final class RandomUtil {
	private final static Random random = new Random();
	private final static char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	private RandomUtil() {
	}

	/**
	 * 获取随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static final int rand(int min, int max) {
		int tmp = max - min;
		if (tmp < 0) {
			return -1;
		} else if (tmp == 0) {
			return min;
		} else {
			return random.nextInt(tmp + 1) + min;
		}
	}

	/**
	 * 获取随机数(double)
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param round
	 *            保留多少位小数（不能太大）
	 * @return Double, null: when max < min
	 */
	public static final double rand(double min, double max, int round) {
		double dRound = Math.pow(10, round);
		int iMin = (int) (min * dRound);
		int iMax = (int) (max * dRound);
		int r = rand(iMin, iMax);
		if (r <= -1) {
			return r;
		}
		return r / dRound;
	}

	/**
	 * 获取概率事件，几率最多支持3位小数
	 * 
	 * @param map 参数举例: Map(1=>20.1, 2=>29.9, 3=>50), 则20.1%几率返回1, 29.9%返回2, 50%返回3
	 * @return 返回键值
	 */
	public static final <T> T getRand(Map<T, Double> map) {
		int multiple = 1000; // 放大位数

		// 求和
		int sum = 0;
		Iterator<Entry<T, Double>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<T, Double> entry = iter.next();
			Double v = entry.getValue();
			sum += v * multiple;
		}

		// 产生0-sum的整数随机
		int luckNum = random.nextInt(sum) + 1;
		int tmp = 0;
		iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<T, Double> entry = iter.next();
			Double v = entry.getValue();
			tmp += v * multiple;
			if (luckNum <= tmp) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * 获取概率事件，几率最多支持3位小数
	 * 
	 * @param map 参数举例: HashMap(1=>20.1, 2=>29.9, 3=>50), 则20.1%几率返回1, 29.9%返回2, 50%返回3
	 * @return 返回键值
	 */
	public static final <T> T getRand(Set<T> map) {
		int multiple = 1000; // 放大位数
		// 求和
		int sum = map.size() * multiple;

		// 产生0-sum的整数随机
		int luckNum = random.nextInt(sum) + 1;
		int tmp = 0;
		for (T one : map) {
			tmp += multiple;
			if (luckNum <= tmp) {
				return one;
			}
		}
		return null;
	}

	/**
	 * 由概率随机是否触发
	 * 
	 * @param chance 0.00 - 100.00
	 * @return boolean
	 */
	public static final boolean isLuck(double chance) {
		return RandomUtil.rand(1, 10000) <= ((int) chance * 100);
	}

	/**
	 * 获取两个数之间的随机数，得出的值符合正态分布
	 * 
	 * @param min  最小值
	 * @param max 最大值
	 * @param factor 调整曲线参数
	 * @return
	 */
	public static final int gaussianRand(int min, int max, int factor) {
		if (min > max) {
			return 0;
		}
		int middle = (int) Math.ceil((min + max) / 2);
		int in = max - middle;
		factor = factor < 0 ? 0 : factor;
		LinkedHashMap<Integer, Double> map = new LinkedHashMap<Integer, Double>();
		for (int i = min; i <= max; i++) {
			if (i == middle) {
				map.put(i, (double) in);
			} else {
				double tmp = Math.abs(in - Math.abs(middle - i));
				tmp = tmp <= 0 ? 1 : tmp;
				tmp = factor > 0 ? tmp * (1 + factor / 100.0) : tmp;
				tmp = tmp > in ? in : tmp;
				map.put(i, tmp);
			}
		}
		return RandomUtil.getRand(map);
	}

	/**
	 * 获取两个数之间的随机数，得出的值符合正态分布
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	public static final int gaussianRand(int min, int max) {
		return gaussianRand(min, max, 0);
	}

	/**
	 * 生成指定长度的随机字符串
	 * @param length 字符串长度
	 */
	public static final String string(int length) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < length; i++) {
			str.append(chars[rand(0, chars.length - 1)]);
		}
		return str.toString();
	}
}
