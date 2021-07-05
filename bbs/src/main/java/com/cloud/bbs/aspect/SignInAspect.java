package com.cloud.bbs.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SignInAspect {
	private static final Logger logger = LoggerFactory.getLogger(SignInAspect.class);
	
	@Pointcut("execution(* com.cloud.bbs.controller.BBSController.writeForm(..))")  //Pointcut �޼ҵ�� ��4�۾��� log��ų� �޼ҵ� ��ġ�� �˷��ִ� ��� 
	public void signIn() {
		logger.info("SinInAspect�� writeForm ����Ʈ�� ����");
	}
	
	@Pointcut("execution(* com.cloud.bbs.controller.BBSController.write(..))")  //Pointcut �޼ҵ�� ��4�۾��� log��ų� �޼ҵ� ��ġ�� �˷��ִ� ��� 
	public void checkMethodTime() {
		logger.info("SinInAspect�� write ����Ʈ�� ����");
	}
	
	@Around("signIn()" ) //signIn�� pointcut�� �޼ҵ� �̸�, pointcut�� ��ġ�� �޼ҵ尡 �����ϱ� ��/�Ŀ� �����Ұ��� �˷��� 
	public Object signInCheck(ProceedingJoinPoint pjt) throws Throwable {
		//���⿡ �ڵ尡 ��.. �� �ڵ尡 �����̽� ��
		HttpSession session = null ; 
		for(Object obj : pjt.getArgs()) {
			if(obj instanceof HttpSession) {
				session = (HttpSession) obj;
			}
		}
		
		if(session.getAttribute("id") ==null) {
			return "login";
		}
		
		Object result = pjt.proceed(); //Controller�� �޼ҵ� ���� 
		//Controller�� �޼ҵ尡 ����ǰ� �� ���Ŀ� �ڵ�
		return result;
		
	}
	
	@Around("checkMethodTime()" ) //signIn�� pointcut�� �޼ҵ� �̸�, pointcut�� ��ġ�� �޼ҵ尡 �����ϱ� ��/�Ŀ� �����Ұ��� �˷��� 
	public Object methodTimeCheck(ProceedingJoinPoint pjt) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		
		Object result = pjt.proceed();
		long methodExcutionTime=System.currentTimeMillis()-startTime;
		logger.info("�޼ҵ� ���� �ð��� :" + methodExcutionTime);
		return result;
		
	
	}
}
