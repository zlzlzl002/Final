<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/loginform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/login.css" />
</head>
<body>
<div class="container">
      <form class="form-signin" action="login.do" method="post">
      	<input type="hidden" name="url" value="${url }"/>
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="id" class="sr-only">아이디</label>
        <input type="text" id="id" name="id" class="form-control" placeholder="아이디" required autofocus>
        <label for="pwd" class="sr-only">Password</label>
        <input type="password" id="pwd" name="pwd" class="form-control" placeholder="비밀번호" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" id="isSave" /> 저장
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
      </form>
</div>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.js"></script>
<script>

	// localStorage 에 저장된 아이디 비밀번호가 있으면 복구시켜준다.
	if(localStorage.id != undefined){
		$("#id").val(localStorage.id);
		$("#pwd").val(localStorage.pwd);
		//체크박스 체크해주기
		$("#isSave").prop("checked", true);
	}


	//폼 전송 이벤트가 발생했을때 실행할 함수 등록
	$(".form-signin").on("submit", function(){
		//아이디 비밀번호 저장여부 
		var isSave=$("#isSave").is(":checked");
		if(isSave){
			//입력한 아이디 비밀번호를 읽어와서
			var inputId=$("#id").val();
			var inputPwd=$("#pwd").val();
			//localStorage 에 저장한다.
			localStorage.id=inputId;
			localStorage.pwd=inputPwd;
		}else{
			//localStorage 에 id, pwd 삭제하기 
			delete localStorage.id;
			delete localStorage.pwd;
		}
	});
</script>
</body> 
</html>













