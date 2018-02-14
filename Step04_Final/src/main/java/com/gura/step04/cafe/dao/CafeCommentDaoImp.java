package com.gura.step04.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.step04.cafe.dto.CafeCommentDto;

@Repository
public class CafeCommentDaoImp implements CafeCommentDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(CafeCommentDto dto) {
		session.insert("cafeComment.insert", dto);
	}
	@Override
	public List<CafeCommentDto> getList(int ref_group) {	
		return session.selectList("cafeComment.getList", ref_group);
	}
	@Override
	public int getSequence() {
		return session.selectOne("cafeComment.getSequence");
	}

}




