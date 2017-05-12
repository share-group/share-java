package com.share.test.error;

import com.share.core.interfaces.Error;

public enum DemoError implements Error {
	/**
	 * 示例错误1
	 */
	error1(1001001, "示例错误1"),
	/**
	 * 示例错误2
	 */
	error2(1001002, "示例错误2"),
	/**
	 * 示例错误3
	 */
	error3(1001003, "示例错误3");

	/**
	 * 错误码
	 */
	private int errorCode;
	/**
	 * 错误信息
	 */
	private String errorMsg;

	/**
	 * 构造函数
	 * @param errorCode 错误码
	 * @param errorMsg 错误信息
	 */
	private DemoError(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * 获取错误码
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * 获取错误信息
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
}