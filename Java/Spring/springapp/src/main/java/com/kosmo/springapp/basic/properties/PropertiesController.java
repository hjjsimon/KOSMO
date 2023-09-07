package com.kosmo.springapp.basic.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Properties")
public class PropertiesController {

	@Value("${spring.mvc.static-path-pattern}")//이 값에 .properties의 값을 EL식으로 주입받음
	private String staticPathPattern;//값을 읽어와서 변수에 세팅
	
	@Value("${nickname}")
	private String nickname;
	
	//디폴트 속성파일: application.properties에서 읽기
	@GetMapping("/ValueDefault")
	public String valueDefault(Model model) {
		//데이터 저장
		model.addAttribute("message", String.format("정적파일패스: %s<br/>닉네임: %s", staticPathPattern,nickname));
		//뷰정보 반환
		return "properties05/Properties";
	}
	
	@Value("${driver-class-name}")
	private String driver;
	@Value("${oracle-url}")
	private String url;
	@Value("${user}")
	private String user;
	@Value("${password}")
	private String password;//이렇게하면 pwd노출x
	
	//디폴트 속성파일이 아닌 src/main/resources/config/database.properties에서 읽기: 
	//1. PropertySourcesPlaceholderConfigurer 빈 등록
	//2. @PropertySource()에 속성파일 위치 설정
	@GetMapping("/ValueCustom")
	public String ValueCustom(Model model) {
		//데이터 저장
		model.addAttribute("message", 
				String.format("드라이버: %s<br/>URL: %s<br/>아이디: %s<br/>비밀번호: %s", 
						driver,url,user,password));
		//뷰정보 반환
		return "properties05/Properties";
	}
	
	
	
	
	
}
