package service;

import java.util.List;
import java.util.Map;

//제너릭 인터페이스
public interface DaoService<T> {//BBSDto외에 다른 것도 사용가능(T는 레코드 하나를 받을 자료구조 ex.BBSDto, Map도 가능 따로 Dto만들 필요 없어 편함)
	
	//리스너(MyContextListener)에서 서블릿 컨텍스트 즉 어플리케이션 영역에 저장한 데이터 소스의 속성명
	String DATA_SOURCE="DataSource";
	
	void close();//데이터베이스종료 메소드
	List<T> selectList(Map map);//글전체 목록반환 메소드(BBSDto객체 List에 담음), 확장성 위해 Map전달(검색시 파라미터 받으려고), 인터페이스로 BBSDto받으면 T가 바뀜
	T selectOne(String ... one);//컨텐츠(레코드) 하나 반환
	int getTotalRecordCount(Map map);//전체 레코드 수
	int insert(T dto);//글입력용 메소드(Map으로해도 됨,전자정부는 DTO로 한대)
	int update(T dto);//수정용 메소드(똑같이 T 받음, Map가능)
	int delete(T dto);//삭제용 메소드
	
}
