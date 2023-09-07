package com.kosmo.springapp.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class OneMethodParamController {

	@RequestMapping("/Controller/OneClassParam/Crud.do") //get, post 무관 처리가능한 것
	public String param(Model model, @RequestParam String crud) { //String 문자열로 된 뷰정보 반환, 이번엔 map말고 model에 담아보자
		//여기서도 HttpServletRequest req 인자로 받아서 getParameter해도 됨, 근데 스프링 프레임워크 철학은 틀만 맞추면 더 쉬운 것
		//@RequestParam는 요청할 때 넘기는 파라미터를 받는 것
		
		//모델계열(Model)에 데이터 저장
		switch(crud.toUpperCase()) {
			case "LIST": model.addAttribute("message", "Read All request"); break;
			case "EDIT": model.addAttribute("message", "Update request"); break;
			case "VIEW": model.addAttribute("message", "Read One request"); break;
			default: model.addAttribute("message", "Delete request");
		}
		
		//디스패처 서블릿에게 뷰정보 반환
		return "controller01/Controller";//포워딩
	}
}
