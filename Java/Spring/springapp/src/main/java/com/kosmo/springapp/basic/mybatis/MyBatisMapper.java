package com.kosmo.springapp.basic.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kosmo.springapp.onememo.service.OneMemoDTO;

//매퍼 인터페이스 : 쿼리 실행결과와 자바 객체를 매핑하는 추상 메소드를 정의한 인터페이스
//추상메소드명은 보통 CRUD작업명으로 한다 (ex. SELECT면 findBy~~ 혹은 save() 등등)
//얘가 모델이다, DAO다 라고 생각하면 된다!!!!
//서비스단에서 아래 메소드 호출
@Mapper//매핑해주는 인터페이스 위에 붙임
public interface MyBatisMapper {//인터페이스는 생성 안되므로 생성자 없다! 이걸 추상화한 클래스가 만들어지겠지!

	//매퍼 역할을 함, 메소드 호출해서 아래 쿼리가 실행되는 것
	//아래 쿼리결과 날짜 2023~~~ 나오는걸 자바객체 String으로 바꿔줌, 이걸 매핑해준다고 함
	
	//1.어노테이션 방식(직접 어노테이션에 쿼리문 작성)
	@Select("SELECT SYSDATE FROM DUAL")//select, insert, delete, update 등만 있음
	//현재 시스템 날짜 나옴
	/*
	  아래 방식으로 진행, psmt에서 ? 하듯 #{id} 안의 값 처리하는 것
	  
	  public interface UserMapper {
		  @Select("SELECT id, name FROM users WHERE id = #{id}")
		  User selectById(int id);
 	  }
	*/
	String getTimeByAnnotation();//추상메소드 정의 
	//상수는 public static final, 메소드는 public abstract 기본 세팅 -> public 이런거 굳이 안써도 됨 
	
	//2.XML방식(xml파일에 쿼리문 작성 필요, 아래처럼 경로로 매핑한 mybatis.xml)
	//<mapper namespace="com.kosmo.springapp.basic.mybatis.MyBatisMapper">
	String getTimeByXml();

	//동적 SQL 연습용(DAO에서 엄청 하던게 지금 한줄로 종결!)
	List<Map> findWithTitleLike(Map map);
	
	List<OneMemoDTO> findWithColumnsLike(Map map);//OneMemoDTO.java 참고
	
	List<OneMemoDTO> findWithColumnsLikeChoose(Map map);
	
	List<OneMemoDTO> findWithColumnsLikeWhere(Map map);
	
	List<OneMemoDTO> findWithColumnsLikeTrim1(Map map);
	
	int updateWithColumnsLikeTrim2(Map map);

	int updateWithColumnsLikeSet(Map map);
	
	//파라미터가 리스트 일때
	List findAll(List<Integer> numbers);
	//파라미터가 맵 일때
	List findAll(Map<String, List> map);
	//foreach 요소 응용
	int deleteEmail(int[] emails);
}
