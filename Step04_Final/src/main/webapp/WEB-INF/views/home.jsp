<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  모바일 에서 반응형 페이지 만들때 필요한 설정  -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalble=no"/>
<title>home.jsp</title>
</head>
<body>
<a href="users/info.do">테스트 링크</a>
<h3>인덱스 페이지 입니다.</h3>
<c:choose>
	<c:when test="${empty id }">
		<a href="users/loginform.do">로그인</a>
	</c:when>
	<c:otherwise>
		<p> <a href="users/info.do">${id }</a> 님 로그인 중...</p>
		<a href="users/logout.do">로그아웃</a>
	</c:otherwise>
</c:choose>
<ul>
	<li><a href="users/signup_form.do">회원가입</a></li>
	<li><a href="file/list.do">자료실 목록보기</a></li>
	<li><a href="cafe/list.do">카페 글 목록보기</a></li>
	<li><a href="shop/list.do">상품 목록 보기</a></li>
	<li><a href="shop/test1.do">예외 test</a></li>
	<li><a href="shop/test1.do?name=gura">예외 test2</a></li>
	<li><a href="html5/Step01_Canvas.html">canvas</a></li>
	<li><a href="html5/Step05_OrientationSensor.html">방향센서1</a></li>
	<li><a href="html5/Step05_OrientationSensor2.html">방향센서2</a></li>
	<li><a href="html5/Step06_GeoLocation.html">위치정보</a></li>
</ul>

<h4>공지사항</h4>
<ul>
	<c:forEach var="tmp" items="${news }">
		<li>${tmp }</li>
	</c:forEach>
</ul>
</body>
</html>








