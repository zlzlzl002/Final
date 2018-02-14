<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<script src="${pageContext.request.contextPath}/SmartEditor/js/HuskyEZCreator.js"></script>
<style>
	textarea{resize:none;}
	#content{display:none;width:100%;height:300px;}
	.comments{
		border: 1px dotted #cecece;
	}
		.comment textarea{
		width:80%;
		background-color: #fff;
	}
	.comment form{
		display: none;
	}
	.comment{
		position: relative;
	}
	.comment .reply_icon{
		position: absolute;
		width: 20px;
		height: 20px;
		top:0;
		left:-20px;
		border-left: 2px solid green;
		border-bottom: 2px solid green;
	}	
	
	body {

margin: 0;

padding: 0;

}

* {

box-sizing: border-box;

-moz-box-sizing: border-box;

}

.page {

width: 21cm;

min-height: 29.7cm;

padding: 2cm;

margin: 0 auto;

background:#eee;

}

.subpage {

border: 2px red solid;

background:#fff;   

height: 257mm;

}



@page {

size: A4;

margin: 0;

}

@media print {

html, body {

width: 210mm;

height: 297mm;

}

.page {

margin: 0;

border: initial;

width: initial;

min-height: initial;

box-shadow: initial;

background: initial;

page-break-after: always;

}

}
</style>

</head>
<body>
<div class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath }/home.do">Acorn</a>
		</div>
		<a class="btn btn-warning btn-xs navbar-btn pull-right" href="${pageContext.request.contextPath}/users/logout.do">로그아웃</a>
		<p class="navbar-text pull-right"><a class="navbar-link" href="${pageContext.request.contextPath }/users/info.do"><strong>${id }</strong></a>님 로그인중... </p>
	</div>
</div>
<div class="container">
	<!-- breadcrumb UI 제공하기 -->
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/home.do">Home</a></li>
		<li><a href="${pageContext.request.contextPath}/cafe/list.do">Cafe</a></li>
		<li class="active">상세 보기</li>
	</ul>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${msg }</div>
	</c:if>
	   <%-- 로그인한 회원이 작성한 글이라면  수정, 삭제 링크를 출력한다. --%>
   <c:if test="${dto.prevNum ne 0 }">
   	<a class="btn btn-default btn-sm" href="detail.do?num=${dto.prevNum}&condition=${condition}&keyword=${keyword}"><i class="glyphicon glyphicon-chevron-up"></i></a>
   </c:if>
   <c:if test="${dto.nextNum ne 0 }">
   	<a class="btn btn-default btn-sm" href="detail.do?num=${dto.nextNum}&condition=${condition}&keyword=${keyword}"><i class="glyphicon glyphicon-chevron-down"></i></a>
   </c:if>
   <button id="print">프린트</button>
   <div class="page">
	<div class="subpage">
   	<h1><strong>${dto.title }</strong></h1>
	<p>${dto.regdate} <em>By</em> <strong>${dto.writer}</strong></p>   
	<hr/>
    <div class="content">${dto.content }</div>
    <hr/>
    <a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/cafe/list.do">리스트로 가기</a>
    <c:if test="${id eq dto.writer }">
    <a class="btn btn-default btn-xs" href="updateform.do?num=${dto.num }">수정</a>
    <a class="btn btn-default btn-xs" href="javascript:deleteCheck()">삭제</a>
    </c:if>
	<!-- 덧글 목록 출력하기 -->
	<div class="comments">
		<c:forEach var="tmp" items="${commentList }">
			<div class="comment" <c:if test="${tmp.num ne tmp.comment_group }">style="margin-left:100px"</c:if> >
				<c:if test="${tmp.num ne tmp.comment_group }">
					<div class="reply_icon"></div>
				</c:if>
				<div>
					from <strong>${tmp.writer }</strong>
					${tmp.regdate }<br/>
					to <strong>${tmp.target_id }</strong>
					<a href="javascript:">답글</a>
				</div>
				<textarea rows="5" disabled>${tmp.content }</textarea>
				<form action="comment_insert.do" method="post">
					<!-- 덧글 작성자 -->
					<input type="hidden" name="writer" value="${id }"/>
					<!-- 덧글 그룹 -->
					<input type="hidden" name="ref_group" value="${dto.num }" />
					<!-- 덧글 대상 -->
					<input type="hidden" name="target_id" value="${tmp.writer }" />
					<input type="hidden" name="comment_group" value="${tmp.comment_group }" />
					<textarea name="content"></textarea>
					<button type="submit">등록</button>
				</form>		
			</div>
		</c:forEach>
	</div>
	</div>
	</div>
	
	<!-- 원글에 대한 덧글 입력 폼 -->
	<div class="comment_form">
		<form action="comment_insert.do" method="post">
			<!-- 덧글 작성자 -->
			<input type="hidden" name="writer" value="${id }"/>
			<!-- 덧글 그룹 -->
			<input type="hidden" name="ref_group" value="${dto.num }" />
			<!-- 덧글 대상 -->
			<input type="hidden" name="target_id" value="${dto.writer }" />
			<textarea name="content"></textarea>
			<button type="submit">등록</button>
		</form>
	</div>   
    
</div>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.js"></script>
<script>
	$("#print").click(function(){
		window.print();
	});
	//로그인 했는지 안했는지 여부 
	var isLogin=${isLogin};
	
	//덧글 전송 이벤트가 일어 났을때 실행할 함수 등록 
	$(".comment_form > form, .comment form").submit(function(){
		if(!isLogin){//만일 로그인 하지 않았다면 
			alert("로그인이 필요 합니다.");
			//로그인 페이지로 이동 
			location.href="${pageContext.request.contextPath}"+
			"/users/loginform.do"+
			"?url=${pageContext.request.contextPath}"+
			"/cafe/detail.do?num=${dto.num}";
			
			return false; //폼전송 막기 
		}
	});
	
	//덧글 달기 혹은 취소 버튼을 눌렀을때 실행할 함수 등록 
	$(".comment a").click(function(){
		if($(this).text()=="답글"){
			$(this)
			.text("취소")
			.parent()
			.parent()
			.find("form")
			.slideToggle();	
		}else{
			$(this)
			.text("답글")
			.parent()
			.parent()
			.find("form")
			.slideToggle();
		}
	});	

	function deleteCheck(){
		var isDelete=confirm("삭제 하시겠습니까?");
		if(isDelete){
			location.href="delete.do?num=${dto.num}";
		}
	}
</script>
</body>
</html>








