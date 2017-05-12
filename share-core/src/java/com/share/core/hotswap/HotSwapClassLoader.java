package com.share.core.hotswap;

/**
 * 热替换ClassLoader
 * @author ruan
 */
public final class HotSwapClassLoader extends ClassLoader {
	/**
	 * 字节码
	 */
	private byte[] byteCode;

	/**
	 * 构造函数
	 * @param byteCode
	 */
	public HotSwapClassLoader(byte[] byteCode) {
		this.byteCode = byteCode;
	}

	/**
	 * 重写findClass
	 */
	public Class<?> findClass(String name) {
		return defineClass(name, this.byteCode, 0, byteCode.length);
	}

	/**
	 * 重写findClass
	 */
	public Class<?> findClass(Class<?> clazz) {
		return findClass(clazz.getName());
	}
}