package com.kosmo.springapp.onememo.service;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Lombok 라이브러리 : 어노테이션만 써도 게터,세터,빌더,별칭 등등 한방에 해결 가능하다!

@Getter//getter 만듦
@Setter//setter 만듦, DTO 만든것
@Builder//빌더
@NoArgsConstructor//기본생성자 만듦, 알아서 만들어져서 사실 필요없음 
@AllArgsConstructor//빌더 만들때 반드시 이거 필요
@Alias("oneMemoDto")//이전에 Map에 담았지만 이제 DTO에 담을거임, 마이바티스(전 아이바티스)에 있음, 별칭하는 것
public class OneMemoDTO {

	//멤버변수]
	private String no;
	private String title;
	private String content;
	private java.sql.Date postDate;
	private String id;
	
	//아래는 SELECT시 게시글 정보를 담기위한 필드들(실제 ONEMEMO테이블에는 없는 필드)
	private String name;//onememo에는 memo없지만 join해서 name에 넣을거임, 미리 변수 만듦 
	private String commentCounts;//각 글 번호(no)에 따른 댓글 총수 저장용
	
	//no에 따른 모든 한줄 댓글 목록 저장용
	//마이바티스 매퍼파일의 <resultMap>요소 하위인 <collection>요소의 property속성에 적용해야한다
	private List<LineCommentDTO> comments;//글번호에 따른 댓글들
	
	
	
	
	
	
	
	
	
	
}
