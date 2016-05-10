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
	<jsp:include page="/WEB-INF/views/include/navigator.jsp"></jsp:include>
	<div class="center-content">
		<form class="search-form">
			<fieldset>
				<input type="text" name="keyword" />
				<input type="submit" value="search" />
			</fieldset>
			<fieldset>
				<input type="radio" name="which" value="blog-title"> <label>blog name</label>
				<input type="radio" name="which" value="tag"> <label>tag</label>
				<input type="radio" name="which" value="blog-user"> <label>blogger</label>
			</fieldset>
		</form>
	</div>
</body>
</html>