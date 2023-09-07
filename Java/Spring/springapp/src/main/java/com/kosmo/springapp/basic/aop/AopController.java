package com.kosmo.springapp.basic.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AopController {
	
	//@Component로 등록된 CrossCuttingConcern빈을 주입받음
	@Autowired
	private TargetClass target;
	
	@GetMapping("/AOP/Aop")
	public String handle(Model model) {
		long total=target.getTotal();
		model.addAttribute("total", total);		
		return "aop13/Aop";
	}
	
	
}
