package com.kosmo.springapp.onememo.service.impl;
//MyBatisConfig.java 가서 패키지 추가해줌
//@MapperScan(value = {"com.kosmo.springapp.basic.mybatis","com.kosmo.springapp.onememo.service.impl"}, sqlSessionFactoryRef = "sqlSessionFactory")


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kosmo.springapp.onememo.service.OneMemoDTO;

@Mapper//impl이 예전방식, DAO에 해당함, 매퍼 인터페이스
public interface OneMemoMapper {
	//아이디와 비밀번호로 회원 조회
	boolean findByIdAndPwd(Map map);
	//전체 조회용(전체 목록 가져옴)
	List findAll(Map map);
	//입력
	int save(Map map);//DTO만들었는데 Map으로 받네
	//상세보기(레코드 하나)
	OneMemoDTO findByNo(Map map);//이번엔 DTO 반환타입~ Map해도 돼~
	//글번호로 조회해서 레코드 하나 삭제
	int deleteByNo(OneMemoDTO record);//영향받은 행의 수 반환
	//글번호로 조회해서 레코드 하나 수정
	int updateByNo(OneMemoDTO record);
	//전체 레코드 수 조회
	int count(Map map);
	int saveUser(Map map);
}
