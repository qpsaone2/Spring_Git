package com.cloud.bbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SignInInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!request.getMethod().equalsIgnoreCase("GET")) {			
			return true;
		}else {
			HttpSession session=request.getSession();
			if(session.getAttribute("id")==null) {
				response.sendRedirect("login.bbs");
				return false;
			}
			return true;
		}	
	}

	

}
