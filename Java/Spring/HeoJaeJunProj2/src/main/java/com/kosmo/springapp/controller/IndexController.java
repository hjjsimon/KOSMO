package com.kosmo.springapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("today")
public class IndexController {

	@GetMapping("/")
	public String index(Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("today", dateFormat.format(new Date()));
		return "/onememo/member/Login";//시작 로그인 페이지 반환
	}
	
}

