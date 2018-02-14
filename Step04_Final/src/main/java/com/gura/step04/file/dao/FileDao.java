package com.gura.step04.file.dao;

import java.util.List;

import com.gura.step04.file.dto.FileDto;

public interface FileDao {
	public void insert(FileDto dto);
	public void delete(int num);
	public List<FileDto> getList(FileDto dto); //FileDto 를 전달받도록
	public FileDto getData(int num);
	public int getCount(FileDto dto); //검색키워드에 맞는 글의 갯수 리턴
}
