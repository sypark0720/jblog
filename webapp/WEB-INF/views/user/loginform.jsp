<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
		<form class="login-form" method="post" action = "${pageContext.request.contextPath}/user/logincheck">
			<input type = "hidden" name="user_name" value = "default">
      		<label>Email</label> <input type="text" name="email" value = "${vo.email}"> 
      		<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('email') }">
					<strong style="color: red"> <c:set var="errorEmail"
							value="${errors.getFieldError( 'email' ).codes[0] }" /> <spring:message
							code="${errorEmail }"
							text="${errors.getFieldError( 'email' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>
      		<label>Password</label> <input type="password" name="password" value = "${vo.password}">
      		<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('password') }">
					<strong style="color: red"> <c:set var="errorPassword"
							value="${errors.getFieldError( 'password' ).codes[0] }" /> <spring:message
							code="${errorPassword}"
							text="${errors.getFieldError( 'password' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>
      		<br/>
      		<input type="submit" value="login">
		</form>
	</div>
</body>
</html>

