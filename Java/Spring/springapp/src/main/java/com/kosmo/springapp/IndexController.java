package com.kosmo.springapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.springapp.basic.injection.Person;


//컨트롤러가 없으면 뷰 반환 불가, WEB-INF에 있는건 못봄

//일반 자바 클래스 형태 즉 POJO(Plain Old Java Object)
//컨트롤러 클래스: 사용자 요청처리 클래스, 현재 페이지 전송할 때 사용
@Controller//컴파일러에게 "아래 클래스는 사용자 요청을 처리하는 클래스야"라고 알려주는 역할
public class IndexController {
	
	public IndexController() {
		//System.out.println("indexController의 기본 생성자");
		//new 안해도 서버 실행시 알아서 @Controller붙은건 실행됨
		//서블릿은 레이지로딩, @Controller는 웹어플리케이션 시작시 바로 실행되는 pre로딩
	}
	
	@Autowired//주입받음, new 하지 않아도 됨 
	private Person person;//위의 생성자에서 person찍으면 nullpointer에러, 생성자 먼저 생기고 주입 받음
	
	//컨트롤러 메소드: 사용자 요청처리 메소드
	@RequestMapping("/controller.do") //get, post 다 처리가능함, 이 요청 들어오면 아래 메소드
	public String controller() {//무조건 public 반환(뷰정보 반환)은 99% String
		//디스패처 서블릿에게 뷰정보 반환
		System.out.println("person(IndexController):"+person);//com.kosmo.springapp.basic.injection.Person@547d6796
		return "controller01/Controller";//properties에 설정함 /WEB-INF/views/ 뒤에 .jsp 붙음, .jsp 여기에도 붙이면 404에러
	}
	
	@RequestMapping("/returntype.do")
	public String returntype() {
		//뷰정보 반환
		return "returntype02/ReturnType";
	}
	
	@RequestMapping("/injection.do")
	public String injection() {
		//뷰정보 반환
		return "injection03/Injection";
	}
	
	@RequestMapping("/annotation.do")
	public String annotation() {
		//뷰정보 반환
		return "annotation04/Annotation";
	}
	
	@RequestMapping("/properties.do")
	public String properties() {
		//뷰정보 반환
		return "properties05/Properties";
	}
	
	@RequestMapping("/database.do")
	public String database() {
		//뷰정보 반환
		return "database06/Database";
	}
	
	@RequestMapping("/validation.do")
	public String validation() {
		//뷰정보 반환
		return "validation07/Validation";
	}
	
	@RequestMapping("/mybatis.do")
	public String mybatis() {
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	
	@RequestMapping("/ajax.do")
	public String ajax() {
		//뷰정보 반환
		return "ajax10/Ajax";
	}
	
	@RequestMapping("/exception.do")
	public String exception() {
		//뷰정보 반환
		return "exception11/Exception";
	}
	
	@RequestMapping("/fileupdown")
	public String fileupdown() {
		//뷰정보 반환
		return "fileupdown12/FileUpDown";
	}
	
	@RequestMapping("/aop")
	public String aop() {
		//뷰정보 반환
		return "aop13/Aop";
	}
	
	@RequestMapping("/websocket")
	public String websocket() {
		//뷰정보 반환
		return "websocket14/Websocket";
	}
	
	/*
	[스프링 타일즈 적용시]- 컨트롤러 메소드에서 String으로 리턴시
	컨트롤러로 포워딩 혹은 리다이렉트 : 앞에 "forward:" 혹은  "redirect:"를 붙인다
	.jsp(UI가 필요한 jsp)로 포워딩 : .kosmo를 붙인다
	.jsp(UI가 필요없는 jsp(ex. message.jsp))로 포워딩: "forward:" 혹은  "redirect:"를 붙일때
	                           /WEB-INF/~로시작하는 전체 경로 그리고 .jsp를 붙인다
	*/
	@RequestMapping("/tiles")
	public String tiles() {
		//뷰정보 반환
		return "tiles15/Tiles.kosmo";//이렇게 만들면 .jsp 붙음, .kosmo아니라 탑, 푸터 안붙음
	}
	
	@RequestMapping("/teachable")
	public String teachable() {
		//뷰정보 반환
		return "teachable16/Teachable.kosmo";//이렇게 만들면 .jsp 붙음, .kosmo아니라 탑, 푸터 안붙음
	}
	
	
	
	
	
	
	
}
