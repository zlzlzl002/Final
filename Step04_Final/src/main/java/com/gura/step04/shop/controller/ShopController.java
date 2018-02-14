package com.gura.step04.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gura.step04.exception.MyException;
import com.gura.step04.shop.dto.ShopDto;
import com.gura.step04.shop.service.ShopService;

@Controller
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	//@RequestMapping("/shop/list")
	@RequestMapping(value="/shop/list", method=RequestMethod.GET)
	public ModelAndView list(){
		
		ModelAndView mView=shopService.getList();
		
		mView.setViewName("shop/list");
		
		return mView;
	}
	/*
	 *  요청 방식: GET
	 *  요청 경로: /shop/buy.do 
	 *  
	 *  위와 같은 요청이 왔을때 실행할 컨트롤러 메소드 
	 */
	@RequestMapping(value="/shop/buy", method=RequestMethod.GET)
	public ModelAndView authBuy(HttpServletRequest request) {
		//로그인된 아이디
		String id=(String)request.getSession().getAttribute("id");
		//구입할 상품의 번호
		int num=Integer.parseInt(request.getParameter("num"));
		//두가지 정보를 ShopDto 에 담고
		ShopDto dto=new ShopDto();
		dto.setId(id);
		dto.setNum(num);
		//구입하는 비즈니스 로직 처리 
		ModelAndView mView=shopService.buy(dto);
		mView.setViewName("shop/buy_result");
		
		return mView;
	}
	
	@RequestMapping("/shop/test1")
	public ModelAndView test1(HttpServletRequest request) throws MyException{
		String name=request.getParameter("name");
		if(name==null){
			//예외 발생 시키기 
			throw new MyException("name 이 null 입니다.");
		}
		return new ModelAndView("shop/test1");
	}
	
}
















