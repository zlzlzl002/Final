package com.gura.step04.shop.service;

import org.springframework.web.servlet.ModelAndView;

import com.gura.step04.shop.dto.ShopDto;

public interface ShopService {
	public ModelAndView getList();
	public ModelAndView buy(ShopDto dto);
}
