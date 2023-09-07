package com.kosmo.springapp.basic.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/*
 스프링 컨테이너에 빈(클래스) 등록하는 방법 (스프링컨테이너가 내가 만든 빈의 생기고 사라지는 라이프사이클을 관리함(=싱글톤), 컨테이너가 알아서 new destroy함)
 
 1.어노테이션으로 빈 등록
 	@Controller/@Component/@Service 등의 어노테이션을 클래스 위에 붙인다
 	
 2.자바코드로 빈 등록
 	@Configuration 과 @Bean 어노테이션을 사용
 	(주로 외부 라이브러리의 클래스를 빈으로 등록할 때 사용)
 	
 3.xml로 빈 등록
 	스프링 레거시에서 스프링 빈 설정파일인 xml파일에 작성한 자바클래스를 태그로 빈 등록
 	(단, 스프링 부트는 xml설정과 같은 것을 최소화하는 것이 철학이라 xml설정이 없다)
 */

//@Controller//이건 요청 처리하는 클래스에 붙임, @RequestMapping으로 받는거랑 친구
@Configuration//어떤 구성을 한다는 뜻, new안해도 서버만 키면 자동으로 생성
public class SpringBeanConfig {
	
	public SpringBeanConfig() {
		System.out.println("@Configuration 붙은 SpringBeanConfig의 생성자"); //서버키면 생성됨
	}
	//@Bean어노테이션에 의해 Command객체는 스프링 컨테이너에 등록되므로 싱글톤으로 생성한것
	@Bean //빈에 아이디 미등록(이 때는 메소드명이 id가 된다, id속성은 주입대상 클래스의 멤버필드명(변수명)과 일치시켜라)
	@Qualifier("command1")//꼭 주입받는 대상의 필드명과 일치하지 않아도 된다
	//@Bean("fCommand")//빈에 아이디 등록 ()안에 ""로 id등록시 싱글톤x, fCommand를 찾아 김길동 주입함, fCommand, sCommand는 주소 다르게 찍힘
	public Command firstCommand() {
		return new Command("김길동", "KIM", "KIM1234");
	}
	
	@Bean
	@Qualifier("command2")
	//@Bean("sCommand")//스프링 컨테이너에 빈 등록, 커맨드객체 2개 되면 에러남 이건 주석해줘야함
	public Command secondCommand() {
		return new Command("박길동", "PARK", "PARK1234");
	}
	
	/*
	 * 접근지정자 : public
	 * 반환타입 : 스프링 컨테이너에 등록하고자하는 빈의 타입
	 * 메소드명 : 소문자로 시작하는 클래스명
	 *
	 * @Bean의 name속성으로 빈의 아이디 설정가능(설정안하면 메소드명이 id가 된다)
	 * 해당 빈을 필요로 하는 곳에서 @Autowired나 @Resource로 자동 주입해서 사용
	 */
	@Bean
	@Lazy//서버시작시 Pre 말고 최초요청시 Lazy로 생성하자(단,Lazy로딩이 아닌 컨트롤러에도 @Lazy를 붙여야 한다)
	public ExternalBean externalBean() {//카멜케이스로 항상 사용, id이자 식별자이자 변수명이 되니까
		return new ExternalBean();
	}
	
	
}
