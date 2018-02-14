<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/logout_result.jsp</title>
</head>
<body>
<script>
	//알림창 띄우기 
	alert("${msg}");
	//root 로 이동 
	location.href="${pageContext.request.contextPath}/";
</script>
</body>
</html>









