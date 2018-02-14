package com.gura.step04.file.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.step04.file.dto.FileDto;
import com.gura.step04.file.service.FileService;

@Controller
public class FileController {
	//의존 객체
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/file/list")
	public ModelAndView list(HttpServletRequest request){
		// 여러가지 정보가 담긴 ModelAndView 객체를 서비스로 부터
		// 리턴 받아서 
		ModelAndView mView=fileService.list(request);
		// view 페이지 정보를 담고 
		mView.setViewName("file/list"); // forward 이동
		// Spring Framework 에 리턴해 준다. 
		return mView;
	}
	/*
	 *  Aop 가 적용이 되면 HttpServletRequest 객체가 필요함으로
	 *  메소드의 인자로 전달 받도록 한다. 
	 */
	@RequestMapping("/file/insertform")
	public ModelAndView authInsertForm(HttpServletRequest request){
		
		//생성자의 인자로 view 페이지의 정보를 전달해서 객체 생성 
		ModelAndView mView=new ModelAndView("file/insertform");
		
		return mView;
	}
	
	//파일 업로드 요청 처리
	@RequestMapping("/file/insert")
	public ModelAndView authInsert(HttpServletRequest request, 
			@ModelAttribute FileDto dto){
		/*
		 *  인자로 전달된 FileDto 에는 
		 *  String writer  작성자하고
		 *  MultipartFile file 객체의 참조값이 들어 있다.
		 *  나머지 정보는 서비스에서 처리 해야한다. 
		 */
		
		fileService.insert(request, dto);
		
		return new ModelAndView("redirect:/file/list.do");
	}
	
	@RequestMapping("/file/download")
	public ModelAndView download(@RequestParam int num){
		
		//다운로드할 파일의 정보를 ModelAndView 객체에 담아서 받는다.
		ModelAndView mView=fileService.getData(num);
		
		// view 의 이름을 지정 (Abstract View 페이지의 이름)
		mView.setViewName("fileDownView");
		
		//mView.setViewName("file/download");
		
		return mView;
	}
	
	@RequestMapping("/file/delete")
	public ModelAndView authDelete(HttpServletRequest request, 
			@RequestParam int num){
		
		fileService.delete(request, num);
		
		return new ModelAndView("redirect:/file/list.do");
	}
}


















