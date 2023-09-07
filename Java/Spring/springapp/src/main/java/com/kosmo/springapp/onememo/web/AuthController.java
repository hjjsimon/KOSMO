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
//@SessionAttributes({"id"})//세션으로 로그인 처리하니까 붙여야함, DTO는 안만들 것, 스프링 시큐리티 적용시는 주석, 세션에 id키로 저장되지않아 로그인시 다르게 해야함
@RequestMapping("/onememo/auth")
public class AuthController {
	
	//※스프링 시큐리티 적용시 서비스 주입 /로그인처리 /로그아웃처리를 주석(할 필요 없다는 뜻, 시큐리티가 알아서 해줌)
	
	//서비스 주입(시큐리티는 주석)
//	@Autowired//필드 인젝션(생성자 만들기 귀찮아서 이게 편함)
//	private OneMemoServiceImpl memoService;
	
	//로그인 폼으로 이동(스프링 시큐리티 대비해서 @GetMapping 아닌 @RequestMapping 사용)
	@RequestMapping("/Login.do")
	public String login() {
		return "onememo09/member/Login";//.jsp는 접미사로 빠짐, 접두사도 확인해봐
		//.kosmo 붙이면 jsp include top, footer 날려도 됨
	}
	
	/*
	//로그인처리
	@PostMapping("/LoginProcess.do")
	public String process(@RequestParam Map map, Model model 
			//, SessionStatus status
			) {
		//※컨트롤러 3개 역할 꼭 기억
		//1)서비스 호출
		boolean isMember = memoService.isLogin(map);
		//2)데이터 저장(session에서 저장, model 쓰면 request 영역에도 저장)
		
//		model.addAttribute("id", map.get("id"));
//		if(isMember) {//막 저장되는게 단점, 회원 아닌 경우 삭제해줌
//			status.setComplete();//회원이 아닌 경우 삭제
//			model.addAttribute("NotMember","아이디와 비번 불일치");
//		}
		
		//2-1)그냥 회원일 때만 저장하자!(DTO,model 저장은 다 저장되는게 단점)
		if(isMember)
			model.addAttribute("id", map.get("id"));
		else
			model.addAttribute("NotMember","아이디와 비번 불일치");
		//3)뷰정보 반환
		return "onememo09/member/Login";
	}

	//로그아웃 처리
	@GetMapping("/Logout.do")//모르겠다 싶으면 리퀘스트 매핑 하면 됨
	public String logout(SessionStatus status) {
		status.setComplete();
		//뷰정보 반환
		return "onememo09/member/Login";
	}
	*/
	
	
	
}
