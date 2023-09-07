package com.kosmo.springapp.basic.annotation;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelAttributeController {

	/*
	 방법1]맵으로 받기: 커맨드 객체(=DTO계열 클래스) 생성 불필요
	 			단, 체크박스처럼 하나의 파라미터명(키값이 됨)으로
	 			여러값이 넘어오면 Map으로 받기 때문에 하나만 받을 수 있다
	 */
	/*
	@RequestMapping("/Annotation/ModelAttribute.do")
	public String map(@RequestParam Map paramMap, Model model, @RequestParam String[] inter) {
		
		//데이터 저장
		paramMap.put("inter", Arrays.toString(inter));
		model.addAllAttributes(paramMap);
		//뷰정보 반환
		return "annotation04/ModelAttribute";
	}
	*/
	
	/*
	  방법2]커맨드객체로 받기 : @ModelAttribute 사용(생략 가능)
		  파라미터가 많은 경우 서블릿 API(HttpServletRequest)보다는
		  커맨드 객체 혹은 맵으로 받는게 유리
		  -단, 커맨드 객체(DTO계열)의 속성명과 파라미터명을 일치시켜야 한다.
	 
	  예] @ModelAttribute 커맨드객체타입 매개변수명
	 
	  ※@SessionAttribute와 함께 세션처리(인증)에서 주로 사용
	
	public Command() {
		System.out.println("Command의 생성자");
		//확인 누를 때마다 매번 Command의 생성자 호출됨
		//요청 들어올 때마다 계속 new하는 것, 싱글톤 디자인이 아님, 싱글톤이면 한번 호출 후 계속 덮어쓰기
	}
	*/
	
	/*
		Command.java확인
		아래 요청이 있을 때마다 Command객체가 새롭게 생성된다(즉, 싱글톤이 아니다)
		이 Command객체의 스프링컨테이너가 생명주기를 관리하지 않는다(스프링 컨테이너가 관리하지 않으면 싱글톤이 아님)
	
	*/
	
	@RequestMapping("/Annotation/ModelAttribute.do")
	//public String command(@ModelAttribute Command cmd, Model model) {//커맨드 객체 Command, 영역에 저장하니 Model필요
	public String command(Command cmd, Model model) {//@ModelAttribute 생략가능, Map으로 받을때 @RequestParam은 생략불가
		//데이터 저장
		//model.addAttribute(cmd); 이렇게 저장하면 안됨
		model.addAttribute("cmd", cmd);//디스패처서블릿이 "cmd"라는 이름으로 cmd 저장, command.java에서 cmd로 출력해야 나옴
		System.out.println("관심사항:"+cmd.getInter());
		//뷰정보 반환
		return "annotation04/ModelAttribute";
	}
	
	
	
	
	
	
}
