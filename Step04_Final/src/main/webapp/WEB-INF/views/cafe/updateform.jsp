<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafe/updateform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<style>
	/* SmartEditor 를 위한 textarea 에 적용할 css */
	#content{
		display:none;
		width:100%;
		height:400px;
	}
</style>
<!-- SmartEditor 관련 javascript 로딩 -->
<script src="${pageContext.request.contextPath }/SmartEditor/js/HuskyEZCreator.js"></script>
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
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath }/">Home</a></li>
		<li><a href="list.do">Cafe</a></li>
		<li class="active">글 수정 페이지</li>
	</ol>
	
	<!-- 글 수정 form -->
	<form action="update.do" method="post">
		<input type="hidden" name="num" value="${dto.num }"/>
		<div class="form-group">
			<label for="writer">작성자</label>
			<input class="form-control" type="text" id="writer" value="${id }" disabled/>
		</div>
		<div class="form-group">
			<label for="title">제목</label>
			<input class="form-control" type="text" name="title" id="title" value="${dto.title }"/>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea name="content" id="content">${dto.content }</textarea>
		</div>
		<button onclick="submitContents(this);" 
			class="btn btn-success" type="submit">수정 확인</button>
	</form>
</div>
<script>
var oEditors = [];

//추가 글꼴 목록
//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",
	sSkinURI: "${pageContext.request.contextPath}/SmartEditor/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["content"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});

function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["content"].getIR();
	alert(sHTML);
}
	
function submitContents(elClickedObj) {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
	
	//검증후 폼의 전송을 막고 싶으면 return false; 
	
	try {
		elClickedObj.form.submit();//폼전송
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '궁서';
	var nFontSize = 24;
	oEditors.getById["content"].setDefaultFont(sDefaultFont, nFontSize);
}
</script>
</body>
</html>













