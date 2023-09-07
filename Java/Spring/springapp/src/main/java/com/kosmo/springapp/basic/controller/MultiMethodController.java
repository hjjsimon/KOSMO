package com.kosmo.springapp.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MultiMethodController {

	//컨트롤러 메소드(총 4개)
	@RequestMapping("/Controller/MultiMethod/List.do")
	public String list(ModelMap model) {//이런 인자도 있음
		//데이터 저장
		model.addAttribute("message", "It requests List");
		//디스패처 서블릿에게 뷰정보 반환
		return "controller01/Controller";//포워딩
	}
	@RequestMapping("/Controller/MultiMethod/Edit.do")
	public String edit(ModelMap model) {//이런 인자도 있음
		//데이터 저장
		model.addAttribute("message", "It requests Edit");
		//디스패처 서블릿에게 뷰정보 반환
		return "controller01/Controller";//포워딩
	}
	@RequestMapping("/Controller/MultiMethod/Delete.do")
	public String delete(ModelMap model) {//이런 인자도 있음
		//데이터 저장
		model.addAttribute("message", "It requests Delete");
		//디스패처 서블릿에게 뷰정보 반환
		return "controller01/Controller";//포워딩
	}
	@RequestMapping("/Controller/MultiMethod/View.do")
	public String view(ModelMap model) {//이런 인자도 있음
		//데이터 저장
		model.addAttribute("message", "It requests View");
		//디스패처 서블릿에게 뷰정보 반환
		return "controller01/Controller";//포워딩
	}
	
	
	
	
}
