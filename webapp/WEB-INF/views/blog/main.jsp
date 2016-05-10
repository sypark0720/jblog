<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/blog-navigator.jsp"></jsp:include>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4> ${postList[0].title}</h4>
					<p>
						${postList[0].content}
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items ="${postList}" var="vo" varStatus="status">	
						<li><a href="">${vo.title}</a> <span>${vo.regdate}</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/${UserBlog.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>Category</h2>
			<ul>
				<c:forEach items ="${categoryList}" var="vo" varStatus="status">	
					<li class ="categoryButton" value = "${vo.category_id}">
						<a href="${pageContext.request.contextPath}/blog/${authUser.email}?sel=${status.index}">${vo.category_name}</a>
					</li>
				</c:forEach>
			</ul>
		</div>

		<div id="footer">
			<p>
				<strong>${UserBlog.title}</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>