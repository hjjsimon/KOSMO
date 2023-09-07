package com.kosmo.springapp.onememo.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.springapp.onememo.service.MemberDTO;
import com.kosmo.springapp.onememo.service.MemberService;

@Controller
@RequestMapping("/onememo/auth")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	//회원가입 처리
	@PostMapping("/MemberProcess.do")
	public String memberProcess(@ModelAttribute MemberDTO member, Model model) {
		
		memberService.joinMember(member);
		model.addAttribute("JoinMember","로그인해주십시오.");
		return "onememo/member/Login";
	}

	//회원정보 수정페이지 이동
	@RequestMapping("/MemberUpdate.do")
	public String memberUpdate(){
		
		return "onememo/member/MemberUpdate";
	}
	
	@RequestMapping("/MemberUpdateFinish.do")
	public String memberUpdateFinish(@ModelAttribute MemberDTO member, HttpServletRequest req) {
		
		memberService.updateFinishMember(member);
		return "onememo/member/Login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
