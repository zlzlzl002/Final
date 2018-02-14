<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafe/update_result.jsp</title>
</head>
<body>
<script>
	alert("${num} 번 글을 수정하였습니다.");
	location.href="detail.do?num=${num}";
</script>
</body>
</html>










