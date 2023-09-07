package com.kosmo.restapi.config;

import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//RestTemplate 커넥션 풀 사용하기 위한 빈 등록
@Configuration
public class RestTemplateConfig {

	/*
	 * 반환 타입:IOC컨테이너(스프링 컨테이너)에 등록할 빈(싱글 톤)
	 * 메소드명:생성된 빈의 이름(아이디값)
	 * 예:RestTemplate restTemplate = new RestTemplate(ClientHttpRequestFactory)
	 */
	
	/*
	 사전 작업:POM.XML에 아래 등록(RestTemplate사용시 커넥션 풀을 사용하기 위함)
	 <dependency>
	    <groupId>org.apache.httpcomponents</groupId>
	    <artifactId>httpclient</artifactId>
	    <version>4.5.14</version>
	 </dependency>		 
	 */
	@Bean
	public RestTemplate restTemplate() {
		
		//1.커넥션 풀 사용을 위한 HttpClient객체 생성
		CloseableHttpClient httpClient = HttpClientBuilder.create()
				.setMaxConnTotal(50)//연결을 유지할 최대 Http커넥션 수(루트, URI 달라도 ㄱㅊ)
				.setMaxConnPerRoute(50)//Route당(요청 URI주소당) 최대 Http커넥션 수(URI주소 하나가 하나의 ROUTE라고함, 같은 users를 여러 사람이 요청가능함)
				.setConnectionTimeToLive(5,TimeUnit.SECONDS)//커넥션 연결 유지시간(5초)
				.build();//httpClient 객체 생성
		
		//2.타임아웃 설정을 위한 객체 생성
		HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory();
		factory.setConnectionRequestTimeout(3000);//요청 타임아웃 시간(3초), 3초간 응답 안하면 에러
		factory.setHttpClient(httpClient);
		
		return new RestTemplate(factory);//요청할때마다 연결x, 미리 연결한 http커넥션 씀
	}
}
