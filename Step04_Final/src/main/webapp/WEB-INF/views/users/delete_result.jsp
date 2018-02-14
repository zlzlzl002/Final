<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/delete_result.jsp</title>
</head>
<body>
<h3>회원 탈퇴 결과 페이지</h3>
<p> <strong>${id }</strong> 님 탈퇴 처리 되었습니다.</p>
<p> 다음에 다시 방문해 주세요...</p>
<a href="${pageContext.request.contextPath }/">확인</a>
</body>
</html>










