package com.gura.step04;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		
		//가상의 공지 사항 
		List<String> news=new ArrayList<>();
		news.add("하나");
		news.add("두울");
		news.add("세엣");
		news.add("어쩌구.. 저쩌구...");
		
		request.setAttribute("news", news);
		
		return "home";
	}

	
}









