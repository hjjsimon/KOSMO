package com.kosmo.springapp.basic.injection;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FieldController {

	//필드 인젝션
	//외부에서 변형이 불가능(접근지정자 private이니까)
	//Person이 싱글톤으로 생성되므로 ConstructorController가 생성될 때 이름,나이,주소가 초기화된 Person객체가 필드로 주입됨
	//여기서 또 Person을 초기화하지 않아도 필드 인젝션 클릭하면 kbd로 [이름:가길동,주소:가산동,나이:20] 출력됨
	@Autowired
	private Person person;//지금 null이라 안됨, @Autowired해주면 필드인젝션으로 됨
	//생성자인젝션 -> 세터인젝션 -> 필드인젝션 순서로 실행됨
	
	@RequestMapping("/Injection/Field.do")
	public String execute(Map map) {
		//데이터 저장
		map.put("personInfo", person);
		//뷰정보 반환
		return "injection03/Injection";
	}
	
	
}
