package com.kosmo.restapi.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {//메소드명이 매퍼파일 아이디
	
	//CREATE(입력)
	int save(UsersDto dto);//추상메소드, public 할 필요 없음
	//READ(조회-모두)
	List<UsersDto> find();
	//READ(조회-하나)
	UsersDto findByUsername(String username);
	//UPDATE(수정)
	int update(UsersDto dto);
	//DELETE(삭제)
	int delete(String username);
}
