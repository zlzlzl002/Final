package com.gura.step04.shop.dao;

import com.gura.step04.shop.dto.OrderDto;

public interface OrderDao {
	//배송 요청을 하는 메소드 
	public void addOrder(OrderDto dto);
}
