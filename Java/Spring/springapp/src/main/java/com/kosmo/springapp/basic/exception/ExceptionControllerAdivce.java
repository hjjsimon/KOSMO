package com.kosmo.springapp.basic.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


//모든 컨트롤러에서 발생하는 예외를 처리(단,범위 지정가능)
//단,범위설정을 통해 특정 컨트롤러나 패키지 혹은 타입(예외타입)으로 범위를 제한 할 수 있다	
//@ControllerAdvice(basePackages = "com.kosmo.springapp.basic.ajax")//이 패키지안의 컨트롤러로 범위 제한
//@ControllerAdvice(assignableTypes = {ExceptionController.class})//이 타입의 컨트롤러에서 발생한 예외로 제한
@ControllerAdvice//지금은 지정 없음, 모든 컨트롤러의 예외 처리!
public class ExceptionControllerAdivce {

	@ExceptionHandler({NumberFormatException.class})
	public String numberFormat(Model model, Exception e) {
		//데이터 저장
		model.addAttribute("message","나이는 숫자만 입력하세요(전역적 예외처리)");//다른 컨트롤러에서 에러 발생도 이제 가능!(단, 지역이 항상 우선함)
		//뷰정보 반환
		return "exception11/Exception";
	}
	
	//파일 최대 업로드 용량 에러 처리
	//FileUpDownController.java에서 옮김
	//방법1- 페이지 반환: 페이지 넘기는거라 에러시 입력 데이터가 다 사라짐, 파라미터 전달안되기때문!!(아래 null확인) 
	/*
	@ExceptionHandler(MaxUploadSizeExceededException.class)//tomcat꺼임 
	public String maxUploadError(Model model, HttpServletRequest req) {
		model.addAttribute("maxUploadSizeError", "파일 최대 업로드 용량을 초과했어요");
		System.out.println("파라미터:"+req.getParameter("title"));//파라미터:null
		//포워드 안됨
		return "fileupdown12/FileUpDown";
	}
	*/
	
	//방법2- 응답바디에 자바스크립트로 출력: 입력 데이터 유지가능(history.back())
	/*
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public @ResponseBody String maxUploadError(HttpServletResponse resp) {
		resp.setContentType("text/html; charset=UTF-8");//이거 해야 아래 한글 안깨짐
		return "<script>alert('최대 업로드 용량 초과');history.back();</script>";
	}
	*/
	
	//방법3
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<String> maxUploadError() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.header(HttpHeaders.CONTENT_TYPE, "text/plain;charset=UTF-8")
				.body("<script>alert('최대 업로드 용량 초과했어요');history.back();</script>");
	}
	
	
	//예외 종류별로 계속 메소드 추가
	
	@ExceptionHandler({Exception.class})//모든 종류 예외 처리
	public String other(Exception e) {
		//뷰정보 반환
		return "exception11/ExceptionInfo";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
