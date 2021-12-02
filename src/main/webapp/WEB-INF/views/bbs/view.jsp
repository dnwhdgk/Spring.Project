<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
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

<!-- 게시글 보기 양식 -->
<div class="container">
	<div class="row">
		<form method="POST" action="writeAction.jsp">
			<table class="table table-striped" style="text-align: center; border: 1px solid #bbbbbb;">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;"> 게시물</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;">제목</td>
						<!-- ** XSS 방지 ** -->
						<!-- HTML 태그를 문자로 바꿀 때 -->
						<!-- ' '  ->  &nbsp; : 문자열.replaceAll(" ", "&nbsp;")  -->
						<!-- '<'  ->  &lt;   : 문자열.replaceAll("<", "&lt;")    -->
						<!-- '>'  ->  &gt;   : 문자열.replaceAll(">", "&gt;")    -->
						<!-- '\n' ->  '<br>' : 문자열.replaceAll("\n", "<br>")   -->
						<td>${map.boarder.title }</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>${map.boarder.writer }</td>
					</tr>
					<tr>
						<td>작성일</td>
						<td>${map.boarder.reg_date }</td>
					</tr>
					<tr>
						<td>내용</td>
						<td style="min-height: 200px; text-align: left;">${map.boarder.contents }</td>
					</tr>
					<c:if test="${not empty map.uploadFile}">
					<tr>
						<td>첨부파일</td>
						<td><a href="./downloadAction?boarder_id=${map.uploadFile.boarder_id }&file_realName=${map.uploadFile.file_realName }">${map.uploadFile.file_name }</a></td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<a href="../bbs" class="btn btn-default">목록</a>
			<c:if test="${user_id eq map.boarder.writer }">
			<a href="./update?boarder_id=${map.boarder.boarder_id }" class="btn btn-success">수정</a>
			<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="./deleteAction?boarder_id=${map.boarder.boarder_id }" class="btn btn-danger">삭제</a>
			</c:if>
		</form>
	</div>
</div>
<!-- 게시글 보기 양식 종료 -->

<script>
$(document).ready(function(){
	var msg = '${msg}';
	if(msg != null && msg != '') alert(msg);
});
</script>
</body>

</html>