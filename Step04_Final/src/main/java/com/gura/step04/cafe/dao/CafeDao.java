package com.gura.step04.cafe.dao;

import java.util.List;

import com.gura.step04.cafe.dto.CafeDto;

public interface CafeDao {
	public void insert(CafeDto dto);
	public void update(CafeDto dto);
	public void delete(int num);
	public CafeDto getData(CafeDto dto);
	public CafeDto getData(int num);
	public List<CafeDto> getList(CafeDto dto);
	public int getCount(CafeDto dto);
	public void increaseViewCount(int num);
}












