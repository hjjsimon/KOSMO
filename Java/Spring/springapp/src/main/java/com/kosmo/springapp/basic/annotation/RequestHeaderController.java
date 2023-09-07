package com.kosmo.springapp.basic.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestHeaderController {

	@RequestMapping("/Annotation/RequestHeader.do")
	public String exec(Model model, 
			@RequestHeader(
					defaultValue = "요청헤더명이 존재하지 않아요",
					required = false,
					value = "user-agent") String userAgent) {
		//안들어올 수도 있으니 required(필수) = true -> false로 해서 에러방지해줌
		//요청헤더에 user-agent 있음, 브라우저 정보 알려줌
		//데이터 저장
		model.addAttribute("userAgent",userAgent);
//Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 출력
		//뷰정보 반환
		return "annotation04/Annotation";
	}
	
	
	
	
}
