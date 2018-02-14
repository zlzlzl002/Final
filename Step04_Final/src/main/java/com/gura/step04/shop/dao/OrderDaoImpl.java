package com.gura.step04.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.step04.shop.dto.OrderDto;

@Repository
public class OrderDaoImpl implements OrderDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void addOrder(OrderDto dto) {
		session.insert("shop.addOrder", dto);
	}

}






