package com.share.core.aspect;

import org.springframework.stereotype.Component;

@Component
public class AspectHaHaHa {
	@AspectDemoAnnotaion
	public int test1() {
		System.err.println("test1");
		return 1;
	}
}
