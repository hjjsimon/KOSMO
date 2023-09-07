package jdbc25.callable;

import java.sql.Types;

import jdbc25.service.IConnectImpl;
/*
 create or replace PROCEDURE SP_INSMEMBER(
    USERNAME IN MEMBER.USERNAME%TYPE,--파라미터 넣음(지금 모두 IN파라미터), 값3개 받아야함 USERNAME,PASSWORD,NAME
    PASSWORD VARCHAR2,--같은타입~ 그냥 이렇게 써도 된다~ 뭔지 찾기 귀찮을 때 위처럼
    NAME MEMBER.NAME%TYPE,
    RTVAL OUT NVARCHAR2--얘만 OUT파라미터
)
IS
BEGIN
    INSERT INTO MEMBER(USERNAME,PASSWORD,NAME) VALUES(USERNAME,PASSWORD,NAME);--이건 변수
    IF SQL%FOUND THEN --최근 SQL문이 내 코드 영향시 TRUE
        RTVAL := '입력성공';
        COMMIT;
    END IF;
    EXCEPTION--입력에러시 예외처리
    WHEN OTHERS THEN 
        ROLLBACK;--하나뿐이라 롤백의미 없지만 여기에 넣는형식이다~ 공부~
        RTVAL:='입력실패:중복키 혹은 입력값이 너무 커요';--10자 넘게 입력 등
END;
 */

public class InsertProc extends IConnectImpl {//프로시져는 반환값 없음, 함수 호출이랑 이것만 다름!

	public InsertProc() {
		connect(ORACLE_URL, "kosmo", "kosmo1234");
	}
	@Override
	public void execute() throws Exception {
		//1]프로시저를 실행하기 위한 CallableStatement객체 얻기
		/*
	      Connection객체의 prepareCall("{call 프로시저명(?,?,...)}")
	    메소드 호출
	    -인 파라미터 설정시에는 setXXXX(파라미터인덱스,값)로
	    -아웃 파라미터 설정시에는
	    registerOutParameter(파라미터인덱스,java.sql.Types클래스의 int형 상수)
	    */
		csmt=conn.prepareCall("{call SP_INSMEMBER(?,?,?,?)}");
		//2]파라미터 설정
		//2-1]인파라미터(?)설정: 오라클의 인파라미터에 해당하는 ? 설정 setxxxx()로
		csmt.setString(1, getValue("아이디"));
		csmt.setString(2, getValue("비밀번호"));
		csmt.setString(3, getValue("이름"));
		//2-2]오라클의 아웃파라미터에 해당하는? 설정 registerOutParameter()로
		csmt.registerOutParameter(4, Types.NVARCHAR);
		//3]프로시저 실행-execute(): 결과값 4번?에 저장 
		System.out.println(csmt.execute());
		//4]아웃파라미터에 저장된 값 읽어오기: CallableStatement객체의 getxxxx()계열 메소드
		System.out.println("프로시저의 아웃 파라미터 값:"+csmt.getString(4));
		//5]자원반납
		close();
	}
	
	public static void main(String[] args) throws Exception {

		new InsertProc().execute();
	}

}
