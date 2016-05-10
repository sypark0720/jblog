package com.estsoft.jblog.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.estsoft.jblog.annotation.AuthUserBlog;
import com.estsoft.jblog.vo.BlogVo;

public class AuthUserBlogHandlerMethodArgumentResolver implements
HandlerMethodArgumentResolver {
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		//1. Check parameter annotation
		AuthUserBlog authUserBlog = parameter.getParameterAnnotation(AuthUserBlog.class); 
		if(authUserBlog == null){
			return false;
		}
		
		//2. Check parameter type(BlogVo)
		if (parameter.getParameterType().equals(BlogVo.class) == false){
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
		
		//Bring authUserBlog from session
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null){
			return WebArgumentResolver.UNRESOLVED;
		}
		
		return session.getAttribute("authUserBlog");
	}
}
