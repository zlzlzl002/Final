package com.gura.step04.cafe.dao;

import java.util.List;

import com.gura.step04.cafe.dto.CafeCommentDto;

public interface CafeCommentDao {
	public void insert(CafeCommentDto dto);
	public List<CafeCommentDto> getList(int ref_group);
	public int getSequence();///덧글의 글번호를 리턴하는 메소드 
}
