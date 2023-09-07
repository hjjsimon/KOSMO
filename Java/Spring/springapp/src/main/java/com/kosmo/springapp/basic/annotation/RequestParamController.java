package com.kosmo.springapp.basic.annotation;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Controller
@RequestMapping("/Annotation/")//동일한 URL부분을 쓰면 됨
public class RequestParamController {
	
	 /*	 
	  *  [ 파라미터 받기 ]
	  *  파라미터를 받기 위해 HttpServletRequest 대신 @RequestParam어노테이션 사용
	  *
	  * -파라미터의 자료형으로 받을 수 있다(String이나 int로 즉 형변환 불필요)
	  * -파라미터가 한두개면 그냥 나열
	  * -파라미터가 여러개일때는
	  *  @ModelAttribute어노테이션(세션영역에서 속성읽어옴,인증시 사용,생략가능)이나 
	  *  @RequestParam Map map이 유리(단,Map으로 받을때 체크박스류는 여러개 값중, 같은 키라서 하나만 저장됨)	 
	  *
	  * -사용자가 전달한 파라미터 값이 매개변수에 저장됨
	  *  즉 매개변수명=리퀘스트객체.getParameter("파라미터명")와 같다
	  *
	  * -required속성은 디폴트가 true이다
	  *  만약 파라미터명이 매개변수 명과 다르다면
	  *
	  *  방법1]
	  *  @RequestParam(value="파라미터명") 자료형 매개변수명 -
	  *  required가 true
	  *  해당 파라미터명으로 전달이 안되면 에러(400에러)
	  *
	  *  방법2]
	  *   @RequestParam(value="파라미터명",required=false) 자료형 매개변수명
	  *   해당 파라미터명으로 전달이 안되도 requeired=false라서 에러안남
	  *   
	  *  -@RequestParam()에서 ()안에 default주면 null넘어와도 default로 준 값이 나와서 null에러 회피가능 
	  */
	
	
	@ExceptionHandler({Exception.class})//모든 예외를 처리한것
	public String error(Model model, Exception e) {//Exception은 생성된 예외객체 받음
		
		if(e instanceof MissingServletRequestParameterException) {
			model.addAttribute("error","파라미터가 전달되지 않았어요:"+e.getMessage());
		}
		else if(e instanceof MethodArgumentTypeMismatchException)
			//에러 저장
			model.addAttribute("error","나이는 숫자만...:"+e.getMessage());
		else
			model.addAttribute("error","관리자에게 문의...:"+e.getMessage());
		//에러 났을 때 뷰정보 반환
		return "annotation04/Annotation";
	}
	
	
	//컨트롤러 메소드-파라미터명과 변수 일치시
	@RequestMapping("RequestParamEqual.do")//위에 쓴 URL과 합쳐짐
	public String equals(@RequestParam String name, @RequestParam int age, Model model) {
		//파라미터명 name을 위의 name 그대로 가져오므로 name1로 하면 파라미터 name 못가져옴
		
		//변수명=파라미터명 name, age는 int로, 데이터 저장할 model
		//데이터 저장
		model.addAttribute("name", name);
		model.addAttribute("age", age+10);
		//뷰정보 반환
		return "annotation04/Annotation";
	}
	
	//컨트롤러 메소드-파라미터명과 변수 불일치시
	@RequestMapping("RequestParamDiff.do")//위에 쓴 URL과 합쳐짐
	public String diff(@RequestParam(value = "username", required = false, defaultValue = "파라미터가 안넘어옴" ) String name, 
			@RequestParam("years") int age, Model model) {
		//파라미터명 username 없음, nickname이라 못받음, 근데 required=false라 회피가능, 기본값이 대신 나옴
		//파라미터명 years을 받아서 여기 메소드에서는 age으로 쓰는 방법
		
		//변수명=파라미터명 name, age는 int로, 데이터 저장할 model
		//데이터 저장
		model.addAttribute("name", name);
		model.addAttribute("age", age+10);
		//뷰정보 반환
		return "annotation04/Annotation";
	}
	
	//컨트롤러 메소드-Map으로 파라미터 받기
	//단,체크박스는 여러개 선택해도 하나만 받는다(이때는 @RequestParam String[] 변수 하나 더 추가)
	@RequestMapping("RequestParamMap.do")
	public String map(@RequestParam Map paramMap, Model model, @RequestParam String[] inter) {
		//맵으로 파라미터 받을시
		//폼의 파라미터명이 key가 되고 입력 또는 선택한 값이 value가 된다
		//단, 체크박스류는 가장 왼쪽에 선택한 것만 저장된다		
		System.out.println("관심사항(Map으로 받을 때):"+paramMap.get("inter"));
		System.out.println("관심사항(String[]로 받을때):"+Arrays.toString(inter));
		//데이터 저장
		paramMap.put("inter", Arrays.toString(inter));//inter를 배열로 덮어씀
		model.addAllAttributes(paramMap);
		//뷰정보 반환
		return "annotation04/Annotation";
	}
	
}
