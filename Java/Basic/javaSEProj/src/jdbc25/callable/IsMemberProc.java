package jdbc25.callable;

import java.sql.Types;

import jdbc25.service.IConnectImpl;
/*
create or replace PROCEDURE SP_ISMEMBER(
    USERNAME_ IN MEMBER.USERNAME%TYPE,--지울때 아이디만 받으면 됨
    PASSWORD_ IN MEMBER.PASSWORD%TYPE,
    RTVAL OUT NUMBER
)
IS
    FLAG NUMBER(1);--변수설정
BEGIN
    SELECT COUNT(*) INTO FLAG FROM MEMBER WHERE USERNAME=USERNAME_;--받은 아이디로 조회, 있으면 COUNT 1, 없으면 0
    IF FLAG=0 THEN RTVAL := -1;--아이디 불일치
    ELSE SELECT COUNT(*) INTO FLAG FROM MEMBER WHERE USERNAME=USERNAME_ AND PASSWORD=PASSWORD_;--아이디 일치, 그냥 패스워드 일치가면 안됨, SQL USERNAME 또 써야하는듯
        IF FLAG=0 THEN RTVAL:=0;--비밀번호 불일치
        ELSE RTVAL:=1;--회원
        END IF;
    END IF;
END;
 */

public class IsMemberProc extends IConnectImpl {//프로시져는 반환값 없음, 함수 호출이랑 이것만 다름!

	public IsMemberProc() {
		connect(ORACLE_URL, "kosmo", "kosmo1234");
	}
	@Override
	public void execute() throws Exception {
		//1]프로시저를 실행하기 위한 CallableStatement객체 얻기
		csmt=conn.prepareCall("{call SP_ISMEMBER(?,?,?)}");
		//2]파라미터 설정
		//2-1]인파라미터(?)설정: 오라클의 인파라미터에 해당하는 ? 설정 setxxxx()로
		csmt.setString(1, getValue("아이디"));
		csmt.setString(2, getValue("비밀번호"));
		//2-2]오라클의 아웃파라미터에 해당하는? 설정 registerOutParameter()로
		csmt.registerOutParameter(3, Types.NUMERIC);//특이하게 numeric으로 담음
		//3]프로시저 실행-execute(): 결과값 4번?에 저장 
		csmt.execute();
		//4]아웃파라미터에 저장된 값 읽어오기: CallableStatement객체의 getxxxx()계열 메소드
		int result=csmt.getInt(3);
		switch(result) {
			case -1:System.out.println("아이디가 존재하지 않아");break;
			case 0:System.out.println("아이디는 일치하나 비밀번호가 불일치");break;
			default: System.out.println("회원님 즐감...");			
		}
		//5]자원반납
		close();
	}
	public static void main(String[] args) throws Exception {

		new IsMemberProc().execute();
	}

}
