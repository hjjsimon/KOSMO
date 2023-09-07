package com.kosmo.springapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloSpringController {//디스패처서블릿의 컨트롤러 -> static폴더보다 templates폴더에서 찾는걸 우선한다

	@GetMapping("/")//패스 없이 루트 요청은 "/" 하면 됨, get요청, 루트(=webapp)요청이 오면 아래 메소드 실행, index로 포워드됨
	public String index(Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("today", dateFormat.format(new Date()));//model에 데이터 저장
		return "index";
		//index라는 뷰정보를 받았는데 뭐하는 놈임? 하면 뷰리졸버가 resources에서 index.html이야 하고 ${today}에 데이터 랜더링, 그리고 사용자에게 보여줌, 포워드 시킴
		//포워드 시킬 페이지 못찾으면 500에러 나옴
		/*
		#내장 톰캣서버 포트 설정(디폴트: 8080) 주석처리는 샵, 타임리프 설정 안했지만 디폴트로 된것, 사용자가 localhost:9090뒤에 /~~ 패스 없이 요청시 이 요청을 디스패처 서블릿이 받음
		#그리고 핸들러 매핑한테 루트요청 받은걸 어느 컨트롤러에 보낼지 물어봄, 얘가 HelloWorld컨트롤러에 보내야해 라고 함, 루트요청이 헬로우월드인건 핸들러매핑이 세팅함
		#GetMappin("/") 하면 HelloWorld찾아감, model.attribute는 리퀘스트영역에 투데이 저장한거랑 똑같음, 그리고 return "index"; 는 포워드랑 동일
		server.port=9090
		
		MVC패턴에서 Model은 데이터거나 업무처리 로직을 갖고있음, 그 model에 데이터를 저장
		
		@컨트롤러가 있으면 컨트롤러가 우선, 문자열의 파일명을 templates에서 찾음, 
		// 주석처리로 @컨트롤러 없으면 static에서 찾음 -> static의 index.html이 페이지에 나옴
		*/
		
	}
	
}
