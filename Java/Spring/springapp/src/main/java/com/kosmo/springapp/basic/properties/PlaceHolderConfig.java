package com.kosmo.springapp.basic.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource({"classpath:config/database.properties","classpath:config/paging.properties"})//이제 여기서 값을 읽어옴 추가시 {}안에 ,로 연결하면 됨
public class PlaceHolderConfig {

	@Bean//컨테이너에 등록
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() { 
		//얘는 어플리케이션에서 하나만 있으면 되니까 static, 근데 그냥 무조건 static으로 쓰라고 함
		//이 클래스는 스프링프레임워크가 만들어줌, 얘가 속성파일 읽음
		return new PropertySourcesPlaceholderConfigurer();
	}
}
