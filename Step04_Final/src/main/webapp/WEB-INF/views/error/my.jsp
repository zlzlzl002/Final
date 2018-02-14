<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error/my.jsp</title>
</head>
<body>
<h3> MyException type 예외 발생!</h3>
<p>정보 : <strong>${exception.message }</strong></p>
<a href="${pageContext.request.contextPath }/">Home 으로</a>
</body>
</html>