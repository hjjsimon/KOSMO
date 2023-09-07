package com.kosmo.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HeoJaeJunProj2Application {

	public static void main(String[] args) {
		SpringApplication.run(HeoJaeJunProj2Application.class, args);
	}

}
