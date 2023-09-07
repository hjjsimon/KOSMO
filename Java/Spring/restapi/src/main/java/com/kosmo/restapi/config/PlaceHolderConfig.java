package com.kosmo.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

//리소스 내 .properties파일의 키=값을 주입받기 위한 빈 등록
@Configuration
@PropertySource({"classpath:config/database.properties"})//.properties 여러개일수 있으니 배열로, classpath는 resources
public class PlaceHolderConfig {

	@Bean//이게 있어야 @Value 어노테이션으로 필요한거 주입받을 수 있음
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {//static 해도 안해도 됨
		
		return new PropertySourcesPlaceholderConfigurer();//이제 .properties파일 주입받을 수 있음
	}
	
}
