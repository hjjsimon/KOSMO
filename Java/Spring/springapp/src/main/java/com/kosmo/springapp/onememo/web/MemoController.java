package com.kosmo.springapp.onememo.web;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.springapp.onememo.service.LineCommentDTO;
import com.kosmo.springapp.onememo.service.ListPagingData;
import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;
import com.kosmo.springapp.onememo.service.PagingUtil;

@Controller
@RequestMapping("/onememo/bbs")
//@SessionAttributes({"id"})//스프링 시큐리티 사용시 주석
public class MemoController {

	//서비스 주입
	@Autowired
	private OneMemoService<OneMemoDTO> memoService;
	
	@ExceptionHandler({HttpSessionRequiredException.class})//id 안넘어왔을 때의 에러 처리
	public String error(Model model, Exception e) {
	
		//에러에 따라 에러 메시지 및 포워드 URL변경
		String eProperty=null, eMessage=null, eUrl=null; //e는 error라는 뜻
		if(e instanceof HttpSessionRequiredException) {//로그인 안하고 게시판 누른 상황
			eProperty = "NotMember";
			eMessage = "로그인 후 이용하세요";//아이디, 비번 틀린 상황은 아님
			eUrl = "onememo09/member/Login";
		}
		//데이터 저장
		model.addAttribute(eProperty, eMessage);//속성명,메세지
		//뷰정보 반환
		return eUrl;
	}
	
	//목록처리]
	@RequestMapping(value="/List.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String list(
			//@ModelAttribute("id") String id, //핸들러 인터셉터로 컨트롤러 넘기기 사전에 인증여부 판단시 주석(인터셉터 쓰면 주석하는듯)
			@RequestParam Map map,//map에 searchWord,searchColumn 채워짐
			@RequestParam(required = false, defaultValue = "1", value = PagingUtil.NOWPAGE) int nowPage, 
				//NOWPAGE 받아 nowPage에 저장, 안넘어오면 1 저장
			HttpServletRequest req,
			Model model
			) {
		//세션영역에서 "id"를 가져옴, id 안넘어오면 에러남( @SessionAttributes({"id"})에 설정한 id 그대로 @ModelAttribute("id") 넣어야함)
		
		//서비스 호출
		ListPagingData<OneMemoDTO> listPagingData= memoService.selectList(map, req, nowPage);//map에 searchWord,searchColumn 채워짐
		//데이터 저장
		model.addAttribute("listPagingData", listPagingData);
		//뷰정보 반환
		return "onememo09/bbs/List";
	}
	
	//입력폼으로 이동]
	//@RequestMapping(value = "/Write.do", method = RequestMethod.GET)
	@GetMapping("/Write.do")//스프링 4.3부터 HTTP메소드별 맞는 어노테이션지원 
	public String write(
			//@ModelAttribute("id") String id
			) {
		//로그인 돼야 입력가능하게 해야함, URL만 치고 들어올수도 있음, 위에 @ModelAttribute 써서 꼭 로그인 후 들어가도록함
		//뷰정보 반환
		return "onememo09/bbs/Write";
	}
	
