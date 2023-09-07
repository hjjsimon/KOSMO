package com.kosmo.restapi.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

	private final ApplicationContext applicationContext;//스프링꺼 임포트
	public DatabaseConfig(ApplicationContext applicationContext) {
		this.applicationContext=applicationContext;		
	}
	
	@Value("${driver}")//스프링꺼 임포트
	private String driver;
	@Value("${url}")
	private String url;
	@Value("${user}")
	private String user;
	@Value("${password}")
	private String password;
	
	
	@Bean
	public DataSource dataSource() {
		//HikariConfig객체 생성후 데이타베이스 연결 및 커넥션 풀 정보 설정
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driver);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(user);
		hikariConfig.setPassword(password);
		//히카리 커넥션 풀 관련 설정 추가
		//자동 커밋 설정(기본값 true) 
		hikariConfig.setAutoCommit(true);
		//IDLE상태에 있는 커넥션이 없을 때 즉 모든 커넥션이 사용 중일때
		//ConnectionTimeout이 지날 때까지 getConnection() 호출은 블록킹된다(기본값:30초)
		hikariConfig.setConnectionTimeout(30000);		
		//커넥션 풀에 최대 커넥션 수.(기본값: 10)
		hikariConfig.setMaximumPoolSize(10);
		
		
		//DataSource를 상속받은 HikariDataSource객체 반환
		//위의 HikariConfig객체로 설정후
		//다른 빈에서 DatabaseConfig객체를 주입받아서 
		//dataSource()를 계속 호출하더라도 동일한 하나의 DataSource객체이다(싱글톤이니까)
		return new HikariDataSource(hikariConfig);
		
	}////////////////
	
	@Bean
	//1.DatabaseConfig를 필드 인젝션으로 주입
	//public SqlSessionFactory sqlSessionFactory() {
	//2.메소드의 인자로 DataSource주입 받을시
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
		SqlSessionFactory factory=null;
		try {
			SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
			
			//데이타 소스 설정:히카리로 설정-DatabaseConfig를 필드 인젝션으로 주입받을시
			//1.DatabaseConfig를 필드 인젝션으로 주입
			//factoryBean.setDataSource(databaseConfig.dataSource());
			//2.메소드의 인자로 DataSource주입 받을시
			factoryBean.setDataSource(dataSource);
			//타입 별칭을 적용할 패키지 경로 설정
			factoryBean.setTypeAliasesPackage("com.kosmo.restapi");
			//매퍼파일의 경로 설정
			factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/**/*.xml"));
			//SqlSessionFactoryBean의 getObject()로 SqlSessionFactory객체 얻기
			factory=factoryBean.getObject();
			
		}
		catch(Exception e) {e.printStackTrace();}		
		return factory;
	}////////////////////////////
	
	@Bean//SqlSessionFactory는 세션 열고 닫고 커밋 등 귀찮음, 그래서 아래 내용이 있대
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	//트랜잭션 처리를 위한 빈 등록
	//트랜잭션 관리자 객체
	@Bean
	public DataSourceTransactionManager transactionManager(HikariDataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	//트랜잭션 관리자 객체를 TransactionTemplate의 트랜잭션 관리자로 설정
	//TransactionTemplate으로 서비스단에서 트랜잭션 처리한다
	@Bean
	public TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager) {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager);
		return transactionTemplate;
	}
}
