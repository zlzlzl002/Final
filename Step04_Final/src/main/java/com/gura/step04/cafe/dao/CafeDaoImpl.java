package com.gura.step04.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.step04.cafe.dto.CafeDto;

@Repository
public class CafeDaoImpl implements CafeDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(CafeDto dto) {
		// parameterType => CafeDto 
		session.insert("cafe.insert", dto);
	}

	@Override
	public void update(CafeDto dto) {
		session.update("cafe.update", dto);
	}

	@Override
	public void delete(int num) {
		session.delete("cafe.delete", num);
	}

	@Override
	public CafeDto getData(CafeDto dto) {
		
		return session.selectOne("cafe.getData", dto);
	}
	//글의 목록 (검색어에 일치하는) 리턴하기 
	@Override
	public List<CafeDto> getList(CafeDto dto) {
		
		return session.selectList("cafe.getList", dto);
	}
	//글의 갯수 (검색어에 일치하는) 리턴하기 
	@Override
	public int getCount(CafeDto dto) {
		/*
		 *  parameterType => CafeDto
		 *  resultType => int 
		 */
		int count=session.selectOne("cafe.getCount", dto);
		return count;
	}

	@Override
	public void increaseViewCount(int num) {
		session.update("cafe.addCount", num);
	}

	@Override
	public CafeDto getData(int num) {
		
		return session.selectOne("cafe.getData2", num);
	}
}















