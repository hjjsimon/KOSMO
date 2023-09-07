package com.kosmo.springapp.onememo.service;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListPagingData<T> {

	//레코드 목록 데이터 저장(T에 Map, OneMemoDTO.. 뭐든 담을 수 있음)
	private List<T> records;
	//맵에 페이징 관련 데이터 저장
	private Map map;
	//페이징 표시용 문자열 저장
	private String pagingString;
	
	
	
}
