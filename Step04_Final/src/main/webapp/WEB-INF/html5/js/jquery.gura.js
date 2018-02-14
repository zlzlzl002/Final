// jquery 플러그인 만들기
// slick fotorama 사용해보기 
(function($){
		//jquery 추가 동작 정의하기
		 $.fn.gura=function(options){ //gura는 추가 동작의 이름정의 자유 
		 	// 기본 설정값 정의 
		 	var settings=$.extend({  //settings obj {}
		 		msg:"안녕! 나는 구라야 ~",
		 		textColor:"#000",
		 		bgColor:"#cecece"
		 	}, options);

		 	// this 는 선택된 jquery object 이다.
		 	this  // .jquery action 사용 
		 	.text(settings.msg)
		 	.css("color", settings.textColor)
		 	.css("background-color", settings.bgColor);  //text는 jquery obj

		 	return this; // chain 형태로 동작 가능하도록 
		 }; 
})(jQuery);
