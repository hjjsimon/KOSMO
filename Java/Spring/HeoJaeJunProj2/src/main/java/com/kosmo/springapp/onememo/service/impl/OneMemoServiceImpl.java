package com.kosmo.springapp.onememo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kosmo.springapp.onememo.service.ListPagingData;
import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;
import com.kosmo.springapp.onememo.service.PagingUtil;



//여기서 서비스 로직 호출, 로직은 서비스에만 작성,  DAO는 CRUD로직만 작성함
//서비스 로직 호출해서 @Service, 컴포넌트로 미리 생김
/*
	 id 미지정시 ID값은 소문자로 시작하는 클래스명이 됨(컨테이너가 관리하는 인스턴스 변수임)
	 ex) OneMemoServiceImpl클래스인 경우 ID값은 oneMemoServiceImpl
	 지정도 가능 @Service(value="임의의 ID값")
 */
@Service("memoService")
public class OneMemoServiceImpl implements OneMemoService<OneMemoDTO>{

	//매퍼 인터페이스 주입
	@Autowired
	private OneMemoMapper mapper;
	
	@Override
	public boolean isLogin(Map map) {
		return mapper.findByIdAndPwd(map);//id와 pwd조회
	}

	//속성파일(paging.properties)에서 페이지 사이즈 수/블록 페이지 수 읽어오기
	@Value("${"+PagingUtil.PAGE_SIZE+"}")
	private int pageSize;
	
	@Value("${"+PagingUtil.BLOCK_PAGE+"}")
	private int blockPage;

	@Override
	public ListPagingData<OneMemoDTO> selectList(Map map, HttpServletRequest req, int nowPage) {
		
		//[페이징 로직 시작]
		//1. 총 레코드 수 : 테이블에서 조회
		//	페이지사이즈 수/블록페이지 수 : 속성파일에서 읽기(PlaceHolderConfig.java, paging.properties 확인)
		//	현재 페이지 번호: 인자로 넘긴다
		//-> 위 4개의 값을 맵에 설정
		int totalCount = mapper.count(map);
		map.put(PagingUtil.TOTAL_RECORD_COUNT, totalCount);
		map.put(PagingUtil.PAGE_SIZE, pageSize);//속성파일값
		map.put(PagingUtil.BLOCK_PAGE, blockPage);
		map.put(PagingUtil.NOWPAGE, nowPage);//인자로 받음
		
		//시작/끝 행번호 및 총 페이지 수 설정: PagingUtil.setMapForPaging() 호출
		PagingUtil.setMapForPaging(map);//start,end 세팅됨
		
		//글 전체 목록 얻기
		List records = mapper.findAll(map);
		
		//페이징 문자열 얻기
		String pagingString = PagingUtil.pagingBootStrapStyle(totalCount, pageSize, blockPage, 
									nowPage,req.getContextPath()+ "/onememo/bbs/List.do?");//List.jsp말고 .do로 걸어야함
		
		//페이징과 관련된 정보 및 모든 목록을 담는 ListPagingData 객체 생성
		ListPagingData<OneMemoDTO> listPagingData = ListPagingData.builder()//빌더패턴으로 만듦
													.records(records) //글 전체 목록 설정
													.map(map)//페이징 관련 데이터 설정
													.pagingString(pagingString)//페이징 문자열 설정
													.build(); //필드명 그대로 세터, 빌더패턴은 필요한 속성만 .을 붙여서 만듦
		//쿼리문 작성(onememo.xml)->List.jsp 결과출력
		return listPagingData;
	}

	@Override
	public OneMemoDTO selectOne(Map map) {
		return mapper.findByNo(map);
	}

	@Override
	public int insert(Map map) {
		//Write.jsp에서 InputError 처리해주기
		int affected = 0;
		try {
			affected = mapper.save(map);//여기서 문제시 에러가 남
		}
		catch (Exception e) {
			e.printStackTrace();//에러나면 0 반환
		}
		return affected;
	}

