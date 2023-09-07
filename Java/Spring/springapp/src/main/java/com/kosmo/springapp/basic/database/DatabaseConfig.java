package com.kosmo.springapp.basic.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {//DB설정과 관련된 빈, 이런걸 Config로 명명, 원래 Controller랑 별개로 Config끼리 묶어서 놔야함

	//데이터 베이스 연결정보
	@Value("${driver-class-name}")
	private String driver;
	@Value("${oracle-url}")
	private String url;
	@Value("${user}")
	private String user;
	@Value("${password}")
	private String password;
	
	@Bean
	public DataSource dataSource() {
		//HikariConfig객체 생성 후 데이터베이스 및 커넥션 풀 정보 설정
		//이 설정 정보대로 커넥션 풀이 만들어짐
		//#데이타베이스 연결-자바코드로 하지 않으면 마이바티스 설정시 에러 -> .properties에 설정한거 주석처리하면 현재 자바코드를 대신하면 되는 것
		//.properties에서 설정 이제 안되는 듯 그냥 자바코드로 하기
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driver);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(user);
		hikariConfig.setPassword(password);
		//자동커밋 설정(기본값 true)
		hikariConfig.setAutoCommit(true);
		//IDLE(놀고있는)상태에 있는 커넥션이 없고 모든 커넥션이 사용중일때
		//ConnectionTimeout이 지날때까지 getConnection()호출은 블로킹된다, 기본 30초
		hikariConfig.setConnectionTimeout(30000);
		//커넥션 풀에 최대 커넥션 수(10)
		hikariConfig.setMaximumPoolSize(10);
		//DataSource를 상속받은 HikariDataSource객체 반환
		//위의 HikariConfig객체로 설정후
		//다른 빈에서 DatabaseConfig객체를 주입받아서 dataSource()를 계속 호출하더라도 동일한 하나의 DataSource객체(싱글톤)
		return new HikariDataSource(hikariConfig);
		
		/*
		#아래 대신 DatabaseConfig.java가서 코드로 세팅함, 아래는 이제 안되는듯
		#데이타베이스 연결-자바코드로 하지 않으면 마이바티스 설정시 에러
		#즉 데이터베이스 관련은 @Configuration 및 @Bean을 사용해서 자바코드로 설정하자
		#spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
		#spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
		#spring.datasource.username=SPRING
		#spring.datasource.password=SPRING
		
		#히카리 커넥션 풀관련 설정 추가
		#커넥션 풀에 최대 커넥션 수. (기본값: 10)
		#IDLE상태에 있는 커넥션이 없을 때 connection-timeout이 지날 때까지 getConnection() 호출은 블록킹된다
		#spring.datasource.hikari.maximum-pool-size=10
		#자동 커밋 설정(기본값 true)
		#spring.datasource.hikari.auto-commit=true
		
		#커넥션 풀에서 커넥션을 얻기전까지 대기하는 최대 시간.대기시간 초과시 SQLException 발생(기본값 30000(30초))
		#spring.datasource.hikari.connection-timeout=30000 
		*/
	}
	
	//트랜잭션 처리를 위한 빈 등록 -> 데이터 소스의 트랜잭션 관리하는 트랜잭션 매니저가 생성됨
	//transactionManager라고 이름 많이 지음, id가 됨
	//타입 기반이라 이름 dataSource안맞춰도 되는데 그냥 맞춰
	
	//트랜잭션 관리자(매니저) 객체
	@Bean
	public DataSourceTransactionManager transactionManager(HikariDataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	//트랜잭션 관리자 객체를 TransactionTemplate의 트랜잭션 관리자로 설정
	//TransactionTemplate으로 서비스단에서 트랜잭션 처리한다
	@Bean
	public TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager) {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager);//주입받은 transactionManer로 설정함
		return transactionTemplate;
	}
	
	
	
	
	
	
}
