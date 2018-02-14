package com.gura.step04.shop.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.gura.step04.exception.MyException;
/*
 *  예외를 처리하는 컨트롤러 
 */
@ControllerAdvice
public class ExceptionController {
//	//MyException type 의 예외가 발생했을때 호출되는 메소드
	@ExceptionHandler(MyException.class)
	public ModelAndView handleMyException(MyException me){
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception", me);
		mView.setViewName("error/my");
		return mView;
	}
	/*
	 *  DB 관련 작업도중 발생한 Exception 은 DataAccessException 
	 *  type 으로 받을수 있다.
	 */
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView 
		handleDataAccessException(DataAccessException dae){
		
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception", dae);
		mView.setViewName("error/data_access");
		return mView;
	}
}
















