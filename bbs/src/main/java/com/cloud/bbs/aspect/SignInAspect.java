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
	
	@Pointcut("execution(* com.cloud.bbs.controller.BBSController.writeForm(..))")  //Pointcut 메소드는 동4작안함 log찍거나 메소드 위치를 알려주는 기능 
	public void signIn() {
		logger.info("SinInAspect의 writeForm 포인트컷 동작");
	}
	
	@Pointcut("execution(* com.cloud.bbs.controller.BBSController.write(..))")  //Pointcut 메소드는 동4작안함 log찍거나 메소드 위치를 알려주는 기능 
	public void checkMethodTime() {
		logger.info("SinInAspect의 write 포인트컷 동작");
	}
	
	@Around("signIn()" ) //signIn은 pointcut의 메소드 이름, pointcut의 위치의 메소드가 동작하기 전/후에 동작할것을 알려줌 
	public Object signInCheck(ProceedingJoinPoint pjt) throws Throwable {
		//여기에 코드가 옴.. 이 코드가 어드바이스 임
		HttpSession session = null ; 
		for(Object obj : pjt.getArgs()) {
			if(obj instanceof HttpSession) {
				session = (HttpSession) obj;
			}
		}
		
		if(session.getAttribute("id") ==null) {
			return "login";
		}
		
		Object result = pjt.proceed(); //Controller의 메소드 실행 
		//Controller의 메소드가 실행되고 난 이후에 코드
		return result;
		
	}
	
	@Around("checkMethodTime()" ) //signIn은 pointcut의 메소드 이름, pointcut의 위치의 메소드가 동작하기 전/후에 동작할것을 알려줌 
	public Object methodTimeCheck(ProceedingJoinPoint pjt) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		
		Object result = pjt.proceed();
		long methodExcutionTime=System.currentTimeMillis()-startTime;
		logger.info("메소드 동작 시간은 :" + methodExcutionTime);
		return result;
		
	
	}
}
