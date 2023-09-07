package com.kosmo.springapp.basic.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.springapp.onememo.service.impl.OneMemoMapper;

/*
	Talend로 http://localhost:9090/users에 POST요청, 바디에 JSON형식 값 보냄
	스프링 시큐리티의 PasswordEncoder로 비밀번호 암호화해서 SPRING/SPRING 계정 
	USERS 테이블에	비밀번호가 암호화되어 저장됨
	
	enable 컬럼이 있음 0이면 비활성화, 로그인 불가하게 가능함
 */


@RestController
public class UsersRestController {

	@Autowired
	private OneMemoMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
//	@PostMapping("/users")
	@GetMapping("/users")
	private @ResponseBody Map insertUser(@RequestBody Map<String,String> map) {
		
		//사용자가 입력한 비밀번호를 암호화
		String rawPassword=map.get("pass");
		String encodedPassword=passwordEncoder.encode(rawPassword);//원문을 암호화
		//암호화된 비밀번호로 다시 설정
		map.put("pass", encodedPassword);
		System.out.println("비밀번호 원문:"+rawPassword);
		System.out.println("암호화된 비밀번호:"+encodedPassword);
		System.out.println("암호일치여부 판단"+passwordEncoder.matches(rawPassword, encodedPassword));
		int affected=mapper.saveUser(map);	
		System.out.println(affected+"행이 입력되었어요");
		return map;
	}
}