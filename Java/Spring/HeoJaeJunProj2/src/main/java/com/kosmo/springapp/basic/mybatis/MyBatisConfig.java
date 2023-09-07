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


@Configuration
@MapperScan(value = {"com.kosmo.springapp.basic.mybatis",
			"com.kosmo.springapp.onememo.service.impl",
			"com.kosmo.springapp.member.service.impl"}, 
			sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {
	
	private final ApplicationContext applicationContext;
	
	public MyBatisConfig(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
		
		SqlSessionFactory factory = null;
		try {
			SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
			//데이터 소스 설정: 히카리로 설정
			factoryBean.setDataSource(dataSource);
			//타입 별칭을 적용할 패키지 경로 설정
			factoryBean.setTypeAliasesPackage("com.kosmo.springapp");
			//매퍼 파일(mybatis.xml)의 경로를 설정
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
