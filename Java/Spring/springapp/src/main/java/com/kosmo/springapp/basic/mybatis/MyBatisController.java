package com.kosmo.springapp.basic.mybatis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosmo.springapp.onememo.service.OneMemoDTO;

/*
	※ 작성순서(3->2->1순서로 작성하는게 편하다)
	1] 컨트롤러 작성(@Controller 어노테이션 부착, MyBatisController)
		1) 서비스 주입받음
		2) 서비스의 메소드 호출
	2] 서비스 작성(@Service 어노테이션 부착, MyBatisService)
		1) 생성자 인젝션으로 매퍼 인터페이스를 주입받는다 
		2) 매퍼 인터페이스의 메소드 호출
	3] 매퍼 인터페이스 작성(@Mapper 어노테이션 부착, MyBatisMapper-얘만 인터페이스)
		1) 어노테이션 방식(@XXX로 쿼리 작성)
		2) XML 방식(XML에 쿼리 작성, mybayis.xml): 마이바티스 구성을 위한 빈(MyBatisConfig.java)만들기 필수(복잡한 쿼리문, 동적쿼리는 XML방식 권장)
		
	※ 바인딩(쿼리 결과와 자바빈의 매핑) 흐름 절차
	1) MyBatisConfig.java에 등록한 빈이 사전에 컨테이너에 등록돼야한다
	2) 서비스의 메소드 호출 -> 서비스에서 매퍼 인터페이스의 메소드 호출(XML기반) 
		ex.서비스(MyBatisService)에서 mapper.getTimeByXml()반환
	3) 매퍼파일(mybatis.xml)에서 매퍼 인터페이스의 메소드명과 동일한 id값을 찾는다
	4) 찾은 해당 id값의 쿼리문이 실행된다
		
		
	※ 마이바티스 구성빈(MyBatisConfig) 미 코드시
	1. 어노테이션 방식은 매퍼인터페이스의 메소드 호출시 정상적으로 바인딩(자바객체와 쿼리문)되어 실행
	2. XML 방식은 매퍼인터페이스의 메소드 호출시 메소드와 쿼리문이 바인딩 안되어 500에러 발생(BindingException: Invalid bound statement)
	-> 해결책: 자바코드로 마이바티스 구성 빈을 작성한다
*/

@Controller
@RequestMapping("/Mybatis")
public class MyBatisController {
	
	//데이터소스 주입받음
	//JSP(HTML템플릿 엔진, HTML이라고 봐) -> 디스패처 서블릿 -> 컨트롤러 -> 중간에 서비스(업무처리 로직작성) 주고 -> DAO 즉 Model 놓고 -> DB 연결
	//서비스 호출, 모델 호출, 모델은 실제 DB에 연결함- 모델에서 메소드 만들어 CRUD작업 하면 됨, 서비스가 결과를 모델 거쳐서 받고, 다시 컨트롤러로 보내줌
	//컨트롤러는 요청을 JSP로 뿌려줌으로써 해결하는 것
	//서비스에서는 딜리트 인설트 둘 중 하나라도 안되면 바로 롤백시킴
	//서비스 호출!- 서비스 호출 전 서비스 주입받기(서비스 만들고옴)
	@Autowired
	private MyBatisService service;	
	
