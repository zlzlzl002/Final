<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/loginform.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>
:root {
  --input-padding-x: .75rem;
  --input-padding-y: .75rem;
}

html,
body {
  height: 100%;
}

body {
  display: -ms-flexbox;
  display: -webkit-box;
  display: flex;
  -ms-flex-align: center;
  -ms-flex-pack: center;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

.form-signin {
  width: 100%;
  max-width: 420px;
  padding: 15px;
  margin: 0 auto;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group > input,
.form-label-group > label {
  padding: var(--input-padding-y) var(--input-padding-x);
}

.form-label-group > label {
  position: absolute;
  top: 0;
  left: 0;
  display: block;
  width: 100%;
  margin-bottom: 0; /* Override default `<label>` margin */
  line-height: 1.5;
  color: #495057;
  border: 1px solid transparent;
  border-radius: .25rem;
  transition: all .1s ease-in-out;
}

.form-label-group input::-webkit-input-placeholder {
  color: transparent;
}

.form-label-group input:-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-moz-placeholder {
  color: transparent;
}

.form-label-group input::placeholder {
  color: transparent;
}

.form-label-group input:not(:placeholder-shown) {
  padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
  padding-bottom: calc(var(--input-padding-y) / 3);
}

.form-label-group input:not(:placeholder-shown) ~ label {
  padding-top: calc(var(--input-padding-y) / 3);
  padding-bottom: calc(var(--input-padding-y) / 3);
  font-size: 12px;
  color: #777;
}
</style>
</head>
<body>
    <form class="form-signin" action="login.do" method="post">
      <input type="hidden" name="url" value="${url }"/>
      <div class="text-center mb-4">
        <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Please Sign in</h1>
      </div>

      <div class="form-label-group">
        <input name="id" type="text" id="id" class="form-control" placeholder="아이디" required autofocus>
        <label for="id">아이디</label>
      </div>

      <div class="form-label-group">
        <input name="pwd" type="password" id="pwd" class="form-control" placeholder="비밀번호" required>
        <label for="pwd">비밀번호</label>
      </div>

      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" id="isSave"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
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













