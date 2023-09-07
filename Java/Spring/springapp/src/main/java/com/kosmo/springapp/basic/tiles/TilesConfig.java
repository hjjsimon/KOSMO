package com.kosmo.springapp.basic.tiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class TilesConfig {
	
	//타일즈 관련 클래스(뷰 리졸버와 타일즈 구성용 클래스)를 자바코드로 컨테이너에 등록
	
	//뷰 리졸버 빈으로 등록(타일즈용)
	@Bean
	public UrlBasedViewResolver viewResolver() {
		//만든 클래스 아니라 @Component 못붙임, 이렇게 등록해야함
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		//사용할 뷰 설정
		resolver.setViewClass(TilesView.class);
		//뷰리졸버의 우선순위 결정: 이 뷰를 우선순위가 InternalResourceViewResolver보다 높게해야한다
		resolver.setOrder(0);
		return resolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		
		TilesConfigurer configurer = new TilesConfigurer();
		//값 못바꾸게 final 줄 수도 있음, 근데 싱글톤이라 한번 생성, 바뀔일 없어서 뺌
		configurer.setDefinitions(new String[] {"/WEB-INF/views/template/layouts.xml"});
		//layouts.xml에 타일즈 설정한걸 여기 알려줘야함
		//뷰리졸버는 이 설정정보 보고 뷰정보 캐내서 디스패처서블릿에 알려줌
		return configurer;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
