<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.estsoft.jblog.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="navigator" class="center-content">
	<h1 class="logo">JBlog</h1>
	<ul class="menu">
		<c:choose>
			<c:when test="${authUser == null}">
				<li><a href="${pageContext.request.contextPath}/user/loginform">login</a></li>
				<li><a href="${pageContext.request.contextPath}/user/joinform">sign-up</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/blog/${authUser.email}?sel=0">내블로그</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>



