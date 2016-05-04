package com.estsoft.jblog.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.estsoft.jblog.annotation.AuthUser;
import com.estsoft.jblog.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {//모든 파라미터가 다 온다.
		//parameter에 annotation이 있고 그게 UserVo여야 한다.
		//BoardController.write() 참조
		//public String write(@AuthUser UserVo authUser, HttpSession session)
		
		//1. Check parameter annotation
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class); //annotation
		if(authUser == null){
			return false;
		}
		
		//2. Check parameter type(UserVo)
		if (parameter.getParameterType().equals(UserVo.class) == false){ //instanceof 못 쓰는 이유: parameter.getParameterType()가 class라서 객체가 아니다.
			return false;
		}
		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mav, NativeWebRequest webRequest, //WAS말고 다른 데서도 쓰게.(http아니더라도 쓸 수 잇도록)
			WebDataBinderFactory binderFactory) throws Exception {
		if(this.supportsParameter(parameter)==false){
			return WebArgumentResolver.UNRESOLVED;
		}
		
		//session에서 authUser가져오기
		//우리는 httpServletRequest가 필요하다.
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null){
			return WebArgumentResolver.UNRESOLVED;
		}
		
		return session.getAttribute("authUser"); //(return 값은 Object)
	}



}
