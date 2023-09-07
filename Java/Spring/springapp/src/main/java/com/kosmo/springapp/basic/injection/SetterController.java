package com.kosmo.springapp.basic.injection;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SetterController {

	
	//@Autowired //필드인젝션 필요없음 세터로 할거임 
	private Person person;//지금 null이라 안됨, @Autowired해주면 필드인젝션으로 됨
	
	//세터 인젝션
	//주입받은 값을 가공해서 속성에 넣을때 사용
	//단, 외부에서 변형가능성이 있다(setter는 public이라 다른게 SetterController주입받아 setter호출하면 변형가능)
	@Autowired//이거 안붙이면 null이라 출력안됨, 생성자가 1개일때는 생략가능
	public void setPerson(Person person) {//person이 주입돼서 this.person을 바꿈, SetterController 생성될때 SetPerson도 호출됨
		System.out.println("SetterController의 세터");//ConstructorController생성자 먼저 뜨고 세터 출력, 그래서 님, 세 이런거 붙어서 나옴
													//생성자인젝션 -> 세터인젝션 -> 필드인젝션 순서로 실행됨
		this.person = person;
		//주입받은 Person객체 가공하자
		this.person.setName(this.person.getName()+" 님");//가져와서 님 붙임
		this.person.setAge(this.person.getAge()+" 세");
		this.person.setAddr(this.person.getAddr()+" 거처");
	}
	
	
	@RequestMapping("/Injection/Setter.do")
	public String execute(Map map) {
		//데이터 저장
		map.put("personInfo", person);
		//뷰정보 반환
		return "injection03/Injection";
	}
	
	
}
