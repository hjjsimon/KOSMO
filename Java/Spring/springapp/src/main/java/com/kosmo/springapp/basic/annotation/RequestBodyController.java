package com.kosmo.springapp.basic.annotation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
	1) @RestController :주로 데이타만 보낼때(@Controller + @ResponseBody와 같다)
	
	2) @Controller : 주로 페이지를 보낼때
	   @RequestBody : 클라이언트로부터 요청시 JSON형식의 데이타 받을 때 사용.
		   		      스프링 부트는 내장되어 있다(Jackson라이브러리)
				      스프링 레거시는 내장되어 있지 않다
	
	cf) Jackson라이브러리 : 자바 객체(DTO계열 혹은 컬렉션)를 JSON형식(자스 객체)으로
	                    JSON형식(자스 객체)을 자바객체로 변환시켜주는 라이브러리
	                  
	※ 결론: 1] form태그, a태그로 보내는 데이터 : @RequestParam 또는 커맨드 객체로 데이터 받기 ->로그인버튼 눌러서 확인
		   2] JSON형식으로 보내는 데이터 : @RequestBody 커맨드 또는 Map으로 데이터 받기 ->JSON데이타 a태그 눌러서 확인
		   
	1] @RequestParam 혹은 커맨드 객체로 데이타 받기(지금까지 form,a태그만 썼었음, 그래서 @RequestParam, 커맨드객체 문제없었음)
	   1)Form태그 및 A링크로 보내는 데이타(KEY=VALUE 쌍형태: id=KIM&pwd=1234) : 
			정상적으로 받는다(form태그는 페이지 받는거임)
	   2)자바스크립트로 JSON형식의 데이타({KEY:VALUE}: {"id":"KIM","pwd":"1234"}) : 
	    	정상적으로 못받는다, 즉 클라이언트에서 보내는 JSON데이터를 못받아 null출력(키=밸류 쌍이 아닌 형태)
	    	
	2] @RequestBody 커맨드 객체변수 혹은 @RequestBody Map 변수 형식으로 데이터 받기(단, @RequestBody String 변수로 받아도 되나 파싱해야 한다)
	   1)Form 및 A링크로 보내는 데이타 : 
	   		415에러:Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported)(400대는 요청에러임)
	   2)JSON 형식 데이타 : 
	   		정상적으로 받는다 즉 JSON 형식데이타는 무조건 @RequestBody 사용하자.(JSON객체는 아니고 JSON을 stiringify하여 JSON형식인 데이타)
*/

@Controller//이건 페이지 보내는거, 데이터 보내려면 @ResponseBody추가로 써야함
public class RequestBodyController {

	//자바는 JSON 형식을 이해못함,
	@RequestMapping("Annotation/RequestBody.do")
	@ResponseBody
	/*
	//1-1]테스트: @RequestParam으로 받을때
	//form태그의 키=밸류쌍: 정상
	//자바스크립트의 JSON형식의 데이터: 못받고 null나옴(단,키=밸류 형식은 잘 받음) 
	public String requestParam(@RequestParam Map paramMap) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Map jmap = new HashMap();
		jmap.put("id", paramMap.get("id"));//id넘어온걸 "id"로 저장
		jmap.put("pwd", paramMap.get("pwd"));
		return mapper.writeValueAsString(jmap);
		//지금은 아니지만 JSON을 String으로 보낼 때는 키나 밸류를 ""로 감싸서 "{}" 해야함(매우 중요)
		//로그인 누르면 페이지전송 안되고 응답바디가 문자열로 브라우저에 바로 출력
	}
	*/
	
	/*
	//1-2]테스트: 커맨드 객체로 받을때
	//form태그의 키=밸류쌍: 정상
	//자바스크립트의 JSON형식의 데이터: 못받고 null나옴(단,키=밸류 형식은 잘 받음) 
	public String command(AuthenticationCommand auth) throws JsonProcessingException {
		System.out.println("아이디:"+auth.getId());
		ObjectMapper mapper = new ObjectMapper();
		Map jmap = new HashMap();
		jmap.put("id", auth.getId());//id넘어온걸 "id"로 저장
		jmap.put("pwd", auth.getPwd());
		return mapper.writeValueAsString(jmap);
		//지금은 아니지만 JSON을 String으로 보낼 때는 키나 밸류를 ""로 감싸서 "{}" 해야함(매우 중요)
		//로그인 누르면 페이지전송 안되고 응답바디가 문자열로 브라우저에 바로 출력
	}
	*/
	/*
	//2-1]테스트: @RequestBody 커맨드객체로 받을때(이때는 뒤에 커맨드객체, 맵 다 됨)
	//form태그의 키=밸류쌍: 415에러
	//자바스크립트의 JSON형식의 데이터: 잘 받음
	public String requestBody(@RequestBody AuthenticationCommand auth) throws JsonProcessingException {
		System.out.println("아이디:"+auth.getId());
		ObjectMapper mapper = new ObjectMapper();
		Map jmap = new HashMap();
		jmap.put("id", auth.getId());//id넘어온걸 "id"로 저장
		jmap.put("pwd", auth.getPwd());
		return mapper.writeValueAsString(jmap);
		//지금은 아니지만 JSON을 String으로 보낼 때는 키나 밸류를 ""로 감싸서 "{}" 해야함(매우 중요)
		//로그인 누르면 페이지전송 안되고 응답바디가 문자열로 브라우저에 바로 출력
	}
	*/
	/*
	//2-2]테스트: @RequestBody 맵으로 받을때(이때는 뒤에 커맨드객체, 맵 다 됨)
	//form태그의 키=밸류쌍: 415에러
	//자바스크립트의 JSON형식의 데이터: 잘 받음
	public String requestBody(@RequestBody Map paramMap) throws JsonProcessingException {
		System.out.println("아이디:"+paramMap.get("id"));
		ObjectMapper mapper = new ObjectMapper();
		Map jmap = new HashMap();
		jmap.put("id", paramMap.get("id"));//id넘어온걸 "id"로 저장
		jmap.put("pwd", paramMap.get("pwd"));
		return mapper.writeValueAsString(jmap);
		//지금은 아니지만 JSON을 String으로 보낼 때는 키나 밸류를 ""로 감싸서 "{}" 해야함(매우 중요)
		//로그인 누르면 페이지전송 안되고 응답바디가 문자열로 브라우저에 바로 출력
	}
	*/
	//※2-1,2]의 경우 String 대신 커맨드 객체나 맵 컬렉션을 반환하면 된다(권장)*******************
	//이게 다 Jackson라이브러리 때문에 가능한 것 객체->JSON으로 바꾼다!
	/*
	public Map requestBody(@RequestBody Map paramMap){
		return paramMap;
	}
	*/
	public AuthenticationCommand requestBody(@RequestBody AuthenticationCommand auth){
		return auth;
	}
	
	
	
	
	
	
	
	
	
	
}
