package com.kosmo.springapp.basic.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConstructorController {
	//@컨트롤러 붙어서 이미 생성됨, 생성되려면 Person필요, 이미 Person도 기본생성자로 생성
	//다 null인데, 가산동 20 가길동으로 초기화함, 필드생성자에서 인젝션 받는 것도 싱글톤이라 같은 주소의 Person객체라 세팅 안해도 잘 나옴

	//현재 클래스가 Person객체 필요(즉 Person객체 의존) - new하지 않고 생성자를 통해서 주입(원래 Person클래스 new 해줘야함)
	private Person person;
	
	//생성자 인잭션 - @Autowired 생략가능(생성자가 1개인 경우)
	//권장: 외부에서 변형 가능성이 없음
	//@Controller 붙어서 ConstructorController는 알아서 생성됨, 컨테이너가 생성,관리하는 빈, 외부에서 지금 클래스 new할 일 없음
	//세터인젝션은 변형가능성이 있음, 세터는 public으로 호출이 가능하기 때문
	@Autowired
	public ConstructorController(Person person) {//생성자인젝션 요청시 실행
		System.out.println("ConstructorController의 생성자");//생성자인젝션 -> 세터인젝션 -> 필드인젝션 순서로 실행됨
		//생성자 인자를 받아 인잭션한것, 여기서 new ConstructorController(Person) 한적 없음
		//빈에 등록한 Person을 넣어 세팅한것
		this.person = person;//받은 person을 현재 멤버에 넣음
		this.person.setAddr("가산동");//여기서 초기화함
		this.person.setAge("20");
		this.person.setName("가길동");
	}
	@RequestMapping("/Injection/Constructor.do")
	public String execute(Model model) {
		//데이터 저장
		model.addAttribute("personInfo",person);//속성명 personInfo로 저장
		//뷰정보 반환
		return "injection03/Injection";
	}
	
}
