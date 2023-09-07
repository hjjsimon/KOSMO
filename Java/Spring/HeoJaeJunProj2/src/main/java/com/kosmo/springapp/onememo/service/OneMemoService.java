package com.kosmo.springapp.onememo.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//제너릭 인터페이스
//메소드명은 업무처리 로직에 맞게 작성
//메소드의 인자타입은 매퍼 인터페이스의 메소드와 일치시키자
public interface OneMemoService<T> {

	//로그인용
	boolean isLogin(Map map);
	//목록용
	ListPagingData<T> selectList(Map map, HttpServletRequest req, int nowPage);//map, req, 현재 페이지 세팅함ㄴ
	//상세보기용
	T selectOne(Map map);
	//입력/수정/삭제용
	int insert(Map map);
	int update(T record);//OneMemoDTO 넣을 자리에 T record 넣음, 수정/삭제만 OneMemoDTO 받기로 OneMemoMapper에 설정함
	int delete(T record);
	
	
	
	
	
	
	
	
	
	
}
