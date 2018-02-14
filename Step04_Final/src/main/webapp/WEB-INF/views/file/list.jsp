<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>file/list.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath }/home.do">Acorn</a>
		</div>
		<c:choose>
			<c:when test="${not empty id }">
				<a class="btn btn-warning btn-xs navbar-btn pull-right" href="${pageContext.request.contextPath}/users/logout.do">로그아웃</a>
				<p class="navbar-text pull-right"><a class="navbar-link" href="${pageContext.request.contextPath }/users/info.do"><strong>${id }</strong></a>님 로그인중... </p>
			</c:when>
			<c:otherwise>
				<a class="navbar-text navbar-link pull-right" href="${pageContext.request.contextPath }/users/loginform.do?url=${pageContext.request.contextPath}/file/list.do">로그인</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div class="container">
	<!-- breadcrumb UI 제공하기 -->
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath }/">Home</a></li>
		<li class="active">파일 자료실</li>
	</ol>

	<a class="btn btn-primary btn-xs" href="insertform.do"><i class="glyphicon glyphicon-upload"></i> 파일 올리기</a>
	<h3>업로드된 파일 목록입니다.</h3>
	<table class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>파일</th>
				<th>크기</th>
				<th>등록일</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tmp" items="${list}">
				<tr>
					<td>${tmp.num }</td>
					<td>${tmp.writer }</td>
					<td>${tmp.title }</td>
					<td>${tmp.orgFileName} <a class="pull-right" href="download.do?num=${tmp.num }"><i class="glyphicon glyphicon-download"></i><span class="sr-only">다운로드</span></a></td>
					<td><fmt:formatNumber value="${tmp.fileSize}"/> <strong>bytes</strong></td>
					<td>${tmp.regdate }</td>
					<td>
						<c:if test="${sessionScope.id eq tmp.writer }">
							<a href="javascript:deleteConfirm(${tmp.num })"><i class="glyphicon glyphicon-trash"></i><span class="sr-only">삭제</span></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination">
		<c:choose>
			<c:when test="${startPageNum ne 1 }">
				<li>
					<a href="list.do?pageNum=${startPageNum-1 }&condition=${condition}&keyword=${keyword}">&laquo;</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="disabled">
					<a href="javascript:">&laquo;</a>
				</li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPageNum }" 
				end="${endPageNum }">	
			<c:choose>
				<c:when test="${i eq pageNum }">
					<li class="active"><a href="list.do?pageNum=${i }&condition=${condition}&keyword=${keyword}">${i }</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="list.do?pageNum=${i }&condition=${condition}&keyword=${keyword}">${i }</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${endPageNum lt totalPageCount }">
				<li>
					<a href="list.do?pageNum=${endPageNum+1 }&condition=${condition}&keyword=${keyword}">&raquo;</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="disabled">
					<a href="javascript:">&raquo;</a>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
	<div class="row">
		<div class="col-xs-6">
			<!-- 검색어 관련 form -->
			<form action="list.do" method="post">
				<input type="hidden" id="condition" value="${condition }" name="condition" />
		        <div class="input-group">
		          <div class="input-group-btn">
		            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
		              <span id="conditionBtn">제목+파일명</span>
		              <span class="caret"></span>
		            </button>
		            <ul class="dropdown-menu">
		              <li><a href="javascript:set('titlecontent')">제목+파일명</a>
		              </li>
		              <li><a href="javascript:set('title')">제목</a>
		              </li>
		              <li><a href="javascript:set('writer')">작성자</a>
		              </li>
		            </ul>
		          </div>
		          <!-- /btn-group -->
		          <input type="text" id="keyword" name="keyword" value="${keyword }" class="form-control"/>
		          	<span class="input-group-btn">
			            <button class="btn btn-default" type="submit">
			              <span class="sr-only">검색</span>
			              <span class="glyphicon glyphicon-search"></span>
			            </button>
          			</span>
		        </div>			
			</form>
		</div>
	</div><!-- /.row -->
</div><!-- /.container -->
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap.js"></script>
<script>
	
	set("${condition}");
	
	function set(condition){
		if(condition=="titlecontent" || condition==""){
			condition="titlecontent";
			$("#conditionBtn").text("제목+파일명");
		}else if(condition=="title"){
			$("#conditionBtn").text("제목");
		}else if(condition=="writer"){
			$("#conditionBtn").text("작성자");
		}
		$("#condition").val(condition);
	}
	
	function deleteConfirm(num){
		var result=confirm(num+" 번 파일을 삭제 하시겠습니까?");
		if(result){
			location.href="private/delete.do?num="+num;
		}
	}
</script>
</body>
</html>











