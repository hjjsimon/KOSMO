package com.kosmo.springapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	/*
	 spring.mvc.static-path-pattern=/static/**
	 spring.web.resources.static-locations=classpath:/static/  
	 */
	//위의 기존 리소스 핸들러는 그대로 유지하며, 원하는 리소스 핸들러만 추가가능, 아래 registry에 계속 하면 됨
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//리소스 처리하는걸 리소스핸들러라고함, 아래 설정시 index.jsp 그림 3개 다 나옴
		registry.addResourceHandler("/virtual/**")//spring.mvc.static-path-pattern=/static/** 동일코드
		/*
			 원래 application.java 에서 spring.mvc.static-path-pattern=/resources/**
			 했던거에 추가해도 되는데 지금 virtual은 자바코드로 인위적으로 추가해준것		  
		 */
				.addResourceLocations("classpath:/mystatic/");//spring.web.resources.static-locations=classpath:/static/ 동일 
																//클래스패스 설정시 반드시 끝에 / 추가
		
		registry.addResourceHandler("/json/**")
				.addResourceLocations("classpath:/json/");//실제 위치는여기, 이렇게 추가로 등록해줌 
	}
	
}
