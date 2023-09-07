package com.kosmo.springapp.onememo.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.springapp.onememo.service.LineCommentDTO;
import com.kosmo.springapp.onememo.service.impl.LineCommentServiceImpl;

//@SessionAttributes("id") //id필요 -> 시큐리티는 주석
@RestController //페이지 전송x, 데이터만 보낼 것
@RequestMapping("/onememo/comments")
public class CommentController {

	/*
	 * 1] @RequestParam : key=value쌍으로 받을때
	 * 					  1)form으로 전송시
	 *                    <form action="my.do" enctype="x-www-form-urlencoded">
	 *                   	<input type="text" name="age"/>
	 *                    </form>
	 * 					  age=사용자 입력값 으로 전송됨
	 * 					  전송방식 즉 post 혹은 get상관없다
	 * 
	 *        			  2)쿼리 스트링으로 전송시
	 * 	                  <a href="my.do?age=30">클릭</a>
	 * 					  age=30으로 전송됨
	 * 					  자스에서 JSON으로 {age:20} 서버로 보내도 됨
	 * 					  단,contentType:"application/x-www-form-urlencoded"(디폴트)로
	 *
	 * 2] @RequestBody : JSON형태의 문자열로 받을때 
	 * 		             즉 자스에서 JSON.stringify(JSON객체)해서 보내야 한다
	 * 			         그리고 contentType:"application/json" 설정 필요(JSON보낸다고 선언)
	 *                   매핑용 어노테이션의 produces = "text/plain;charset=UTF-8"은(부트에서 필요 없음)
	 *                   응답바디에 Content-type:text/plain;charset=UTF-8 쓰는 거와 같다(부트에서 필요 없음)
	 *
	 */
	//서비스 주입
	@Autowired
	private LineCommentServiceImpl commentService;
	
	//Map으로 반환: JACKSON라이브러리 설치시에만(부트는 기본설치), 그렇지 않은 경우 Map을 일일이 JSON형식으로 바꿔야함
	//댓글 입력 그리고 입력한 댓글 반환
	@PostMapping("/Write.do")//post로 옴
	public Map write(
			//@ModelAttribute("id") String id,
			Authentication auth, 
			@RequestParam Map paramMap) {//map에는 no, lno(빈값), lineComment(사용자 입력값) 있음
		//한줄 댓글 작성자의 아이디 설정
//		paramMap.put("id",id);//시큐리티 미사용시

		//시큐리티
		paramMap.put("id", ((UserDetails)auth).getUsername());
		
		//서비스 호출
		commentService.insert(paramMap);//입력한 행의 키값(lno) 반환-수정/삭제시 사용 혹은 바로 상세보기로 이동시 키 필요함
		LineCommentDTO dto=commentService.selectOne(paramMap);
		paramMap.put("name", dto.getName());//한줄 댓글 작성자의 이름 설정
		paramMap.put("lpostDate",dto.getLpostDate());//입력한 댓글의 작성일 설정
		//데이터 반환(뷰정보 반환x)
		return paramMap;//방금 입력한 댓글 반환, 인자로 받는 것들은 해당 jsp에서 form태그 중 파일 여러개 랒아보면됨
	}
	
	//댓글 작성자의 아이디를 문자열로 반환
	@GetMapping("/GetId.do")
	public String getId(@ModelAttribute("id") String id,@RequestParam String lno) {//파라미터 int로 받아도 됨
		//서비스 호출 및 데이터 반환(댓글작성자의 아이디 문자열로 반환)
		return commentService.getIdByLno(lno);
	}
	
	@PostMapping("/Edit.do")
	public LineCommentDTO edit(@ModelAttribute("id") String id,LineCommentDTO dto) {
		//서비스 호출  
		commentService.update(dto);//영향받은 행의 수 반환
		Map map = new HashMap();
		map.put("lno",dto.getLno());
		//데이터 반환
		return commentService.selectOne(map);		
	}
	
	@DeleteMapping("/Delete.do")
	public LineCommentDTO delete(@ModelAttribute("id") String id, @RequestBody LineCommentDTO dto) {//로그인한 사람만 볼 수 있어야함, id 받아야함
		//서비스 호출
		//삭제전 데이터 가져오기
		Map map = new HashMap<>();
		map.put("lno", dto.getLno());
		LineCommentDTO willDeleteComment= commentService.selectOne(map);//삭제될 댓글
		commentService.delete(dto);//원래 리퀘스트바디 어노테이션 Map map이었는데 LineCommentDTO 형변환도 된다
		//데이터 반환-삭제한 코멘트 정보 반환
		return willDeleteComment;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
