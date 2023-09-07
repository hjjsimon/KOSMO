package com.kosmo.springapp.basic.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
	@ResponseBody : 뷰 리졸버를 거치지 않고 직접 브라우저로 출력시 사용.
					페이지가 아닌 데이타 전송시에 사용한다
	                반환 타입은 String으로 한다.(String이 응답바디에 쓰임)
	                데이타 전송 예는 ReturnTypeController.java 참조
	                ※반환되는 문자열이 응답바디에 쓰인다
	                
	@Controller + @ResponseBody는 @RestController(데이터 보냄)와 같다
*/
@Controller
public class ResponseBodyController {

	//스프링 레거시 한글깨짐 처리: produces = "text/html; charset=UTF-8" jsp맨위에서 가져온거 추가
	//@RequestMapping(value="/Annotation/ResponseBody.do",produces="text/html; charset=UTF-8")
	//스프링 부트는 위 내용 무관, 한글 안깨짐, 불필요
	@RequestMapping("/Annotation/ResponseBody.do")
	@ResponseBody 
	public String exec() {
		return "<script>alert('@ResponseBody로 직접 출력합니다');history.back();</script>";
	}
	
}
