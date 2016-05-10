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

<script type = "text/javascript" src = "/mysite3/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type = "text/javascript">

var fetchList = function(){
	$(".categoryListRow").remove();
	$.ajax({
		url:"/jblog/blog/${authUser.email}/admin-category-ajaxlist",
		type:"get",
		dataType: "json",
		data:"",
		success: function(response){
			if(response.result != "success"){
				return;
			}
			$.each(response.data, function(index, vo){
				renderHtml(vo, index);
			});			
			},
		error: function(xhr, status, error){		
			console.error(status+":"+error);	
		}
	});
}

var renderHtml = function(vo, index){
	var html="<tr class = 'categoryListRow'>"
	+"<td>"+(index+1)+"</td>"
	+"<td>"+vo.category_name+"</td>"
	+"<td>"+vo.postcount+"</td>"
	+"<td>"+vo.description+"</td>"
	+"<td>"
		+"<form>"
			+"<button class ='deleteButton' type='button' value="+vo.category_id+">"
			+"<img style = 'cursor:pointer' src='${pageContext.request.contextPath}/assets/images/delete.jpg'>"
			+"</button></form></td></tr>";		
	$("#category-list").append(html);
}

$(function(){
		fetchList();	

		//1. insert하면 바로 붙음.
		$("#admin-cat-add").submit(function(event){
			event.preventDefault();			
			var name = $("#category-name").val();
			var description = $( "#category-description").val();
			this.reset();

			$.ajax({
				url: "/jblog/blog/${authUser.email}/admin-category-add",
				type: "post",
				dataType: "json",
				data: "blog_id=${authUserBlog.blog_id}&name="+name+"&description="+description,
				success: function(response){
					$( "#category-list" ).append(renderHtml(response.data, response.listsize-1 ));	
				},
				error: function(xhr, status, error){
						console.error("에러"+status+":"+error);	
					}
				});				
			});

		//2. deleteButton 클릭 시 삭제
		$(document).on('click', ".deleteButton", function(){
			var category_id = $(this).attr("value");
			
			$.ajax({
				url: "/jblog/blog/${authUser.email}/admin-category-delete",
				type: "post",
				dataType: "json",
				data: "category_id="+category_id,
				
				success: function(response){
					fetchList();					
				},
				error: function(xhr, status, error){
						console.error("에러"+status+":"+error);	
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
					<li><a href="${pageContext.request.contextPath}/blog/${authUser.email}/admin-basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/blog/${authUser.email}/admin-write">글작성</a></li>
				</ul>
		      	<table id = "category-list" class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>					
				</table>
				    	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="admin-cat-add">
			      	<table>
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input id = "category-name" type="text" name="name" ></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input id = "category-description" type="text" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
		      	</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>${authUserBlog.title}</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>