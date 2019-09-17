<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${cp}/js/jquery-3.4.1.min.js"></script>
<script>
	$(function(){
		//pathBtn이 클릭 될 때 이벤트 핸들러
		$("#pathBtn").on("click", function(){
			var subpath = $("input[name=path]:checked").val();
			$("#frm").attr("action", "/spring/mvc/path/" + subpath);
			$("#frm").submit();
			
		})
	})		
</script>
</head>
<body>
	<h2>mvc/view.jsp</h2>
	<form action="${cp}/mvc/requestParam" method="post">
		userId : <input type="text" name="userId" value="sally"/>
		<input type="submit" value="전송"/>
	</form>
	
	<h3>pathvariable</h3>
	<form id="frm">
		brown : <input type="radio" name="path" value="brown"><br>
		sally : <input type="radio" name="path" value="sally"><br>
		<input type="button" id="pathBtn" value="전송"/>
	</form>
</body>
</html>