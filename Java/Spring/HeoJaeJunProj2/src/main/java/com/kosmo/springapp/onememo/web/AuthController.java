package com.kosmo.springapp.onememo.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kosmo.springapp.onememo.service.impl.OneMemoServiceImpl;

@Controller
@SessionAttributes({"id"})//세션으로 로그인 처리
@RequestMapping("/onememo/auth")
public class AuthController {
	
	@Autowired
	private OneMemoServiceImpl memoService;
	
	//로그인 폼으로 이동
	@RequestMapping("/Login.do")
	public String login() {
		return "onememo/member/Login";
	}
	
	@RequestMapping("/LoginProcess.do")
	public String process(@RequestParam Map map, Model model) {

		//1)서비스 호출
		boolean isMember = memoService.isLogin(map);
		//2)데이터 저장(session에서 저장, model 쓰면 request 영역에도 저장)
		// 회원일 때만 저장하자!(DTO,model 저장은 다 저장되는게 단점)
		if(isMember) {
			model.addAttribute("id", map.get("id"));
			return "redirect:/onememo/bbs/List.do";
		}
		else
			model.addAttribute("NotMember","아이디와 비밀번호가 일치하지 않습니다.");
		//3)뷰정보 반환
		return "onememo/member/Login";
	}

	//로그아웃 처리
	@GetMapping("/Logout.do")//모르겠다 싶으면 리퀘스트 매핑 하면 됨
	public String logout(SessionStatus status) {
		status.setComplete();
		//뷰정보 반환
		return "onememo/member/Login";
	}
	
	@RequestMapping("/List.do")
	public String list() {
		return "redirect:/onememo/bbs/List.do";
	}
	
	
}
