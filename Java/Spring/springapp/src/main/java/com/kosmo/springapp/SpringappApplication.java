package com.kosmo.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //스프링부트로 만든 어플리케이션이라는 뜻
public class SpringappApplication {
	
	/*
	public SpringappApplication() {
		System.out.println("SpringApplication의 생성자");
		//어노테이션이 붙은건 서버시작과 동시에 실행되는 프리로딩임, 생성이 되어있으므로 강제주입되는 것, 서블릿은 요청들어와 실행하는 레이지로딩
	}
	*/
	
	public static void main(String[] args) {//메인이 프로그램 진입점
		/*
		 스프링 컨텍스트(=전체영역)를 초기화
		 구성 파일을 로드하여 애플리케이션을 시작
		 이렇게 실행되면 애플리케이션은 내장된 웹서버를 시작하고 필요한 빈을 생성하고 주입
		 애플리케이션을 실행하는데 필요한 다른 초기화 작업을 수행한다(아래 run이!)
		*/
		SpringApplication.run(SpringappApplication.class, args);
	}

}
