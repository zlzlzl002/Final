package com.gura.step04.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil implements PasswordEncoder{
	//맴버필드
	private BCryptPasswordEncoder encoder;
	
	//생성자
	public PasswordUtil(){
		encoder=new BCryptPasswordEncoder();
	}
	
	//암호화된 문자열을 리턴하는 메소드
	@Override
	public String encode(CharSequence pwd) {
		String hash=encoder.encode(pwd);
		return hash;
	}
	//입력한 문자열과 암호화된 문자열이 일치하는지 여부를 리턴하는 메소드
	@Override
	public boolean matches(CharSequence pwd, String hash) {
		//일치 여부를 boolean type 으로 얻어내기 
		boolean isMatch=encoder.matches(pwd, hash);
		return isMatch;
	}
}












