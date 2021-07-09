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
	
	@Pointcut("execution(* com.cloud.bbs.controller.BBSController.writeForm(..))")
	public void signIn() {
		logger.info("SingInAspect의 writeForm 포인트컷 동작");
	}
	
	@Pointcut("execution(* com.cloud.bbs.controller.BBSController.write(..))")
	public void checkMethodTime() {
		logger.info("SingInAspect의 write 포인트컷 동작");
	}
	
	@Around("signIn()")
//	@Around("execution(* com.cloud.bbs.controller.BBSController.writeForm(..))")
	public Object signInCheck(ProceedingJoinPoint pjt) throws Throwable{		
		HttpSession session =null;
		for(Object obj : pjt.getArgs()) {
			if(obj instanceof HttpSession) {
				session= (HttpSession)obj;
			}
		}
		
		if(session.getAttribute("id")==null) {
			return "login";
		}
		
		Object result=pjt.proceed(); //Controller의 메소드 실행
		//Controller의 메소드가 실행되고 난 이후에 코드 
		return result;
		
	}
	
	@Around("checkMethodTime()")
	public Object methodTimeCheck(ProceedingJoinPoint pjt) throws Throwable{		
		long  startTime= System.currentTimeMillis();
		
		Object result=pjt.proceed(); 
		long methodExecutionTime = System.currentTimeMillis()-startTime;
		logger.info("메소드 동작 시간은 : " +  methodExecutionTime);
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
