<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<script>
$(document).ready(function () {
	
	$('form').submit(function (event) {
		
		var title    = $('#title').val();
		var contents = $('#contents').val();
		
		if(title == '') {
			alert('제목을 입력하세요.');
			event.preventDefault();
			return;
		}
		
		if(contents == '') {
			alert('내용을 입력하세요.');
			event.preventDefault();
			return;
		}
		
	});
});
</script>

</head>

<body>

<jsp:include page="../bbsNav.jsp" />

<!-- 글쓰기 양식 -->
<div class="container">
	<div class="row">							 <!-- 파일 전송을 위한 enctype 설정 -->
		<form method="POST" action="./writeAction" enctype="multipart/form-data">
			<table class="table table-striped" style="text-align: center; border: 1px solid #bbbbbb;">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">게시판 글쓰기 양식</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" class="form-control" id="title" placeholder="글 제목" name="title" maxlength="50"></td>
					</tr>
					<tr>
							<!-- textarea : 장문의 문자를 입력받는 HTML 입력 태그 -->
						<td><textarea class="form-control" id="contents" placeholder="글 내용" name="contents" style="height: 350px;"></textarea></td>
					</tr>
					<tr>
						<td><input type="file" class="form-control" id="file" placeholder="첨부파일" name="file"></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
		</form>
	</div>
</div>
<!-- 글쓰기 양식 종료 -->
<script>
$(document).ready(function(){
	var msg = '${msg}';
	if(msg != null && msg != '') alert(msg);
});
</script>
</body>

</html>