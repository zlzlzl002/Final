package com.gura.step04.users.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.step04.users.dao.UsersDao;
import com.gura.step04.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{
	
	//의존 객체
	@Autowired
	private UsersDao dao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public ModelAndView signup(UsersDto dto) {
		//입력한 비밀번호를 암호화된 문자열로 얻어낸다.
		String hash=encoder.encode(dto.getPwd());
		//Dto 에 다시 넣어준다.
		dto.setPwd(hash);
		
		//Dao 를 이용해서 DB 에 저장
		dao.insert(dto);
		
		// 아이디를 ModelAndView 객체에 담아서 리턴해준다. 
		ModelAndView mView=new ModelAndView();
		mView.addObject("id", dto.getId());
		
		return mView;
	}

	@Override
	public boolean canUseId(String id) {
		//Dao 가 리턴해주는 값을 
		boolean canUse=dao.canUseId(id);
		//리턴해준다. 
		return canUse;
	}

	@Override
	public ModelAndView login(UsersDto dto, HttpServletRequest request) {
		//입력한 아이디를 이용해서 가입된 회원정보가 있는지 select 한다.
		UsersDto resultDto=dao.getData(dto.getId());
		
		//아이디 비밀번호가 유효한지 여부 
		boolean isValid=false;
		
		if(resultDto != null){ //DB 에 입력한 아이디가 존재한다면
			//비밀번호를 확인한다.
			boolean isMatch=encoder
				.matches(dto.getPwd(), resultDto.getPwd());
			if(isMatch){
				isValid=true;
			}
		}
		
		//원래 가야할 url
		String url=request.getParameter("url");
		
		ModelAndView mView=new ModelAndView();
		if(isValid){//아이디 비밀번호가 일치 한다면
			//로그인 처리를 해준다.
			request.getSession().setAttribute("id", dto.getId());
			mView.addObject("msg", 
					dto.getId()+" 님 로그인 되었습니다.");
			mView.addObject("url", url);
		}else{//아이디 혹은 비밀번호가 틀린 경우 
			mView.addObject("msg", "아이디 혹은 비밀번호가 틀려요");
			String location=request.getContextPath()+
					"/users/loginform.do?url="+url;
			mView.addObject("url", location);
		}
		
		return mView;
	}

	@Override
	public void update(UsersDto dto) {
		dao.update(dto);
	}

	@Override
	public ModelAndView delete(HttpSession session) {
		//로그인된 아이디를 읽어와서 
		String id=(String)session.getAttribute("id");
		dao.delete(id);//DB 에서 아이디 삭제
		//세션 초기화 (탈퇴와 동시에 로그아웃 되도록)
		session.invalidate();
		//ModelAndView 객체에 탈퇴한 회원의 아이디를 담아서 리턴 
		ModelAndView mView=new ModelAndView();
		mView.addObject("id", id);
		return mView;
	}

	@Override
	public ModelAndView detail(String id) {
		UsersDto dto=dao.getData(id);
		
		ModelAndView mView=new ModelAndView();
		mView.addObject("dto", dto);
		
		return mView;
	}

}









