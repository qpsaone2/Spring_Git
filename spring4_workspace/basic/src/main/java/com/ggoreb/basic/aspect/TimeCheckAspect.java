package com.ggoreb.basic.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class TimeCheckAspect {
	long start, end;
	
	@Before(value = "execution (* com.ggoreb.basic.controller.Thyme*.userList(..))")
	public void onBeforeHandler(JoinPoint joinPoint) {
		start = System.currentTimeMillis();
	}

	@After(value = "execution (* com.ggoreb.basic.controller.Thyme*.userList(..))")
	public void onAfterHandler(JoinPoint joinPoint) {
		end = System.currentTimeMillis();
		System.out.printf("%s msec", end - start);
	}
}