<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.PrintWriter" %>

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


<!-- 메인 네비게이션 -->
<!-- nav : 네비게이션 리스트 만들어주는 HTML 태그 -->
<nav class="navbar navbar-default">

	<!-- 네비게이션 헤더 -->
	<!-- div : 웹 페이지에서 공간을 지정하는 HTML 태그 (가로 전체) -->
	<div class="navbar-header">
		<!-- button : 버튼을 만들어주는 HTML 태그 -->
		<button type="button" class="navbar-toggle collapsed" 
			data-toggle="collapse" data-target="#bs-navbar-collapse"
			aria-expanded="false">
			<!-- span : 웹 페이지에서 공간을 지정하는 HTML 태그 (컨텐츠 사이즈 가로) -->
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<!-- a : url을 이동시켜주는 HTML 태그 href="" 속성에 경로 지정 -->
		<a class="navbar-brand" href="${path }">JSP 게시판</a>
	</div>
	<!-- 네비게이션 헤더 종료 -->
	
	<!-- 네비게이션 메뉴 -->
	<div class="collapse navbar-collapse" id="bs-navbar-collapse">
		
		<!-- 메인 메뉴 -->
		<!-- ul : 순서가 없는 리스트를 만들어주는 HTML 태그 -->
		<ul class="nav navbar-nav">
			<!-- li : 리스트 하나의 요소를 만들어주는 HTML 태그 -->
			<li class="active"><a href="${path }">메인</a></li>
			<li><a href="${path }/bbs">게시판</a></li>
		</ul>
		<!-- 메인 메뉴 종료 -->
		
		<!-- 마이페이지 메뉴 -->
		<!-- 로그인이 되있지 않을 때 표시 -->
		<c:if test ="${empty user_id }">
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">
					마이페이지<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="${path }/login">로그인</a></li>
					<li><a href="${path }/join">회원가입</a></li>
				</ul>
			</li>
		</ul>
		</c:if>
		<c:if test ="${not empty user_id }">
		<!-- 로그인이 되었을 때 표시 -->
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">
					마이페이지<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="${path }/logout">로그아웃</a></li>
				</ul>
			</li>
		</ul>
		</c:if>
		<!-- 마이페이지 메뉴 종료 -->
		
	</div>
	<!-- 네비게이션 메뉴 종료 -->
	
</nav>
<!-- 메인 네비게이션 종료 -->
	
</nav>
<!-- 메인 네비게이션 종료 -->

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


</body>

</html>