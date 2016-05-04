<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
	<script type="text/javascript"
	src="/mysite3/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function(){	
	//email 중복 체크 안했을 때
	$("#join-form").submit(function(){
		if($("#img-checkemail").is(":visible")==false){
			alert("이메일  중복체크를 해야 합니다.");
			return false;
			}		
		if($("#agree-prov").is(":checked")==false){
			alert("약관 동의를 해야 합니다.");
			return false;
		} 
		return true; });

	$("#btn-checkemail").click(function(){		
		var email = $("#blog-id").val();
		if(email == ""){ return;}		
		$.ajax({
			url:"${pageContext.request.contextPath}/user/checkemail?email="+email, 
			type: "get", 
			dataType: "json", 
			data:"", 
			success: function(response){
				if(response.result !="success"){					
					return;
				}
				if(response.data == false){
					alert("이미 존재하는 이메일입니다. 다른 이메일을 사용해 주세요.");
					$("#blog-id").val("").focus();
					return;
				}
				$("#btn-checkemail").hide();
				$("#img-checkemail").show();				
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
	<jsp:include page="/WEB-INF/views/include/navigator.jsp"></jsp:include>
	<div class="center-content">

		<form class="join-form" id="join-form" method="post"
			action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label> <input id="name"
				name="user_name" type="text" value="${vo.user_name}"> <br />
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('user_name') }">
					<strong style="color: red"> <c:set var="errorUser_name"
							value="${errors.getFieldError( 'user_name' ).codes[0] }" /> <spring:message
							code="${errorUser_name}"
							text="${errors.getFieldError( 'user_name' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>

			<label class="block-label" for="blog-id">Email</label> 
			<input id="blog-id" name="email" type="text" value = "${vo.email}"> 
			<input id="btn-checkemail" type="button" value="email 중복체크"> 
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<br />
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('email') }">
					<strong style="color: red"> <c:set var="errorEmail"
							value="${errors.getFieldError( 'email' ).codes[0] }" /> <spring:message
							code="${errorEmail }"
							text="${errors.getFieldError( 'email' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>

			<label class="block-label" for="password">패스워드</label> <input
				id="password" name="password" type="password" /> <br />
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('password') }">
					<strong style="color: red"> <c:set var="errorPassword"
							value="${errors.getFieldError( 'password' ).codes[0] }" /> <spring:message
							code="${errorPassword}"
							text="${errors.getFieldError( 'password' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="false">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