	@GetMapping("/annotation")//a태그 겟방식이니까
	public String annotation(Model model) {
		//서비스 호출
		model.addAttribute("time","ANNOTATION 방식:"+service.getTimeByAnnotation());
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	
	@GetMapping("/xml")
	public String xml(Model model) {
		//서비스 호출
		model.addAttribute("time","XML 방식:"+service.getTimeByXml());
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	
	@GetMapping("/If1.do")
	public String if1(@RequestParam Map map, Model model) {//Map으로 한번에 받으면 DTO 만들 필요 없어 좋음, List컬렉션 계속 add안해도됨
		//아래 3개가 컨트롤러가 하는 일 3개 끝
		//서비스 호출
		List<Map> articles = service.if1(map);//받은 파라미터 그대로 서비스로 넘겨줌
		for(Map article:articles) {//테스트용 출력
			System.out.println(article.get("TITLE"));//get(컬럼명=키), 컬럼명 무조건 대문자로
		}
		//데이터 저장
		model.addAttribute("message", "검색된 총 글 수:"+articles.size());
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	
	@GetMapping("/If2.do")
	public String if2(@RequestParam Map map, Model model) {
		//서비스 호출
		List<OneMemoDTO> articles = service.if2(map);//받은 파라미터 그대로 서비스로 넘겨줌
		//데이터 저장
		model.addAttribute("message", "검색된 총 글 수:"+articles.size());
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	
	@GetMapping("/choose.do")
	public String choose(@RequestParam Map map, Model model) {
		//서비스 호출
		List<OneMemoDTO> articles = service.choose(map);//받은 파라미터 그대로 서비스로 넘겨줌
		//데이터 저장
		model.addAttribute("message", "검색된 총 글 수:"+articles.size());
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	//※where절에 동적으로 SQL문 추가시 <where>태그 권장
	@GetMapping("/where.do")
	public String where(@RequestParam Map map, Model model) {
		//서비스 호출
		List<OneMemoDTO> articles = service.where(map);//받은 파라미터 그대로 서비스로 넘겨줌
		for(OneMemoDTO dto : articles) {
			System.out.println(dto.getNo());
		}
		//데이터 저장
		model.addAttribute("message", "검색된 총 글 수:"+articles.size());
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	
	@GetMapping("/trim1.do")
	public String trim1(@RequestParam Map map, Model model) {
		//서비스 호출
		List<OneMemoDTO> articles = service.trim1(map);//받은 파라미터 그대로 서비스로 넘겨줌
		for(OneMemoDTO dto : articles) {
			System.out.println(dto.getNo());
		}
		//데이터 저장
		model.addAttribute("message", "검색된 총 글 수:"+articles.size());
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	
	@GetMapping("/trim2.do")
	public String trim2(@RequestParam Map map, Model model) {
		//서비스 호출
		if(!(map.get("title")==null && map.get("content")==null)){//파라미터명이 키, 둘중하나만 넘어오거나 둘다 넘어오거나
			int affected = service.trim2(map);//즉 title이나 content 하나는 무조건 넘어와야함
			//데이터 저장
			model.addAttribute("message", "수정된 총 글 수:"+affected);
		}
		else {//아무것도 안넘어옴
			//데이터 저장
			model.addAttribute("message", "제목이나 내용 둘 중 하나는 전송하세요");
		}
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	
	@GetMapping("/set.do")
	public String set(@RequestParam Map map, Model model) {
		//서비스 호출
		if(!(map.get("title")==null && map.get("content")==null)){//파라미터명이 키, 둘중하나만 넘어오거나 둘다 넘어오거나
			int affected = service.set(map);//즉 title이나 content 하나는 무조건 넘어와야함
			//데이터 저장
			model.addAttribute("message", "수정된 총 글 수:"+affected);
		}
		else {//아무것도 안넘어옴
			//데이터 저장
			model.addAttribute("message", "제목이나 내용 둘 중 하나는 전송하세요");
		}
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	
	@GetMapping("/foreach.do")
	public String foreach(Model model) {//파라미터 안넘어와서 @RequestParam 필요없음
		//[글번호를 저장할 컬렉션]
		//1)파라미터가 리스트 계열 컬렉션인 경우
		//List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		//서비스 호출
		//List items = service.foreach(numbers);//받은 파라미터 없으니 인자로 안받고 대신 위의 numbers 넣음, List 타입 지정 안함
		
		//2)파라미터가 맵 계열 컬렉션인 경우
		Map<String,List> map =new HashMap<>();//밸류는 반드시 배열이나 List돼야함
		map.put("numbers", Arrays.asList(1,2,3,4,5,6,7,8,9,10));//키값 글번호
		//서비스 호출
		List items = service.foreach(map);
		//데이터 저장
		model.addAttribute("message", "검색된 총 글 수:"+items.size());
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
	
	@PostMapping("/foreachExam.do")
	public String foreachExam(@RequestParam("email") int[] emails, Model model) {
		//변수명 emails? 파라미터명과 같아야함, emails 굳이 쓰고싶으면 "" 사이에 email 파라미터명 써주면 됨
		//메일 1,2 선택후 삭제버튼 누르면 레코드 1,2 삭제됨
		int affected = service.foreachExam(emails);//배열전달
		//데이터 저장
		model.addAttribute("message", "삭제된 총 글 수:"+affected);
		//뷰정보 반환
		return "mybatis08/Mybatis";
	}
}