	@Override
	public int update(OneMemoDTO record) {
		//Edit.jsp에서 InputError 처리해주기
		int affected = 0;
		try {
			affected = mapper.updateByNo(record);//여기서 문제시 에러가 남
		}
		catch (Exception e) {
			e.printStackTrace();//에러나면 0 반환
		}
		return affected;
	}
	
	
	/*
	 //https://mybatis.org/spring/transactions.html
	 [삭제 작업을 트랜잭션 처리하기]
	 방법1] - 스프링 레거시(메이븐) 기준(부트는 아래 빈 설정파일 없음)
	 		메이븐 기준 - 우리는 빈으로 DatabaseConfig.java에 등록해놓음, 레거시로 설정파일에 할때는 root-contex.xml로 해도 된다는 뜻
	 1.빈 설정파일(root-context.xml)에 DataSourceTransactionManager 등록
	 2.빈 설정파일(root-context.xml)에 TransactionTemplate 등록
	 3.TransactionTemplate 주입받는다
	 4.TransactionTemplate객체의 execute()메소드로 트랜잭션 작업 실행
	   You can omit to call the commit and rollback method
	   using the TransactionTemplate.(자동으로 커밋,롤백됨)
	 
	 방법2] - 스프링 부트 및 레거시 기준
	 		@Configuration 및  @Bean써서 자바코드로 빈 등록(DatabaseConfig.java에서 시행) 
	
	 ※트랜잭션 작업에서 런타임 오류시 롤백,정상 실행시 커밋된다
	 ※트랜잭션 처리는 @Service어노테이션이 붙은 클래스에서 하자
	*/
	//여기서 트랜잭션 처리(여러개중 하나라도 실패시 롤백시킴)

	//트랜잭션 처리 관련 빈 주입받기
	@Autowired
	private TransactionTemplate template;//타입기반이라 이름 안맞아도 됨
	@Autowired
	private LineCommentMapper commentMapper;//댓글 건드려야해서 이거 주입받음
	
	@Override
	public int delete(OneMemoDTO record) {
		int affected = 0;//삭제된 댓글의 총 수 저장용
		try {
			Map map = new HashMap<>();//deleteByNo에 map넣어야함
			map.put("no", record.getNo());//no받아옴, no로 삭제해야하니까
			//TransactionCallback<T>: 타입파라미터 T는 트랜잭션 처리 작업 후 반환할 타입으로 지정
			/*
			 * 방법1]
			affected = template.execute(new TransactionCallback<Integer>() {//인자가 함수형 인터페이스임
				//<T>에 Integer하면 int로 받고(오토언박싱) String으로 하면 String으로 받는 것 
				//인터페이스니까 추상메소드 하나 있음, 오버라이드!
				@Override
				public Integer doInTransaction(TransactionStatus status) {
					//댓글 있어도 삭제하도록 만든 것, 댓글먼저 삭제하니까!
					//글번호에 따른 모든 댓글 삭제(자식부터 삭제해야함)
					int deletedCommentCount = commentMapper.deleteByNo(map);
					//일부러 트랜잭션 테스트를 위한 에러를 발생시키자, 중간에 에러 발생시 롤백시킴, try함
					//////Integer.parseInt("에러");
					//해당 원본 글 삭제
					mapper.deleteByNo(record);
					return deletedCommentCount;//doInTransaction()의 반환값이 execute()메소드의 반환값이다 
				}
			});*/
			//방법2] 방법1을 람다함수로 변경
			affected = template.execute(status -> {
					//댓글 있어도 삭제하도록 만든 것, 댓글먼저 삭제하니까!
					//글번호에 따른 모든 댓글 삭제(자식부터 삭제해야함)
					int deletedCommentCount = commentMapper.deleteByNo(map);
					//일부러 트랜잭션 테스트를 위한 에러를 발생시키자, 중간에 에러 발생시 롤백시킴, try함
					//////Integer.parseInt("에러");
					//해당 원본 글 삭제
					mapper.deleteByNo(record);
					return deletedCommentCount;//doInTransaction()의 반환값이 execute()메소드의 반환값이다 
				});
			
		}
		catch (Exception e) {
			e.printStackTrace();//콘솔로 빨간줄 출력
			return -1;
		}
		
		return affected;
	}
	
	

}
