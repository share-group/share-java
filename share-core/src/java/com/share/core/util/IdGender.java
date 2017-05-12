package com.share.core.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * id生成器
 * @author ruan
 */
public class IdGender {
	/**
	* 原子自增
	*/
	private static AtomicInteger atomicInteger = new AtomicInteger(1);

	/**
	 * 生成唯一id
	 */
	public final static long genUniqueId() {
		int x = atomicInteger.getAndIncrement();
		if (x > 99) {
			atomicInteger.set(1);
		}
		// 这样做可以保证按照时间顺序
		return StringUtil.getLong(Time.date("yyyyMMddHHmmssSSS")) * 100L + x;
	}
}