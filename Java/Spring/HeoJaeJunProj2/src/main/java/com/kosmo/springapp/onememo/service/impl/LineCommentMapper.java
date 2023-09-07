package com.kosmo.springapp.onememo.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kosmo.springapp.onememo.service.LineCommentDTO;

@Mapper
public interface LineCommentMapper {

	//글번호에 따른 모든 댓글 목록 조회
	List<LineCommentDTO> findAllByNo(Map map);	
	//코멘트 입력
	int save(Map map);
	//코멘트 하나 수정(키로 수정하므로 하나만 선택!)
	int updateByLno(LineCommentDTO dto);
	//코멘트 하나 삭제
	int deleteByLno(LineCommentDTO dto);
	//코멘트 하나 조회
	LineCommentDTO findByLno(Map map);
	//해당 글번호의 모든 댓글 삭제
	int deleteByNo(Map map);
	//댓글번호로 작성자 아이디 찾기
	String findIdByLno(String lno);
	
	
	
	
}
