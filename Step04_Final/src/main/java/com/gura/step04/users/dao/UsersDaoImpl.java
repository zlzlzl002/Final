package com.gura.step04.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.step04.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{
	
	//의존객체
	@Autowired
	private SqlSession session;
	
	
	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
	}

	@Override
	public UsersDto getData(String id) {
		
		return session.selectOne("users.getData", id);
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
	}

	@Override
	public void delete(String id) {
		session.delete("users.delete", id);
	}

	@Override
	public boolean canUseId(String id) {
		// id 가 존재하는지 select 해본다. 
		String result=session.selectOne("users.isExistId", id);
		
		if(result==null){ //존재하지 않으면
			return true; //사용가능
		}else{//존재하면
			return false;//사용불가 
		}
	}

}










