package com.estsoft.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.jblog.dao.BlogDao;
import com.estsoft.jblog.service.UserService;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.UserVo;


public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired private UserService service;
	@Autowired private BlogDao blogDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo userVo = new UserVo();
		userVo.setEmail(email);
		userVo.setPassword(password);

		//Login처리 -> authUser, authUserBlog session에 저장
		UserVo authUser = service.login(userVo);
		if(authUser == null){
			response.sendRedirect(request.getContextPath()+"/user/loginform");
			return false;
		}
		
		BlogVo authUserBlog = blogDao.getBlogByEmail(authUser.getEmail());
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		session.setAttribute("authUserBlog", authUserBlog);
		
		response.sendRedirect(request.getContextPath()+"/main");		
		
		return false;
	}
}
