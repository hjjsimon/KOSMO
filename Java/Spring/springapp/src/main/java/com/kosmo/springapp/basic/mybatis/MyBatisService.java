package com.kosmo.springapp.basic.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kosmo.springapp.onememo.service.OneMemoDTO;

//※서비스의 메소드명은 업무처리 로직에 관련있는 이름으로 정의하자
@Service
public class MyBatisService {
	
	//일반클래스, 스프링컨테이너에 등록해야함, 사용자 요청 받는게 아니라 업무처리 로직 작성하는 서비스 레이어
	//마이바티스 매퍼(인터페이스) 주입받음, 근데 어노테이션 안붙여도 됨
	//생성자 인젝션으로 MyBatisMapper(DAO역할)를 주입받자(생성자 방식 추천)
	private MyBatisMapper mapper;//인터페이스 가져옴

	@Autowired//생략가능
	public MyBatisService(MyBatisMapper mapper) {
		this.mapper = mapper;
	}
	
	public String getTimeByAnnotation() {
		/*
		 매퍼의 getTimeByAnnotation()호출시 어노테이션의 쿼리가 실행된다
		 실행된 쿼리 결과를 메소드의 반환타입으로 매핑시켜 반환한다
		 
		 mapper의 메소드 이걸 호출하면, 위의 쿼리문이 실행됨
		 그리고 그 결과를 String으로 매핑하여 호출한 현재 장소로 반환해줌
		 그냥 return 메소드 하면 쿼리결과가 반환된다~~
		 */
		return mapper.getTimeByAnnotation();
		
	}
	
	public String getTimeByXml() {//메소드명이 식별자(mybatis.xml의 id속성!)
		/*
		 매퍼의 getTimeByXml()호출시 매퍼파일(mybatis.xml)의 쿼리가 실행된다
		 메소드명과 id, 메소드 반환타입과 resultType 동일하게 세팅필수
		 실행된 쿼리 결과를 메소드의 반환타입으로 매핑시켜 반환한다
		 */
		return mapper.getTimeByXml();
	}

	public List<Map> if1(Map map) {
		//여기에 맘대로 쓰고 콕 찍어서 MyBatisMapper.java에 만들면 됨
		return mapper.findWithTitleLike(map);//받은 파라미터 map 그대로 전달
	}

	public List<OneMemoDTO> if2(Map map) {
		return mapper.findWithColumnsLike(map);
	}

	public List<OneMemoDTO> choose(Map map) {
		return mapper.findWithColumnsLikeChoose(map);
	}

	public List<OneMemoDTO> where(Map map) {
		return mapper.findWithColumnsLikeWhere(map);
	}

	public List<OneMemoDTO> trim1(Map map) {
		return mapper.findWithColumnsLikeTrim1(map);
	}

	public int trim2(Map map) {
		return mapper.updateWithColumnsLikeTrim2(map);
	}

	public int set(Map map) {
		return mapper.updateWithColumnsLikeSet(map);
	}

	public List foreach(List<Integer> numbers) {
		//파라미터가 리스트일때
		return mapper.findAll(numbers);
	}
	
	public List foreach(Map<String, List> map) {
		//파라미터가 맵일때
		return mapper.findAll(map);
	}
	//아래 글 삭제 로직을 트랜잭션으로 처리하자
	//즉 참조하고 있는 댓글(자식)이 있는 경우 메일(=레코드)(부모)에러발생
	//이때 삭제된 모든 글을 다시 롤백한다, 조건 2개 확인하여 안되면 복구하는게 트랜잭션
	//원래 자식 참조중에도 삭제하려면 cascade, 그거 없이 실행한다고 했었음
	
	/*
	//트랜잭션 미적용 -> 댓글이 없는 글은 삭제됨, 이거 쓰면 자식 참조중에도 삭제됨, 즉 댓글(COMMENTS 테이블) 달려도 삭제, 이러면 안됨
	public int foreachExam(int[] emails) {
		return mapper.deleteEmail(emails);
	}
	*/
	
	//트랜잭션 적용 - 자식 참조중에는 삭제 불가능하게 함, 롤백시킴
	@Autowired//트랜잭션 템플릿 주입
	private TransactionTemplate transactionTemplate;
	
	public int foreachExam(int[] emails) {
		//execute가 함수형 인터페이스, 아래 new 대신 자바 람다식(화살표함수)사용
		/*
		transactionTemplate.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		*/
		int deleteCount = transactionTemplate.execute(status->{
			//execute 메소드로 실행(deleteEmail()실행), 반환한 affected 값이 deleteCount에 저장되고, 맨 밑에서 반환
			int affected = 0;//영향받은 행의 수 저장, 뭐 어쩌구 인자 1개라고 했었음
			
			try {//자식 참조중에 에러, try~
				affected = mapper.deleteEmail(emails);
			}
			catch (Exception e) {
				e.printStackTrace();
				affected = -1;//삭제시 에러난 경우 -1 저장, 이미 삭제된건 롤백된다, 롤백 호출할 필요 없음
			}
			return affected;
		});
		return deleteCount;
	}
	
	
	
}
