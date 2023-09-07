package com.kosmo.springapp.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{//코드기반으로 webmvc 구성 설정하는 객체, 그래도 서버시작과 동시에 생성은 안됨
	
	public WebMvcConfig() {
		System.out.println("[WebMvcConfig 생성]");//@Configuration 붙였더니 생성됨, 컨트롤 클릭해보면 컴포넌트 붙어있음, 이것도 컴포넌트 붙은셈
	}

	//아래 메소드에서 인터셉터를 add로 등록해줘야 인터셉터가 서버 시작시 생성출력된다
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//모든 패턴의 URL요청에 대해서 컨트롤러 메소드의 실행시간을 재는 인터셉터 등록
		registry.addInterceptor(new RuntimeInterceptor());//클래스 만들었던거 new 해줌, 인자로 HandlerInterceptor 필요
		//한줄 게시판 URL요청의 인증여부를 판단하는 인터셉터
		/*
		registry.addInterceptor(new AuthenticationInterceptor())
					.addPathPatterns("/onememo/**")
					.excludePathPatterns("/onememo/auth/**");//AuthController는 인증 받으러 가는 곳, 여기는 갈 필요 없음
*/
		//스프링 시큐리티 사용시 인터셉터보다 시큐리티 필터가 먼저 요청을 가로챔, 그 뒤에 여기로 옴
		//위에 주석처리 해야 AuthenticationInterceptor로 시큐리티 후 인터셉터로 넘어가 쓸데없이 출력되는 일 없음
				
	}

	
}