	//입력처리]
	@PostMapping("/Write.do")
	public String writeOk(
			//@ModelAttribute("id") String id,
			Authentication auth,
			@RequestParam Map map, Model model) {
		//Write.jsp 확인지금 map에는 title, content만 담김, id는 따로 맵에 저장해야함
		//서비스 호출 전 세션영역에서 아이디 읽어와(@ModelAttribute) 아이디를 맵에 저장
		
		//시큐리티 사용시 주석
//		map.put("id", id);//시큐리티 미사용시
		//서비스 호출
		//서비스 호출전 아이디를 맵에 저장
		//스프링 시큐리티 적용시 인증(로그인)되었다면 Authentication객체에
		//로그인과 관련된 정보가 전달됨 로그인이 안되어 있으면 auth는 null
		System.out.println("[Authentication객체]");
		System.out.println("auth:"+auth);
		UserDetails userDetails = (UserDetails)auth.getPrincipal();
		System.out.println("아이디:"+userDetails.getUsername());
		System.out.println("비밀번호:"+userDetails.getPassword());
		System.out.println("부여된 권한들");
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)userDetails.getAuthorities();
		for(GrantedAuthority authority : authorities) {
			System.out.println(authority.getAuthority()+" ");//부여된 권한들 다 출력
		}
		map.put("id", userDetails.getUsername());
		
		
		int affected = memoService.insert(map);//OneMemoService로 메소드 만들러 이동
		if(affected == 0) {//입력 실패 OneMemoServiceImpl 확인
			model.addAttribute("InputError","입력오류입니다. 다시 입력해주세요");
			return "onememo09/bbs/Write"; //포워드 시키면 파라미터 유지되므로 param.xxx 그대로 값 유지됨
		}
		//뷰정보 반환 
		//return "/onememo09/bbs/List.do";
		//onememo09/bbs/List.do 만 쓰면 .jsp랑 접두사 붙음, 앞에 / 붙여줘도 거기가 붙어서 에러남
		//위의 목록처리로 포워드 시킨것, list()~~~가 호출됨
		return "forward:/onememo/bbs/List.do";//forward(디폴트): 붙이면 접두어,접미어가 붙지 않는다	
											  //redirect: 리다이렉트 방식 이동
	}
	//상세보기]-GET,POST(수정완료시) 둘다 받아야하므로 RequestMapping사용(POST처리 빼면 405에러)
	@RequestMapping(value="/View.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String view(
			//@ModelAttribute("id") String id,
			@RequestParam Map map,Model model) {
		//로그인 해야 상세보기 가능해야하므로 @ModelAttribute id 받아야함, 글번호는 map으로 받음
		//서비스 호출(레코드 하나)
		OneMemoDTO record= memoService.selectOne(map);
		//줄바꿈
		record.setContent(record.getContent().replace("\r\n", "<br/>"));
		//데이터 저장(모델 필요)
		model.addAttribute("record",record);
		//뷰정보 반환
		return "onememo09/bbs/View";
	}
	
	//삭제(컨트롤러에서는 트랜잭션처리 안함)
	@GetMapping("/Delete.do")
	public String delete(
		//@ModelAttribute("id") String id,
		OneMemoDTO dto, Model model) {
		//서비스 호출
		int deleteCommentCount = memoService.delete(dto);//영향받은 행의 수 반환
		if(deleteCommentCount == -1) {//삭제불가, 에러발생한것
			model.addAttribute("FAILURE","삭제할 수 없어요");
			return "forward:/onememo/bbs/View.do";
		}
		System.out.println("삭제된 총 댓글 수:"+deleteCommentCount);
		//뷰정보 반환]-목록을 처리하는 컨트롤러로 이동
		return "forward:/onememo/bbs/List.do";	
}
	
	//수정폼으로 이동 - 스프링에서는 필터역할의 인터셉터 사용, ModelAttribute 빼도됨
	@GetMapping("/Edit.do")
	public String edit(
			//@ModelAttribute("id") String id,
			@RequestParam Map map,Model model) {
		//서비스 호출(레코드 하나)
		OneMemoDTO record= memoService.selectOne(map);
		//데이터 저장(모델 필요)
		model.addAttribute("record",record);//파라미터로 받은걸 레코드에 저장
		//뷰정보 반환
		return "onememo09/bbs/Edit";	
	}
	
	//수정처리]
	@PostMapping("/Edit.do")
	public String editOk(
			//@ModelAttribute("id") String id, 
			OneMemoDTO dto, Model model) {
		//서비스 호출
		int affected = memoService.update(dto);//OneMemoService로 메소드 만들러 이동
		if(affected == 0) {//입력 실패 OneMemoServiceImpl 확인
			model.addAttribute("InputError","수정오류입니다. 다시 입력해주세요");
			return "onememo09/bbs/Edit"; //포워드 시키면 파라미터 유지되므로 param.xxx 그대로 값 유지됨
		}
		//뷰정보 반환 
		return "forward:/onememo/bbs/View.do";
	}
	
	
	
	
	
	
	
	
	
	
}
