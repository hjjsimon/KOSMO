package com.kosmo.springapp.basic.injection;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InjectionController {
	
	//@Autowired
	private Person person;  
	//위를 그대로 카피해서 다른곳에 넣어도 똑같이 실행, 주소가 동일함, 똑같이 Person을 주입받음 
	
	@Autowired//생성자 인젝션으로 주입받음, new 하지 않아도 됨, 생성자 1개는 주석처리 가능
	public InjectionController(Person person) {
		super();
		this.person = person;
	}
	
	
	@RequestMapping("/Injection/Injection.do")
	public String execute(@RequestParam Map<String, String> map, Map model) {//파라미터 받은 맵, 데이터 저장 위한 맵(Model이나 ModelMap해도 됨)

		//데이터 저장]
		//사용자 입력값으로 person 설정
		//현재 가길동 님, 20 세, 가산동 거처 -> 사용자 입력값으로 person 재설정
		person.setName(map.get("name"));//파라미터명이 키
		person.setAddr(map.get("addr"));
		person.setAge(map.get("age"));
		model.put("personInfo", person);
		
		//System.out.println("person(InjectionController):"+person);//com.kosmo.springapp.basic.injection.Person@547d6796
		//Person 객체 1개를 생성해서 여기저기에 다 뿌려줌, 그래서 indexController.java, InjectionController.java 똑같이 주소 나오는 것
		//뷰정보 반환
		return "injection03/Injection";
	}
	
	

}
