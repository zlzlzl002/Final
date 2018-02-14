package com.gura.step04.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.step04.shop.dto.ShopDto;

@Repository
public class ShopDaoImpl implements ShopDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<ShopDto> getList() {
		
		return session.selectList("shop.getList");
	}
	//재고의 갯수를 1 감소 시키는 메소드 
	@Override
	public void minusCount(int num) {
		session.update("shop.minusCount", num);
	}
	//회원의 계좌 잔액을 줄이는 메소드 
	@Override
	public void minusMoney(ShopDto dto) {
		session.update("shop.minusMoney", dto);
	}
	// 10% 의 포인트를 적립하는 메소드 
	@Override
	public void plusPoint(ShopDto dto) {
		session.update("shop.plusPoint", dto);
	}
	//상품의 가격을 리턴해주는 메소드 
	@Override
	public int getPrice(int num) {
		
		return session.selectOne("shop.getPrice", num);
	}

}








