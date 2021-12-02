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

</head>

<body>

<jsp:include page="../bbsNav.jsp" />

<!-- 글 수정하기 양식 -->
<div class="container">
	<div class="row">
		<form method="POST" action="./updateAction" enctype="multipart/form-data">
			<input type="hidden" name="boarder_id" value="${map.boarder.boarder_id }">
			<table class="table table-striped" style="text-align: center; border: 1px solid #bbbbbb;">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;"> 게시물</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;">제목</td>
						<td><input type="text" class="form-control" name="title" value="${map.boarder.title }"></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><input type="hidden" name="writer" value="${map.boarder.writer }">${map.boarder.writer }</td>
						
					</tr>
					<tr>
						<td>작성일</td>
						<td>${map.boarder.reg_date }</td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea class="form-control" name="contents" style="height: 350px;">${map.boarder.contents }</textarea></td>
					</tr>
					
					<c:if test="${not empty map.uploadFile }">
					<tr>
						<td>첨부파일</td>
						<td>${map.uploadFile.file_name }</td>
					</tr>
					</c:if>
					
					<tr>
						<td colspan="2"><input type="file" class="form-control" id="file" placeholder="첨부파일" name="file"></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" class="btn btn-warning" value="수정">
		</form>
	</div>
</div>
<!-- 글 수정하기 양식 종료 -->

<script>
$(document).ready(function(){
	var msg = '${msg}';
	if(msg != null && msg != '') alert(msg);
});
</script>
</body>

</html>