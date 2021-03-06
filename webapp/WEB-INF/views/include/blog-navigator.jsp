<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.estsoft.jblog.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div id="header">		
		<h1>${UserBlog.title}</h1>
			<ul>
				<c:choose>
					<c:when test="${authUserBlog != null}">					
						<li><a href="${pageContext.request.contextPath}/blog/logout">logout</a></li>
						<li><a href="${pageContext.request.contextPath}/blog/${authUser.email}/admin-basic">blog-management</a></li>
					</c:when>
					<c:otherwise>		
						<li><a href="${pageContext.request.contextPath}/user/loginform">login</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
	</div>
		
		