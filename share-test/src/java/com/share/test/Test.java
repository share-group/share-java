package com.share.test;

import com.share.core.util.StringUtil;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		System.err.println(StringUtil.toCamelCase("xxBbVv"));
		System.err.println(StringUtil.toSnakeCase("xxBbVv"));
	}
}