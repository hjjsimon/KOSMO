package com.kosmo.springapp.basic.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AutowiredController {

	/*
	  테스트 시나리오
	  Type, ID, Qualifier 3가지 단계를 거쳐 인젝션을 하더라
	  각 과정의 오류와 해결방법 알아보기
	  
	  1] Type기반
	  	 1)  SpringBeanConfig파일에 Command객체 하나 등록(value속성/@Qualifier 생략)
	  	 	 fCommand와 sCommand는 같은 객체가 주입된다(컨테이너에 Command객체 1개임, 등록된 커맨드를 fCommand에 주입, sCommand에도 주입 동일)
	  		 @Autowired
			 private Command fCommand;
			 @Autowired
			 private Command sCommand;
			 
	     2)  빈 설정파일(레거시인경우) 또는 자바코드로 빈 등록시, 현재 Command객체 또 하나 등록(SpringBeanConfig에서 주석 풀어서 2개 등록)
	     	 컨테이너에 같은 Command객체가 2개 있음, Command객체 fCommand에 주입하려고 보는데 컨테이너가 둘 중 뭘 주입할지 모르겠는 것, 
	     	 500에러발생, sCommand 없어도 에러남
	     	 firstCommand, secondCommand 메소드명이 식별자가 됨
			 Field fCommand in com.kosmo.springapp.basic.annotation.AutowiredController required a single bean, but 2 were found:
			 - firstCommand: defined by method 'firstCommand' in class path resource [com/kosmo/springapp/basic/annotation/SpringBeanConfig.class]
			 - secondCommand: defined by method 'secondCommand' in class path resource [com/kosmo/springapp/basic/annotation/SpringBeanConfig.class]

	    3)  @Autowired(required = false) 빈 설정파일 혹은 자바코드로 등록한 두 개의 @Bean을 주석처리
	        주입이 필수가 아니기때문에 객체가 없으면 주입이 안되므로 에러남, @Bean해야 객체가 들어감
	        fCommand와 sCommand는 null
	        이건 무조건 객체가 컨테이너에 있어야함
	        주입이 필수가 아니도록 @Autowired(required = false) 주면 컨테이너에 컨테이너가 없어도 에러가 안남
	      
	 2] id 기반
	    SpringBeanConfig파일에서 id속성은 주입대상 클래스의 멤버필드명(변수명)과 일치시켜라
	    자바코드로 빈 등록시 ID 부여방법 2가지
	    1) @Bean("id명")으로 어노테이션 주면 됨, 인스턴스 변수명이 id명인셈
	    또는(1번 방법이 우선함)
	    2) 메소드명이 id명이 된다
	    -> id 안주고 @Bean만 굳이 쓰고싶다면 //private Command firstCommand; 처럼 메소드명을 쓰면 된다는 뜻
	    -> 1번 방법이 우선하고 id명 부여하지 않으면 메소드명이 id명이 된다
	    
	 3] Qualifier기반
	    1) SpringBeanConfig파일에서 id속성 제거 및  @Qualifier("식별자1") 추가
	    2) 필드에 @Qualifier("식별자1")
	    
	    
	 
	  ※ 타입-> ID-> Qualifier
	  @Autowired: 타입, ID, Qualifier 순서로 빈을 찾아서 주입함
	  @Resource: ID, 타입, Qualifier 순서(이 어노테이션은 잘 안씀)
	  
	*/
	//1.Type기반 테스트
	
	/*
	//[필드에 부착]-필드 인젝션
	//SpringBeanConfig에서 @Bean으로 등록한 Command가 주입된것, 주소 동일, 싱글톤임
	//fCommand:com.kosmo.springapp.basic.annotation.Command@413a7019, 
	//sCommand:com.kosmo.springapp.basic.annotation.Command@413a7019
	@Autowired
	@Qualifier("command1")//이걸 주면 각자 식별자에 맞춰 주입됨, 주소 다르게 나옴
	private Command fCommand;
	//private Command firstCommand;
	@Autowired
	@Qualifier("command2")
	private Command sCommand;
	//private Command secondCommand;
	*/
	
	//[세터에 부착]-추가적인 로직이 필요할때 사용(세터 인젝션)
	//이 때는 SpringBeanConfig파일의 id속성값이 세터의 매개변수명과 일치시켜야한다
	/*
	private Command firstCommand;
	private Command secondCommand;
	
	//세터에 부착시
	@Autowired
	public void setFirstCommand(Command firstCommand) {
		this.firstCommand = firstCommand;
	}
	@Autowired
	public void setSecondCommand(Command secondCommand) {
		this.secondCommand = secondCommand;
	}
	*/

	//[생성자에 부착]-단,@Qualifier부착 불가-생성자 인젝션(권장/ Autowired 생략가능하고 @Bean만 붙이고, 메소드명대로 변수명 만들면 되니까 가장 간단함)
	//이때는 SpringBeanConfig파일의 id속성값이 생성자의 매개변수명과 일치시켜야한다
	private Command firstCommand;
	private Command secondCommand;
	
	//@Autowired //생성자 하나일때 생략가능
	public AutowiredController(Command firstCommand, Command secondCommand) {
		super();
		this.firstCommand = firstCommand;
		this.secondCommand = secondCommand;
	}
	
	@RequestMapping("/Annotation/Autowired.do")
	public String execute(Model model) {
		//데이터 저장
		//1]필드에 부착
		//model.addAttribute("message", String.format("fCommand:%s, sCommand:%s", fCommand, sCommand));
		
		//2]세터에 부착
		model.addAttribute("message", String.format("fCommand:%s, sCommand:%s", firstCommand, secondCommand));
		
		//뷰정보 반환
		return "annotation04/Annotation";
	}

	

}
