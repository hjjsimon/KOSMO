package com.kosmo.springapp.onememo.service;

import java.util.List;

import org.apache.ibatis.type.Alias;

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
@Alias("oneMemoDto")
public class OneMemoDTO {

	//멤버변수]
	private String no;
	private String title;
	private String content;
	private java.sql.Date postDate;
	private String id;
	
	//아래는 SELECT시 게시글 정보를 담기위한 필드들(실제 ONEMEMO테이블에는 없는 필드)
	private String name;
	private String commentCounts;//각 글 번호(no)에 따른 댓글 총수 저장용
	
	//no에 따른 모든 한줄 댓글 목록 저장용
	//마이바티스 매퍼파일의 <resultMap>요소 하위인 <collection>요소의 property속성에 적용해야한다
	private List<LineCommentDTO> comments;//글번호에 따른 댓글들
	
	
	
	
	
	
	
	
	
	
}
