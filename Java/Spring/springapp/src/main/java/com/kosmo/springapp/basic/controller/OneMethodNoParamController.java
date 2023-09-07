package com.kosmo.springapp.basic.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//컨트롤러 클래스로 만듦
public class OneMethodNoParamController {

	//컨트롤러 메소드
	/*
	 패스파라미터란 URL패스에 파라미터를 포함시켜 값을 전달하는 방식이다
	 설정: URL패턴의 변하는 부분을 {변수명}로 변수처리
	 읽기: 패스 파라미터로 값을 받을 때는 @PathVariable String 변수명으로 받는다 
	 */
	//URL패턴의 변하는 부분을 {변수명(ex.path)}으로 변수처리: {변수명}을 패스 파라미터 라고 한다
	//?~~어쩌구하는 쿼리스트링은 패스에 포함되지 않음
	//패스 파라미터란 URL패스에 서버에서 받은 파라미터를 포함시켰다라는 의미다(ex. 쿼리스트링의 ?name=가길동 같은 name이 파라미터)
	//패스 파라미터로 값을 받을 때는 @PathVariable 타입 변수명으로 받는다(아래 메소드의 인자)
	@RequestMapping("/Controller/OneClass/{path}")//스프링은 {}변수처리, 여기 부분만 다르니까 변수로 다 해결해버리겠다~!
	public String noparam(@PathVariable String path, Map map) {//List.do 하면 이게 path에 그대로 들어옴
		
		System.out.println(path);//클릭시 콘솔에 List.do 등 출력
		//모델 계열(Map)에 데이터 저장: request영역에 저장됨
		//디스패처 서블릿이 빈 Map 껍데기를 줌, 거기에 담으면 됨
		switch(path.toUpperCase()) {
			case "LIST.DO": map.put("message", "목록요청 입니다"); break;
			case "EDIT.DO": map.put("message", "수정요청 입니다"); break;
			case "VIEW.DO": map.put("message", "상세보기요청 입니다"); break;
			default: map.put("message", "삭제요청 입니다");
		}
		
		//디스패처 서블릿에게 뷰정보 반환
		return "controller01/Controller";//포워딩
	}
	
}
