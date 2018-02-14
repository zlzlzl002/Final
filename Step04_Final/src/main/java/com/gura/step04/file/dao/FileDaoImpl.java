package com.gura.step04.file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.step04.file.dto.FileDto;

@Repository
public class FileDaoImpl implements FileDao{
	
	//의존 객체
	@Autowired
	private SqlSession session;

	@Override
	public void insert(FileDto dto) {
		session.insert("file.insert", dto);
	}

	@Override
	public void delete(int num) {
		session.delete("file.delete", num);
	}

	@Override
	public List<FileDto> getList(FileDto dto) {
		// parameterType 으로 검색 키워드가 담긴 FileDto 를 전달
		return session.selectList("file.getList", dto);
	}

	@Override
	public FileDto getData(int num) {
		
		return session.selectOne("file.getData", num);
	}

	@Override
	public int getCount(FileDto dto) {
		// parameterType 으로 검색 키워드가 담긴 FileDto 를 전달
		int count=session.selectOne("file.getCount", dto);
		return count;
	}
	
	
}












