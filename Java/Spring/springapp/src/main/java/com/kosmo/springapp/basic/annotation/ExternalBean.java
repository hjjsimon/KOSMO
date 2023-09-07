package com.kosmo.springapp.basic.annotation;
/*  
	스프링 철학에 맞게 DI를 통해서 주입받자
	주입받으려면 주입받으려는 빈은 반드시 스프링컨테이너에 등록돼야한다
	
	스프링컨테이너에 빈 등록방법 3가지
	1.@Component
	2.@Configuration + @Bean
	3.xml설정파일에 등록
	
	즉 내가 만든 빈은 @Component붙이면 됨
	단, 외부라이브러리에서 제공하는 클래스는 스프링컨테이너 등록시스프링 부트는 2번을 사용
*/

//아래 빈은 2번째 방법(자바코드)으로 등록해서 다른 빈에 주입하려고 한다
//아래는 ConfigurationController에서 주입 받는 외부 라이브러리라고 하자(내가 만든 빈이 아니라는 가정)
public class ExternalBean {//지금 외부빈 jar라는 뜻으로 지은 이름, @Component붙일 수 없음

	public ExternalBean() {
		System.out.println("ExternalBean의 생성자");
	}
	@Override
	public String toString() {
		return "ExternalBean객체입니다";//어노테이션 없어서 출력x
	}
}
