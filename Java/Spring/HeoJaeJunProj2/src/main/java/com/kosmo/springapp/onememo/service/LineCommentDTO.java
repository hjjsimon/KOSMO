package com.kosmo.springapp.onememo.service;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("commentDTO")
public class LineCommentDTO {

	private String lno;
	private String lineComment;
	private java.sql.Date lpostDate;
	private String no;
	private String id;
	private String name;//댓글 작성자 이름
	
}
