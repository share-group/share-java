package com.share.core.interfaces;

/**
 * 错误码
 * <li>错误码共7位：axxxyyy</li>
 * <li>第一位：1.系统级错误 2.应用级别错误</li>
 * <li>第二、三、四位：模块编号，从001开始</li>
 * <li>最后三位：错误编号，从001开始</li>
 * <li>例如：1001001、2001001</li>
 */
public interface Error {
	/**
	 * 获取错误码
	 */
	public int getErrorCode();

	/**
	 * 获取错误信息
	 */
	public String getErrorMsg();
}