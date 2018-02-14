package com.gura.step04.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.step04.cafe.dto.CafeCommentDto;
import com.gura.step04.cafe.dto.CafeDto;

public interface CafeService {
	public void insert(CafeDto dto);
	public void update(CafeDto dto);
	public void delete(int num);
	public ModelAndView list(HttpServletRequest request);
	public ModelAndView detail(HttpServletRequest request);
	public ModelAndView detail(int num);
	//덧글 추가하는 메소드
	public void commentInsert(HttpServletRequest request);
}












