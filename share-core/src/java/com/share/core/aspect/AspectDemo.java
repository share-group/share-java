package com.share.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectDemo {
	@AfterReturning("execution(int com.share.core.aspect.AspectHaHaHa.test1())")
	public void testReturn(JoinPoint pjp) {
		System.err.println(pjp.getTarget());
		System.err.println("AfterReturning ...");
	}
}
