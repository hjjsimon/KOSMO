package com.kosmo.springapp.basic.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
	스프링 시큐리티 관련 라이브러리 설치시 스프링 시큐리티에서 제공하는
	폼 화면(이상한 로그인화면)이 뜬다(아래 설정하지 않은 경우)	


	아래 클래스 작성후 해야할 작업들 순서
	1. AuthController.java의 서비스 주입 및 로그인 처리 및 로그아웃 처리 주석
	2. Login.jsp의 로그인폼의 action속성을 반드시
	   loginProcessingUrl()메소드에 지정한 경로와 일치 시켜야한다
	3. Login.jsp의 세션영역에서 ${sessionScope.id}를 읽어오는 부분을
	   모두 security 태그 라이브러를 사용해서 변경한다.
	4. Top.jsp도 3번과 같이 security 태그 라이브러를 사용해서 변경
	5. .java(컨트롤러)에서 세션영역에서 읽어오는 코드(@ModelAttribue String id)를 모두 Autentication클래스를 
	      사용한 코드로 변경
	   (@SessionAttribute나 HttpSesion등은 주석처리)
	   
	즉
	컨트롤러 단(/onememo/bbs/** 패턴의 URL 요청을 처리하는 컨트롤러)에서는 
	@SessionAttribute대신 스프링 씨큐리티 코드로 변경
	뷰단(JSP)에서는 스프링 씨큐리티 태그 라이브러리로 변경
	
	시큐리티 > 인터셉터, 시큐리티가 더 먼저 가로채서 인터셉터는 정보를 못가로챔
	AuthenticationInterceptor에서 확인하기  
		
	시큐리티는 반드시 hidden으로 POST써서 토큰을 보내야함

*/


//https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
//1.web.xml에서 XML기반이 아닌 어노테이션 기반 설정이 적용되도록 수정
//2.자바코드로 스프링 씨큐리티 설정
@Configuration
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 인증 설정
            .authorizeRequests()
                .antMatchers("/onememo/bbs/**").hasRole("USER")//어느 URL에 대해 인증할 것인지, bbs 아래 모두 USER 권한 있어야함
                .anyRequest().permitAll()
                .and()
            // 폼 기반 인증(로그인) 설정
            .formLogin()
                .loginPage("/onememo/auth/Login.do")
                .loginProcessingUrl("/onememo/auth/LoginProcess.do")//로그인처리 URL이랑 반드시 일치시켜야함
                .failureUrl("/onememo/auth/Login.do")
                .failureHandler((request, response, exception) -> {
                    request.setAttribute("NotMember", "로그인하라니까....");
                    request.getRequestDispatcher("/onememo/auth/Login.do").forward(request, response);
                })
                .usernameParameter("id")
                .passwordParameter("pwd")
                .defaultSuccessUrl("/onememo/auth/Login.do")
                .and()
            // 로그아웃 설정
            .logout()
                .logoutUrl("/onememo/auth/Logout.do")
                .and()
            // CSRF 비활성화
//            .csrf().disable() //talend로 POST 값 입력시 주석해제, GET은 주석(POST는 토큰을 보내야함)
            	//활성화시 위에 설정한 URL패턴에 상관없이 POST방식은 CSRF토큰을 서버에 전송해야한다
            	//아니면 403에러 발생함
            	//주석 풀고 POST로 하면 주석 풀고
            	//주석 처리시 GET설정 후  UsersRestController의 insertUser @GetMapping()으로 바꾸면 됨
            
            
            
            /*
            ※ Talend 사용해서 JSON형태로 데이터를 내 서버에 POST로 보내준 것
            
            POST: http://localhost:9090/users
            헤더 - Content-Type:application/json
            바디 -   {
					  "name": "가길동",
					  "id":"KIM",
					  "pass":"1234"
					}
            
            200 성공 후
		            {
						"name": "가길동",
						"id": "KIM",
						"pass": "$2a$10$Nn2L8OfjfFLL5WLqp0RdOe/7VdJJqKSwEdxvvW1FbI96sQ7/C7PVa"
					}
            
            */
            // 세션 관리
            .sessionManagement()//중복로그인 방지
                .invalidSessionUrl("/onememo/auth/Login.do")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/onememo/auth/Login.do");

        return http.build();
    }
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Autowired
    private DataSource dataSource;
	
	

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select id as username, pass as password, enabled from users where id = ?")
            .authoritiesByUsernameQuery("select id as username, authority from users where id = ?")
            .passwordEncoder(passwordEncoder());
    }
	
}
