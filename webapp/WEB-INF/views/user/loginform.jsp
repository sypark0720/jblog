<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/navigator.jsp"></jsp:include>
	<div class="center-content">		
		<form class="login-form" method="post" action = "${pageContext.request.contextPath}/user/login">
      		<label>이메일</label> <input type="text" name="email">
      		<label>패스워드</label> <input type="password" name="password">
      		<br/>
      		<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>

