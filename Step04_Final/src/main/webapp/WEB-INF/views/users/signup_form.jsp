<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/signup_form.jsp</title>
</head>
<body>
<h3>회원가입 폼입니다.</h3>
<form action="signup.do" method="post" id="myForm">
	<label for="id">아이디</label>
	<input type="text" name="id" id="id" />
	<button id="checkBtn">중복확인</button>
	<span id="checkResult"></span>
	<br/>
	<label for="pwd">비밀번호</label>
	<input type="password" name="pwd" id="pwd" />
	<br/>
	<label for="email">이메일</label>
	<input type="text" name="email" id="email"/>
	<br/>
	<button type="submit">가입</button>
	<button type="reset">취소</button>
</form>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.js"></script>
<script>

	$(function(){
		//입력한 아이디가 유효한지 여부 
		var idValid=false;

		$("#checkBtn").click(function(){
			//입력한 아이디을 읽어와서
			var inputId=$("#id").val();
			//ajax 를 이용해서 서버에 전송
			$.ajax({
				url:"checkid.do",
				method:"GET",
				data:{"inputId":inputId},
				success:function(data){
					if(data.canUse){//사용가능
						$("#checkResult").text("사용가능")
						.css("color","green");
						idValid=true;
					}else{//사용불가
						$("#checkResult").text("사용불가")
						.css("color","red");
						idValid=false;
					}
				}
			});
			
			return false; //중복확인 버튼을 눌렀을때 폼 전송 막기 
		});
		
		//폼 전송 이벤트가 발생했을때
		$("#myForm").submit(function(){
			if(idValid==false){
				alert("아이디 중복 확인을 하세요.");
				$("#id").val("").focus();
				return false; //폼 전송 막기 
			}
		});
		
		//id 입력란에 keydown 이벤트가 일어났을때
		$("#id").keydown(function(){
			idValid=false;
			$("#checkResult").text("");
		});
			
		
	});
	
</script>
</body>
</html>











