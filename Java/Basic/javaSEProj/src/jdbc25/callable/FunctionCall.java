package jdbc25.callable;

import java.sql.Types;

import jdbc25.service.IConnectImpl;

/*
 create or replace FUNCTION TO_ASTERISK(VAL VARCHAR2)
    RETURN VARCHAR2
IS
BEGIN
    RETURN RPAD(SUBSTR(VAL,1,1),LENGTH(VAL),'*');
END;

->오라클에 정의된 함수나 프로시저 사용시 CallableStatemenet 객체를 사용한다
->자바에서 함수나 프로시저 실행시(혹은 기타 DDL,DCL 실행시) 무조건 execute()메소드로 실행(데이터 중 s(executequery),i,u,d(executeupdate) 빼고 DDL,DCL은 다 execute()쓰면됨)
 */

public class FunctionCall extends IConnectImpl{
	
	public FunctionCall() {
		connect(ORACLE_URL, "SCOTT", "scott");
	}
	
	@Override
	public void execute() throws Exception {
		/*
	  	1]오라클에 정의된 함수를 실행하기 위한 CallableStatement객체 생성(얘는 PreparedStatement 상속, 그래서 ? 씀)-> 근데 자바에서 함수 실행할 일은 없ㅇ므
	    -Connection객체의 prepareCall()메소드로  생성(Statement생성시 Connection객체에 createStatement()씀, PreparedStatemenet 생성시 Connection객체에 prepareStatement()) -> 근데 .찍으면 나와서 안외워도 됨, prepared 아닌거 주의
	    -자바에서 함수호출형식: 
	    	prepareCall("{? = call 함수명(?,?...)}"); 
	    	->얘도 prepared처럼 함수실행 명령어를 생성시 문자열로 미리 써줌(상속받았으니까)
	    	->?= 앞의 ?는 함수가 반환하는 값을 담는 아웃파라미터(함수는 무조건 값 반환함),뒤의 ?,?...는 인파라미터임, 여튼 ?아웃파라미터 값을 가져오면됨 
	    -첫번째 물음표는 반환값(아웃파라미터,?라서 이것도 설정해야함,타입만 지정하면됨, 값은 함수로 나옴)을 의미, 반환값은 파라미터 설정시 java.sql.Types클래스의 int형 상수로 설정한다.
	     아웃파라미터 설정시 registerOutParameter()메소드 사용, 인파라미터 매개변수 설정시는 기존 PreparedStatement객체와 동일
		 */		
		csmt=conn.prepareCall("{?=call TO_ASTERISK(?)}");//이번에 csmt 처음씀, 함수 받는 값 1개라 ?(인파라미터) 1개임
		//2]인파라미터 설정: 매개변수는 즉 인파라미터는 setXXX()계열 메소드로 설정
		csmt.setString(2, getValue("문자열"));//아웃파라미터는 순서 무조건 1번, ?인파라미터는 두번째로 나온거니까 2씀
		//3]아웃파라미터 설정: 반환값에 해당하는 파라미터 설정(인덱스 무조건 1번)
		csmt.registerOutParameter(1, Types.VARCHAR);//뒤에값이 int sqlType? 반환값이 return VARCHAR2임->Types.VARCHAR로 함
		//4]함수 실행: 무조건 execute()사용, ?다 세팅했으니까 실행하면됨
		System.out.println(csmt.execute());//RS면 true, 영향받은 행이 있거나 없으면 false임, 함수라 무조건 false 나옴
		//5]반환값은 getxxx()계열로 얻어온다(인덱스 무조건 1번)
		System.out.println("함수의 반환값:"+csmt.getString(1));//아웃파라미터 인덱스는 당연히 1
		//6]자원반납
		close();
	}
	
	public static void main(String[] args) throws Exception {
		
		new FunctionCall().execute();
	}

	

}
