package com.kosmo.springapp.basic.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/*
[스프링 컨테이너에 빈 등록 방법]

	방법1. 어노테이션으로 빈 등록
		  @Controller,@Service,@Repository,@Component,@Configuration가 붙은 클래스를 스캔 해서
		  스프링 컨테이너에 빈으로 등록(컴포넌트 스캔)	
		  빈의 아이디는 카멜 케이스 형태의 클래스명이 된다		
		  빈의 아이디를 직접 지정시에는 name속성 추가.
		  단, @Controller(value="빈 아이디"),@Component(value="빈 아이디")는 value속성 사용
		  
	방법2. 자바코드로 빈 등록
		  @Congiguration어노테이션과 @Bean어노테이션 사용해서 자바코드로 new 생성해서 빈 등록
	
		@Configuration	
		public  class  SpringBeanConfig{
		
			@Bean		
		    public OneMemoService oneMemoService(){		    	
		   		//return  new  OneMemoService();
		   		 OneMemoService oneMemoService= new  OneMemoService();
		   		 return oneMemoService;
		    }
		    @Bean	
		    public OneMemoRepository oneMemoRepository(){
		    	return  new  OneMemoRepository();
		    }
		
		}
		
		이때 빈의 아이디는 카멜케이스 형태의 메소드명이 된다	
		혹은 @Bean(name="빈 아이디")으로 지정하면 된다
		
		단.@Controller는 요청처리를 하기위한 빈이니까 @Configuration으로 등록하는 빈에서 제외한다
		서비스 및 리포지토리 역할을 하는 빈(클래스)은 @Configuration으로 등록할 수 있다	
		단, 서비스와 리포지토리 역할을 하는 빈에서는 @Service, @Repository(DAO계열에 붙임)를 제거한다	
		※생성된 빈은 @Autowired나 @Resource로 주입해서 사용
		
		//@Service
		public class OneMemoService{
		
		}
		//@Repository
		public class OneMemoRepository{
		
		}
		※주된 목적은 빈을 설정파일이 아닌 자바코드로 등록해서 빈을 주입받고자할때 사용한다
		즉 외부 라이러리에서 제공하는 빈이나 빈을 Config별로 구분해서 등록후 주입받을때 사용한다
		(내가 만든건 그냥 @Component 붙이면 되는데 jar 가져다 쓸 때 이야기)
	
	방법3. 빈 설정파일에 빈으로 등록(레거시 얘기)
	<beans:bean name="/HandlerMapping/BeanNameUrl.do" class="com.kosmo.springapp.basic.handlermapping.BeanNameUrlController"/>
*/
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Lazy
public class ConfigurationController {
	
	@Autowired
	private ExternalBean externalBean;//외부빈 주입받음, 필드명은 메소드명과 같게(클래스의 카멜케이스 형태)
	
	@RequestMapping("/Configuration/Configuration.do")
	public String exec(Model model) {
		//데이타 저장
		model.addAttribute("message", "최초 요청시 LazyController가 한번만 생성됩니다");
		//뷰정보 반환
		return "annotation04/Annotation";
	}
	
}
