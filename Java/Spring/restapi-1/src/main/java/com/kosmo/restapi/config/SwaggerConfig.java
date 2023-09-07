package com.kosmo.restapi.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8080/swagger-ui/

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket openApi() {//Docket : Swagger 설정의 핵심 클래스
		return new Docket(DocumentationType.SWAGGER_2)
				.select()//ApiSelectorBuilder객체 생성(화면 구성을 위한 객체)
				.apis(RequestHandlerSelectors.basePackage("com.kosmo.restapi.controller"))//API를 문서화하기 위한 Swagger를 적용할 패키지 설정
				.paths(PathSelectors.any())//apis()로 선택되어진 API중 특정 조건에 맞는 API들을 다시 필터링하여 문서화. 예:PathSelectors.ant("/v2/api/**")인 경우 /v2/api 경로의 API를 문서화.
											//any()는 위의 .controller 패키지의 안의 모든 메소드를 문서화 하겠다는 뜻
				.build()
				.apiInfo(openApiInfo());//아래 확인, openApi 만들고 http://localhost:8080/swagger-ui/#/ 에서 출력되는 내용
	}
	//Swagger UI 화면 정보 설정(제목, 설명 등 문서에 대한 정보)
	private ApiInfo openApiInfo() {
		
//		  ApiInfo()생성자의 인자에 대한 설명
//		  String title,
//	      String description,
//	      String version,
//	      String termsOfServiceUrl,
//	      Contact contact,
//	      String license,
//	      String licenseUrl,
//	      Collection<VendorExtension> vendorExtensions
		 
		//방법1]ApiInfo()생성자로 생성
//		return new ApiInfo(
//				"REST API Documentation",
//                "OpenApi 사용 설명서입니다",  
//                "1.0",                                           
//                "localhost:9090",
//                new Contact("Choi Cheol Hwan","http://choi.com","hwanyhee@naver.com"), 
//                "CCH 2.0", 
//                "https://springfox.github.io",
//                new ArrayList<VendorExtension>());
		
		//방법2]ApiInfo를 빌더로 생성(위보다 권장)
		return new ApiInfoBuilder()
				.title("REST API Documentation")
				.description("OpenApi 사용 설명서입니다")
				.version("1.0")
				.termsOfServiceUrl("http://localhost:8080/swagger-ui/")
				.contact(new Contact("Choi Cheol Hwan","http://choi.com","hwanyhee@naver.com") )
				.license("CCH 2.0")
				.licenseUrl("https://springfox.github.io")
				.extensions(new ArrayList<VendorExtension>())
				.build();
	}
}
