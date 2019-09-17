<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul class="nav nav-sidebar">
		<li class="active"><a href="${cp}/user/main.jsp">Main <span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="${cp}/user/userList">사용자리스트<span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="${cp}/user/userListOnlyHalf">사용자리스트(50)<span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="${cp}/user/userPagingList">사용자 페이징 리스트<span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="${cp}/lprodList">제품그룹리스트</a></li>
		<li class="active"><a href="${cp}/lprodPagingList">제품그룹 페이징 리스트</a></li>
	</ul>
</body>
</html>