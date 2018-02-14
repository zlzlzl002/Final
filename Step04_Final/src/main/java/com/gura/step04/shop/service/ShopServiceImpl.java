package com.gura.step04.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.gura.step04.shop.dao.OrderDao;
import com.gura.step04.shop.dao.ShopDao;
import com.gura.step04.shop.dto.OrderDto;
import com.gura.step04.shop.dto.ShopDto;

@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private ShopDao shopDao;
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public ModelAndView getList() {
		
		List<ShopDto> list=shopDao.getList();
		
		ModelAndView mView=new ModelAndView();
		mView.addObject("list", list);
		
		return mView;
	}
	/* 
	 *  - 트랜젝션 설정 방법
	 *  
	 *  1. pom.xml 에 spring-tx dependency 추가
	 *  2. servlet-context.xml 에 transaction 설정 추가
	 *  3. @Transactional 어노테이션 추가 
	 * 
	 */
	@Transactional
	@Override
	public ModelAndView buy(ShopDto dto) {
		//1. 상품의 가격정보를 얻어온다.
		int price=shopDao.getPrice(dto.getNum());
		//2. 가격만큼 계좌 잔액을 줄인다.
		dto.setPrice(price); 
		shopDao.minusMoney(dto);
		//3. 가격의 10% 를 point 로 적립한다.
		shopDao.plusPoint(dto);
		//4. 재고의 갯수를 -1 줄인다.
		shopDao.minusCount(dto.getNum());
		//5. 배송 요청 정보를 입력한다.
		OrderDto orderDto=new OrderDto();
		orderDto.setId(dto.getId());  //주문자의 아이디
		orderDto.setCode(dto.getNum()); //상품 번호
		orderDto.setAddr("노량진"); //배송지 주소
		orderDao.addOrder(orderDto);
		
		ModelAndView mView=new ModelAndView();
		mView.addObject("msg", "주문이 정상적으로 완료 되었습니다.");
		
		return mView;
	}

}






