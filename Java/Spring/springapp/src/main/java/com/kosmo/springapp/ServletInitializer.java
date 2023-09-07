package com.kosmo.springapp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	/*
	 스프링부트 애플리케이션을 외부 서블릿 컨테이너(=외부 톰캣)에서 실행하기 위한 클래스다 
	 configure()메소드는 외부 서블릿 컨테이너가 애플리케이션을 시작할 때 호출된다
	 이 파일은 프로젝트 생성시 패키징을 .war로 선택시 생성된다(톰캣도 war일때만 생김, pom.xml에서 starter-tomcat으로 확인가능)
	*/
	
	public ServletInitializer() {
		//System.out.println("ServletInitializer의 생성자");
		//서버시작과 동시에 실행안됨, 외부서버 배포시에 사용하는 것
	}
	
	@Override //빌더는 생성자, 외부 톰캣에 의해 아래 메소드 호출되어 어플리케이션 실행됨
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringappApplication.class);	
	}

}
