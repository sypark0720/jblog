package com.estsoft.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.jblog.vo.UserVo;

public class AuthUserBlogLogoutInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession httpSession = request.getSession();
		
			UserVo uservo = (UserVo) httpSession.getAttribute("authUser");
			String email =uservo.getEmail();
			httpSession.removeAttribute("authUser");
			httpSession.removeAttribute("authUserBlog");
			httpSession.invalidate();

		response.sendRedirect(request.getContextPath()+"/blog/"+email);	
		return false;
	}
	


}
