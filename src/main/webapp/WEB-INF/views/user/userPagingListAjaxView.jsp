<%@page import="kr.or.ddit.user.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp - basicLib</title>
<%@include file="/WEB-INF/views/commonJsp/basicLib.jsp" %>
<script>
	$(function(){
		
		getUserHtmlList(1, 10);
		
		$('#userListTbody').on("click", ".userTr", function(){
			// 
			var tdText = $('td:eq(1)', this).text();
			console.log("tdText  : " + tdText);
			
			// input 태그에 저장된 값 확인
			var inputValue = $(this).find("input").val();
			console.log("inputValue : " + inputValue)
			
			// data 속성으로 값 가져오기
			// ***** data 속섬명은 소문자로 치환된다.
			// data-userId --> $(this).data("userid");
			// ***** 대소문자 주의
			var dataValue = $(this).data("userid");
			console.log("dataValue : " + dataValue);
			
			// input 태그에 값 설정
			$('#userId').val(dataValue);
			
			// form 태그 이용 전송
			console.log("serialize : " + $('#frm').serialize());
			
			$('#frm').submit();
		})
	})
	
	//ajax 응답을 html로 받는다.(html javascript로 생성하는 작업을 줄인다.)
	function getUserHtmlList(page,pageSize){
		$.ajax({
			url : "${cp}/user/userPagingListHtmlAjax",
			data : "page=" + page + "&pageSize=" + pageSize,
			success : function(data){
				var html = data.split("#####");
				$("#userListTbody").html(html[0]);
				$(".pagination").html(html[1]);
			}
		})
	}
	
	function getUserListRequestBody(page, pageSize){
		var param = {};
		param.page = page;
		param.pageSize = pageSize;
		console.log("param : " + param);
		$.ajax({
			url : "${cp}/user/userPagingListAjaxRequestBody",
			contentType : "application/json",
			dataType : "json",
			method : "post",
			data : JSON.stringify(param),
			success : function(data){
				console.log(data)
				createUserListTbody(data.userList);		//userList.html 생성
				createPagination(data.pageVo, data.paginationSize);	//페이지 네이션 html 생성
			}
		})
	}
	
	//ajax call을 통해 page, pageSize하는 사용자
	//데이터를 가져온다.
	function getUserList(page,pageSize){
		$.ajax({
			url : "${cp}/user/userPagingListAjax",
			data : "page=" + page + "&pageSize=" + pageSize,
			success : function(data){
				createUserListTbody(data.userList);		//userList.html 생성
				createPagination(data.pageVo, data.paginationSize);	//페이지 네이션 html 생성
			}
		})
	}
	
	function createPagination(pageVo, paginationSize){
		var html = "";
		   if(pageVo.page == 1){
		      html += "<li class='disabled'>";
		      html += "   <span aria-hidden='true'>&laquo;</span>"
		      html += "</li>"
		   }else{
		      html += "<li>";
		      html += "   <a href='javascript:getUserList("+(pageVo.page-1)+","+pageVo.pageSize+")' aria-label='Previous'>";
		      html += "      <span aria-hidden='true'>&laquo;</span>"
		      html += "   </a>";
		      html += "</li>";
		   }
		   
		   for(var page = 1; page<= paginationSize; page++){
		      if(page == pageVo.page){
		         html += "<li class='active'><span>"+page+"</span></li>";
		      }else{
		         html += "<li>"
		         html += "    <a href='javascript:getUserList("+page+","+pageVo.pageSize+")'>" + page + "</a>";
		         html += "</li>";
		      }
		   }
		   
		   if(pageVo.page == paginationSize){
		      html += "<li class='disabled'>";
		      html += "   <span aria-hidden='true'>&raquo;</span>"
		      html += "</li>"
		   }else{
		      html += "<li>";
		      html += "   <a href='javascript:getUserList("+(pageVo.page+1)+","+pageVo.pageSize+")' aria-label='Next'>";
		      html += "      <span aria-hidden='true'>&raquo;</span>"
		      html += "   </a>";
		      html += "</li>";
		   }
		   
		   $(".pagination").html(html);
	}
	
	
	function createUserListTbody(userList){
		//사용자 데이터 list
		//기존 데이터 제거
		$("#userListTbody").empty();
		var html = "";
		userList.forEach(function(user, idx){
			html += "<tr>";
			html += "	<td>" + user.userId + "</td>";
			html += "	<td>" + user.userNm + "</td>";
			html += "	<td>" + user.alias + "</td>";
			html += "	<td>" + user.reg_dt + "</td>";
			html += "</tr>";
		})
		$("#userListTbody").html(html);
	}
</script>
</head>

<body>
<form id="frm" action="${cp}/user/user" method="get">
	<input type="hidden" id="userId" name="userId">
</form>

<%@ include file="/WEB-INF/views/commonJsp/header.jsp" %>
<div class="container-fluid">
		<div class="row">
			
<div class="col-sm-3 col-md-2 sidebar">
	<%@ include file="/WEB-INF/views/commonJsp/left.jsp" %>
</div>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>사용자 아이디</th>
					<th>사용자 이름</th>
					<th>사용자 별명</th>
					<th>등록일시</th>
				</tr>
				<tbody id="userListTbody">
				</tbody>
			</table>
		</div>

		<a href="${cp }/user/userForm" class="btn btn-default pull-right">사용자 등록</a>

		<div class="text-center">
			<ul class="pagination">
			</ul>
		</div>
	</div>
</div>
	</div>
		</div>
	</div>
</body>
</html>
