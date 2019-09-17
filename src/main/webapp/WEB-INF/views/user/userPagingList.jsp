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
		$('.userTr').click(function(){
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
</script>
</head>

<body>
<form id="frm" action="${cp}/user" method="get">
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
				<c:forEach items="${userList}" var="user">
					<tr class="userTr" data-userId="${user.userId}">
						<input type="hidden" value="${user.userId}"/>
						<td>${user.userId}</td>
						<td>${user.userNm}</td>
						<td>${user.alias}</td>
						<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy/MM/dd"/></td>
						
					</tr>
				</c:forEach>
			</table>
		</div>

		<a href="${cp }/userForm" class="btn btn-default pull-right">사용자 등록</a>

		<div class="text-center">
			<ul class="pagination">
				<%-- 
					이전 페이지 가기 : 지금 있는 페이지에서 한 페이지 전으로
					단, 1페이지인 경우에는 li 태그에 class="disabled"를 추가를 하고 이동경로는 차단
				--%>
				<c:choose>
					<c:when test="${pageVo.page == 1}">
						<li class="disabled">
						    <a href="#" aria-label="Previous">
						    	<span aria-hidden="true">&laquo;</span>
						    </a>
					    </li>
					</c:when>
					<c:otherwise>
						<li>
						    <a href="${cp}/user/userPagingList?page=${pageVo.page-1}&pageSize=10" aria-label="Previous">
						    	<span aria-hidden="true">&laquo;</span>
						    </a>
					    </li>
					</c:otherwise>
				</c:choose>
				
			    
				<c:forEach begin="1" end="${paginationSize}" var="page">
					<c:choose>
						<c:when test="${page == pageVo.page }">
							<li class="active"><span>${page}</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="${cp}/user/userPagingList?page=${page}&pageSize=10">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageVo.page == paginationSize}">
						<li class="disabled">
						    <a href="#" aria-label="Next">
						    	<span aria-hidden="true">&raquo;</span>
						    </a>
					    </li>
					</c:when>
					<c:otherwise>
						<li>
						    <a href="${cp}/user/userPagingList?page=${pageVo.page+1}&pageSize=10" aria-label="Next">
						    	<span aria-hidden="true">&raquo;</span>
						    </a>
					    </li>
					</c:otherwise>
				</c:choose>
				
			</ul>
		</div>
	</div>
</div>
	</div>
		</div>
	</div>
</body>
</html>