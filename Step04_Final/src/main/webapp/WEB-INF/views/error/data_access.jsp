<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error/data_access.jsp</title>
</head>
<body>
<h3>에러가 발생했습니다.</h3>
<p>예외 정보 : ${exception.message }</p>
<a href="${pageContext.request.contextPath }/">확인</a>
</body>
</html>











