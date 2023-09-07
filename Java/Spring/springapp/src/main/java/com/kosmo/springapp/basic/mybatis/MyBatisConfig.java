package com.kosmo.springapp.basic.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kosmo.springapp.basic.database.DatabaseConfig;

//현재 페이지는 선생님이 만들어준거 그대로 가져다쓰면 됨
//https://mybatis.org/spring/ko/factorybean.html 참고하면 다 나와있음

//어노테이션이 아닌 XML방식으로 쿼리 실행시 필수
@Configuration
//아래 어노테이션은 @Mapper 어노테이션이 붙은 매퍼 인터페이스를 스캔(MyBatisMapper 찾을 예정)
//1. value={매퍼인터페이스들의 패키지 나열}
//2. sqlSessionFactoryRef는 SqlSessionFactory빈의 아이디 설정, sqlSessionFactoryRef를 참조하겠다는 뜻
@MapperScan(value = {"com.kosmo.springapp.basic.mybatis","com.kosmo.springapp.onememo.service.impl"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {
	
	//생성자 인젝션을 통해 ApplicationContext를 컨테이너로부터 받는다
	private final ApplicationContext applicationContext;//framework에 있는거 임포트
	
	public MyBatisConfig(ApplicationContext applicationContext) {
		//MyBatisConfig가 생성이 될 때, 컨테이너에게 application
		System.out.println("스프링 컨테이너부터 받은 ApplicationContext"+applicationContext);
		this.applicationContext = applicationContext;
	}

	//DatabaseConfig를 필드 인젝션으로 주입
	//@Autowired//주입받음, 아래 dataSource 대신 다른거 쓰고싶으면 씀
	//private DatabaseConfig databaseConfig;
	
	//설정파일에 해도 되지만 빈으로 하는게 낫다, 반복작업이 줄어듦
	//※메소드명이 아이디이므로 sqlSessionFactoryRef속성의 값과 일치시키자(권장)
	//반드시 일치하지 않아도 타입 기반 주입이므로, 타입 맞으면 알아서 가져오긴 한다
	//DatabaseConfig.java의 @Bean의 아이디 미지정시 메소드명이 아이디
	//@Bean 메소드명과 아래 sqlSessionFactory메소드의 인자명을 일치시켜야한다 
	/*
		@Bean
		public DataSource dataSource() {~~~ 참고
	*/
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {//메소드 인자로 DataSource 주입받음
																		//위처럼 필드 인젝션시에는 인자 필요없음
		
		SqlSessionFactory factory = null;
		try {
			SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
			//데이터 소스 설정: 히카리로 설정
			factoryBean.setDataSource(dataSource);//위의 databaseConfig 주입받고 아래처럼 해도 됨
			//factoryBean.setDataSource(databaseConfig.dataSource());//이때는 위 메소드 인자로 dataSource 받을 필요 없음
			//타입 별칭을 적용할 패키지 경로 설정
			factoryBean.setTypeAliasesPackage("com.kosmo.springapp");//패키지를 별칭(alias), 보통 최상위 패키지로 별칭
			//매퍼 파일(mybatis.xml)의 경로를 설정
			//resource 가져옴, 지금 resources가 루트, 그 하위 mybatis폴더부터 시작 , mapper 모든 하위의 모든 xml
			//여기에 xml이 있구나!
			factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/**/*.xml"));
			//SqlSessionFactoryBean의 getObject()로 SqlSessionFactory 객체 얻기
			factory = factoryBean.getObject();
		}
		catch (Exception e) {e.printStackTrace();}
		return factory;
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {//위에 생성된걸 인자로 주입받음
		
		return new SqlSessionTemplate(sqlSessionFactory);//마이바티스는 내부적으로 SqlSessionTemplate 가지고 작업함
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
