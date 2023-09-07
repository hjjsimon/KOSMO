package com.kosmo.springapp.basic.annotation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestMappingController {

	//※스프링 4.3부터 HTTP메소드에 대응하는 어노테이션 등장(@GetMapping/@PostMapping 등)
	
	/* @RequestMapping("/요청URL")
	 * -주로 요청을 처리하는 메소드 앞에 단다.(클래스 위에 달아도 됨)
	 * -컨텍스트 루트를 제외한 /로 시작하는 요청URL(현재 컨텍스트 루트 없음, /임, properties에서 주석처리함, 아래 확인)
	 * 								#컨텍스트 루트 변경(디폴트: /) <- 루트 의미
									#server.servlet.context-path=/springapp
	 * -GET방식 및 POST방식 둘다 처리 가능(HTTP메소드 모든 방식 처리)
	 */
	/*
	@RequestMapping("/Annotation/RequestMappingBoth.do")
	public String both(HttpServletRequest req) {//HttpServletRequest 써도 되는데 지양함, 어노테이션 추천
		//데이터 저장]리퀘스트 영역에 직접 저장(Model, Map, ModelMap 모두 가능하지만 지금은 그냥~)
		String loginInfo = String.format("[아이디:%s,비밀번호:%s,요청방식:%s]", 
				req.getParameter("id"), req.getParameter("pwd"), req.getMethod());
		req.setAttribute("loginInfo", loginInfo);
		//뷰정보 반환
		return "annotation04/Annotation";
	}
	
	 //@RequestMapping(value="/요청URL",method=전송방식지정) -> 방식 하나일때 [method =] 생략가능
	 //-하나만 처리가능
	 //cf) Http로는 get, post방식만 전송가능
	 
	 
	@RequestMapping(value="/Annotation/RequestMappingOne.do",method = RequestMethod.GET)//마지막은 전송방식은 대문자로
	public String one(HttpServletRequest req) {//HttpServletRequest 써도 되는데 지양함, 어노테이션 추천
		//뷰정보 반환
		return both(req);//위의 both 호출, 뷰정보 반환(String) 똑같이 하는 것, 받은 요청을 그대로 both로 전달, 
	}
	*/
	/*
	 여러 요청을 하나의 컨트롤러 메소드로 처리하기
	 1.value={"요청URL1","요청URL2",...}
	 2.@PathVariable과 요청URL의 바뀌는 부분을 변수처리
	 */
	/*
	//@RequestMapping({"/Annotation/RequestMappingBoth.do","/Annotation/RequestMappingOne.do"})
	@RequestMapping(value = {"/Annotation/RequestMappingBoth.do","/Annotation/RequestMappingOne.do"}, 
					method= {RequestMethod.POST,RequestMethod.GET})//위코드와 동일, get post 둘 다 처리
	public String both(HttpServletRequest req) {//HttpServletRequest로 서블릿API 써도 되는데 지양함, 어노테이션 추천
		//데이터 저장]리퀘스트 영역에 직접 저장(Model, Map, ModelMap 모두 가능하지만 지금은 그냥~)
		String loginInfo = String.format("[아이디:%s,비밀번호:%s,요청방식:%s]", 
				req.getParameter("id"), req.getParameter("pwd"), req.getMethod());
		req.setAttribute("loginInfo", loginInfo);
		//뷰정보 반환
		return "annotation04/Annotation";
	}
	*/
	@RequestMapping("/Annotation/{path}")//Rest API에서 PathVariable 아주 많이 씀
	public String both(@PathVariable String path, @RequestParam Map paramMap, Map dataMap) {
		//파라미터 저장 paramMap, 데이터 저장할 dataMap
		//데이터 저장
		String loginInfo = String.format("[아이디:%s,비밀번호:%s,URL의 PATH:%s]", 
				paramMap.get("id"), paramMap.get("pwd"), path);
		dataMap.put("loginInfo", loginInfo);
		//뷰정보 반환
		return "annotation04/Annotation";
	}
	
}
