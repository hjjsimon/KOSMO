package com.kosmo.springapp.basic.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/*
		1) @ControllerAdvice 어노테이션
		 -클래스위에 붙인다
		 -전역적인 예외처리다(모든 컨트롤러에서 발생하는 예외를 처리가능)
		 -@Service(서비스레이어에 붙임)나 @Repository(DAO같은 리포지터리에 붙임)역할을 하는 클래스의 예외처리는 안된다
		 -클래스의 각 메소드위에 @ExceptionHandler를 붙여 예외처리를 실제적으로 한다
		
		2) @ExceptionHandler 어노테이션
		 - 해당 컨트롤러에서 발생하는 예외를 처리할때 즉 지역적이다
		 - 예외 발생시 처리할 로직 메소드 위에 붙인다
		 - @ControllerAdvice보다 우선순위가 높다
	 */

@Controller
public class ExceptionController {

	//@ExceptionHandler 사용 - 예외처리하려는 모든 컨트롤러마다 작성해야함
	//@ExceptionHandler({예외1.class, 예외2.class, ...})
	//@ExceptionHandler(NumberFormatException.class)
	
	/*이거 예외처리시 ExceptionControllerAdvice.java에서 처리한 전역에러처리 페이지로 이동함
	//방법1] String 사용 : jsp 페이지로 반환하는 방법
	@ExceptionHandler({NumberFormatException.class})///////////예외명
	public String numberFormat(Model model, Exception e) {
		//데이터 저장
		model.addAttribute("message","나이는 숫자만 입력하세요(지역적 예외처리)");//다른 컨트롤러에서 에러 발생은 처리 불가
		//뷰정보 반환
		return "exception11/Exception";
		
	}*/
	
	//방법2] ResponseEntity<T> 사용
	//ResponseEntity<T>: 이때 T는 응답바디에 쓸 컨텐츠의 타입이다, String 말고 이 반환타입 사용
	@ExceptionHandler({NullPointerException.class})
	public ResponseEntity<String> nullPointer() {
		
		/*방법2-1]
		//응답헤더 설정
		HttpHeaders headers = new HttpHeaders();//org꺼 HttpHeaders 임포트
		headers.set("Content-Type", "text/plain;charset=UTF-8");//첫번째인자: 응답헤더명
		return new ResponseEntity<>("널 입니다", headers, HttpStatus.INTERNAL_SERVER_ERROR);
		//첫번째인자: T body
		//두번째인자: 헤더명
		//세번째인자: 상태코드, 현재 메소드 안에서 에러 발생이라 500, 이거에 맞는게 HttpStatus.INTERNAL_SERVER_ERROR
		*/
		
		//방법2-2] 권장
		//ResponseEntity 객체 반환 - 메소드 체인 형식(순서는 상태코드().응답헤더().응답바디())
		return ResponseEntity.status(HttpStatus.NOT_EXTENDED)
				.header(HttpHeaders.CONTENT_TYPE, "text/plain;charset=UTF-8")
				.body("It is Null");
	}
	
	
	@PostMapping("/Exception")//원래 요청 /Exception.do인데 부트는 .do없어도됨 (레거시는 절대 금지 404에러)
	public String handler(@RequestParam int years, Model model) {//form이니까 무조건 @RequestParam
		
		//메소드 안에 발생하는건 try~catch하면됨
		//근데 @RequestParam int years 이런거 에러는 try~catch불가, 어노테이션 처리해야함
		//데이터 저장
		model.addAttribute("message","당신의 10년후 나이는"+(years+10));
		
		//NullPointerException 발생 테스트용 코드(반드시 숫자 넣자)
//		String nullString = null;
//		nullString.length();
		
		//뷰정보 반환
		return "exception11/Exception";
		
	}
	
	
}
