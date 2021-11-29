<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<title>JSP 게시판</title>
<meta http-equiv="Conetent-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="${path}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${path}/resources/css/custom.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/js/bootstrap.js"></script>

</head>

<body>

<jsp:include page="../mainNav.jsp" />

<!-- 메인 컨텐츠 -->
<div class="container">
	<div class="col-lg-4"></div>
	
	<div class="col-lg-4">
		<div class="jumbotron">
			<form method="POST" action="./loginAction">
				<h3 style="text-align: center;">로그인</h3>
				
				<!-- 아이디 입력 -->
				<div class="form-group">
					<input type="text" class="form-control" autocomplete="off" name="user_id" placeholder="아이디">
				</div>
				
				<!-- 비밀번호 입력 -->
				<div class="form-group">
					<input type="password" class="form-control" name="user_pw" placeholder="비밀번호">
				</div>
				
				<!-- 제출 버튼 -->
				<div class="form-group">
					<input type="submit" class="btn btn-primary form-control" value="로그인">
				</div>
				
				<!-- 회원가입 버튼 -->
				<div class="form-group">
					<a href="./join" class="btn btn-success form-control">회원가입</a>
				</div>
				
			</form>
		</div>
	</div>
	
	<div class="col-lg-4"></div>
</div>
<!-- 메인 컨텐츠 종료 -->

<script>
$(document).ready(function(){
	var msg = '${msg}';
	if(msg != null && msg != '') alert(msg);
});
</script>

</body>

</html>