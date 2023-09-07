package com.kosmo.springapp.basic.returntype;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ReturnTypeController {
	
	//데이터나 로직을 갖는 객체가 Model, 뿌려주는게 View, ModelAndView는 데이터와 뷰정보 동시 저장가능
	/*
	 주로 뷰정보(HTML페이지) 반환시는 반환타입을 String
	 HTML페이지가 아닌 데이터 반환시는 반환타입을 객체(좁은의미 자바빈 혹은 컬렉션)로 한다
	 [컨트롤러 메소드]반환타입-ModelAndView : ModelAndView에 뷰정보와 데이터정보 저장해서 반환	 
	*/
	//뷰 정보 설정시 : ModelAndView객체.setViewName(String)일때 접두사, 접미사가 붙는다
	//				ModelAndView객체.setView(View객체)일때 접두사, 접미사가 붙지않는다
	@RequestMapping("/ReturnType/ModelAndView.do") //ReturnType.jsp처리
	public ModelAndView modelAndView(@RequestParam Map map, Model model) {
		//JSP에서 getParameter는 String저장, getParameters는 String[]저장, getParameterMap은 키,밸류 저장
		//@RequestParam Map map 이것도 파라미터맵과 동일함
		
		//방법1] ModelAndView객체에 뷰정보만 저장해서 반환
		//데이터 저장-Model에 데이터 저장
		//map에 파라미터 다 저장함, 그리고 Model객체에 저장
		/*
		model.addAllAttributes(map);//넘긴 파라미터명이 키, 디스패처서블릿이 알아서 키를 속성명으로 리퀘스트영역에 저장
									//파라미터명(returnType)이 속성명이 되어 리퀘스트 영역에 저장된다
									//{requestScope.returnType} 출력됨
									//포워드로 보내니까 ${param.returnType}도 출력됨
		model.addAttribute("message", map.get("returnType"));//message에 뿌려줌 
		return new ModelAndView("returntype02/ReturnType");
		//ModelAndView(viewName)는 뷰정보를 반환, 뷰리졸버에게 뷰정보 물어보고나서 뷰객체를 사용자에게 응답해줌
		*/
		
		//방법2] ModelAndView객체에 뷰정보와 데이터 저장해서 반환 - Model인자는 필요없다
		ModelAndView mav = new ModelAndView();
		//데이터 저장
		mav.addAllObjects(map);//model.addAllAttributes(map) 동일
		mav.addObject("message", map.get("returnType"));//model.addAttribute("message", map.get("returnType")) 동일
		//뷰정보 저장
		//방법2-1]
		//mav.setViewName("returntype02/ReturnType");//String으로 이름 저장, String일때는 접두사,접미사 붙음
		//방법2-2]
		mav.setView(new InternalResourceView("/WEB-INF/views/returntype02/ReturnType.jsp"));
		//스프링레거시는 jsp가 기본 뷰, 스프링부트에서는 기본 아니라 쓸일없음, 포워드할 URL
		//InternalResourceView객체로 뷰정보 설정시 반드시 뷰템플릿(JSP)의 풀경로를 설정해줘야한다
		//뷰 리졸버의 접두사,접미사가 붙지 않기 떄문
		//webapp가 컨텐츠루트(기본경로), 뷰객체로 저장시에는 접두사,접미사 안붙음 
		
		return mav;
	}
	
	//[컨트롤러 메소드]반환타입-String: 뷰 정보만 반환, 즉 HTML페이지를 브라우저로 전송
	//무조건 접두사,접미사가 붙는다
	@RequestMapping("/ReturnType/String.do")
	public String string(@RequestParam String returnType, Map map) {//파라미터 1개니까 맵으로 받을 필요 없음, 근데 변수명 정확히 일치시켜야함
		//데이터 저장(이것만 하면 됨)
		map.put("message", returnType);//파라미터 그대로 returnType에 저장됨
		map.put("returnType", returnType);
		
		//디스패처서블릿에게 뷰정보 반환, jsp가 html로 바뀌어 사용자에게 보내짐
		return "returntype02/ReturnType";
	}
	
	//[컨트롤러 메소드]반환타입-void: @Controller어노테이션 사용시 페이지가 아닌 데이터를 보낼때
	//이번에는 뷰, 데이터정보가 디스패처서블릿에게 반환되지 않음, 뷰리졸버에게도 당연히 질문안함
	//컨트롤러에서 디스패처서블릿 들리지 않고 바로 브라우저로 데이터 전송, 뷰리졸버가 동작하지 않는다
	
	@RequestMapping("/ReturnType/Void.do")
	public void noreturn(@RequestParam String returnType, HttpServletResponse resp) throws IOException {
		/*
		 Ajax나 Rest API구현시 주로 사용(단,void보다는 객체반환을 주로 사용한다)
		 디스패처 서블릿에게 모델 및 뷰정보 전달 안하므로 뷰리졸버 거치지 않음
		 웹브라우저에 바로 데이터 출력시 사용
		 즉 Http응답바디에 데이터 출력시 사용
		 */
		//서블릿API쓰면 HttpServletResponse resp 알아서 주어짐, 
		resp.setContentType("application/json");//contentType="text/html; charset=UTF-8" 원래 jsp 맨 위에 이거였음
		//웹브라우저에 출력하기 위한 출력스트림 얻기
		//컨트롤러 메소드는 예외를 던질 수 있다, 걍 던져도 됨
		PrintWriter out = resp.getWriter();
		out.print(String.format("{\"%s\":\"%s\", \"%s\":\"%s\", \"%s\":\"%s\" }", 
						"username","KOSMO",
						"password","KOSMO1234",
						"parameter",returnType));
		//이때는 키 밸류 다 "" 써주는게 좋대, {} JSON형태로 나옴
		out.close();
	}
	/*
		매우 중요한 내용!!!!!!!!!!!!!!!
		[컨트롤러 메소드]반환타입-객체 혹은 String(JSON으로 자동으로 변환해서 브라우저로 전송) : @Controller어노테이션과 @ResponseBody - 페이지가 아닌 데이타를 보낼때
		컨트롤러에서 디스패처서블릿 들리지 않고 바로 브라우저로 데이타 전송, 뷰리졸버가 동작하지 않는다
		뷰 리졸버 대신 HttpMessageConverter가 작동한다(응답메세지 변환해줌)
		String반환시는  StringHttpMessageConverter가 작동해서 JSON으로 변환 처리
		객체 반환시는 JSON으로 변환하는 라이브러리가 작동해서 JSON으로 변환 처리(단,스프링부트는 JACKSON이 포함되어있음)
		예를들면 MappingJackson2HttpMessageConverter
		
		https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.15.2
		원래는 여기서 JACKSON 다운받아 설치해야함
	*/
	
	@RequestMapping("/ReturnType/Object.do")
	@ResponseBody
	//1)String타입 반환시 - StringHttpMessageConverter가 처리
	/*
	public String object(@RequestParam String returnType) throws JsonProcessingException {
		//만약에 자바객체(자바빈 혹은 컬렉션)를 JSON으로 변환해주는 라이브러리(GSON 혹은 JACKSON)를 사용한다면
		//아래처럼 불편하게 문자열로 JSON형식을 만들 필요가 없다
		//ResponseBody했으니까 아래 뷰정보 아님 
		
		//return String.format("{\"%s\":\"%s\",\"%s\":\"%s\",\"%s\":\"%s\"}","username","JACKSON","password","JACKSON1234","parameter",returnType);
		
		//{"username":"JACKSON","password":"JACKSON1234","parameter":"Object!!!"} 브라우저에 바로 쓰임
		//자바스크립트로 이벤트처리 해야됨
		
		//JACKSON설치시 ObjectMapper있음(레거시에는 JACKSON 없음)
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> map = new HashMap<>();
		map.put("username", "GSON");
		map.put("password", "GSON1234");
		map.put("parameter", returnType);
		return mapper.writeValueAsString(map);//객체 넣으면 String으로 반환, 예외 걍 던져
	}
	*/
	/*
	//2)객체타입 반환시 - 자바빈
	public Member object(@RequestParam String returnType) {
		
		return new Member("BEAN", "BEAN1234", returnType);
	}
	
	*/
	/*
	//3)객체타입 반환시 - 컬렉션
	public Map<String,Member> object(@RequestParam String returnType){
		
		Map<String,Member> map = new HashMap<>();
		map.put("FIRST", new Member("MAP1", "MAP1234", returnType));
		map.put("SECOND", new Member("MAP2", "MAP2234", returnType));
		return map;
		//{"SECOND":{"username":"MAP2","password":"MAP2234","parameter":"Object!!!"},
		//"FIRST":{"username":"MAP1","password":"MAP1234","parameter":"Object!!!"}} 
		//JSON안의 JSON형태로 반환
	}
	*/
	public List<Member> object(@RequestParam String returnType){
		
		List<Member> members = new Vector<>();
		members.add(new Member("MAP1", "MAP1234", returnType));
		members.add(new Member("MAP2", "MAP2234", returnType));
		return members;
		//[{"username":"MAP1","password":"MAP1234","parameter":"Object!!!"},
		//{"username":"MAP2","password":"MAP2234","parameter":"Object!!!"}]
		//자바스크립트 []는 배열, 배열로 반환
	}

	//2)좁은의미 자바빈 만듦
	static class Member{
		
		private String username;
		private String password;
		private String parameter;
		
		public Member(String username, String password, String parameter) {
			super();
			this.username = username;
			this.password = password;
			this.parameter = parameter;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getParameter() {
			return parameter;
		}
		public void setParameter(String parameter) {
			this.parameter = parameter;
		}
		
	}
}
