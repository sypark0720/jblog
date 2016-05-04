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
<script type="text/javascript"
	src="/mysite3/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function(){	
	//logo파일 주소가 바뀌었을 때
	$("#btn-logoupload").on('click', function(){
		var form = new FormData(document.getElementById('uploadForm'));
		if (form == ""){ return; }
		$.ajax({
			url:"${pageContext.request.contextPath}/blog/uploadLogo/", 
			data: form,
			dataType: 'json',
			processData: false,
			contentType: false,
			type: "POST", 
			success: function(response){	
					console.log(response.data);
					$('#img-logoshow').attr('src', "${pageContext.request.contextPath}"+response.data);		
				},
			error: function(jqXHR,status,error){ 
				console.error(status+":"+error);
			}			
		});
	});
});

</script>

</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/blog-navigator.jsp"></jsp:include>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected">기본설정</li>
					<li><a href="${pageContext.request.contextPath}/blog/admin-cetegory">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath}/blog/admin-write">글작성</a></li>
				</ul>
				<form id = "uploadForm" enctype = "multipart/form-data" action="${pageContext.request.contextPath}/blog/admin-basic-submit" method="post">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img id="img-logoshow" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg"></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="logo-file"></td> 
			      		</tr>           				
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
				<button id = "btn-logoupload">로고 변경</button>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>