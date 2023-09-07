package com.kosmo.springapp.basic.ajax;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AjaxConfig {
	
	@Bean//ObjectMapper는 잭슨의 객체라 주입시 config 필요, 여기서 뉴, 이런 config는 모아놓는게 좋음
	public ObjectMapper objectMapper() {
		return new ObjectMapper(); 
	}

}
