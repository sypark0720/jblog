package com.estsoft.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.jblog.annotation.Auth;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response, 
			Object handler) 
			throws Exception {
		
		if(handler instanceof HandlerMethod == false){ //handlermethod 는 컨트롤러의 method 정보를 담아놓은 객체
			return true;
		}
		Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
		//auth가 붙어잇으면 auth가 나온다.
		//원래 type이 handlermethod가 아닌 경우도 있다 --> handlerDefaultServlet(없은 url이나 이미지 url에)--> 20번째 줄에 처리
		
		
		//@Auth가 없는 컨트롤러 핸들러
		//접근 제어가 필요 없다.
		if(auth == null){
			return true;
		}
		
		//@Auth가 있는 컴트롤러 핸들러 --> 접근 제어(인증이 필요함)
		HttpSession session = request.getSession();
		if(session == null){
			response.sendRedirect(request.getContextPath()+"/user/loginform");
			return false;
		}
			
		//인증된 사용자
		return true;
	}
	
}
