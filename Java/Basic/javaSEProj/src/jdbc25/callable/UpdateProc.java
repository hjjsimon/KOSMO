package jdbc25.callable;

import java.sql.Types;

import jdbc25.service.IConnectImpl;
/*
create or replace PROCEDURE SP_UDTMEMBER(
    USERNAME_ IN MEMBER.USERNAME%TYPE,
    PASSWORD_ VARCHAR2,
    NAME_ MEMBER.NAME%TYPE,
    RTVAL OUT NCHAR
)
IS
BEGIN
    --수정시에는 모두 변수로 처리됨,따라서 컬럼명과 변수를 다른 이름으로해라
    UPDATE MEMBER SET PASSWORD=PASSWORD_,NAME=NAME_ WHERE USERNAME=USERNAME_;
    IF SQL%FOUND THEN
        RTVAL:='SUCCESS';
        COMMIT;
    ELSE --존재하지않는 아이디(PK)로 수정시
        RTVAL := 'FAIL:NOT EXIST USERNAME';
    END IF;
    EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RTVAL:='FAIL:TOO MUCH VALUE';
END;
 */

public class UpdateProc extends IConnectImpl {//프로시져는 반환값 없음, 함수 호출이랑 이것만 다름!

	public UpdateProc() {
		connect(ORACLE_URL, "kosmo", "kosmo1234");
	}
	@Override
	public void execute() throws Exception {
		//1]프로시저를 실행하기 위한 CallableStatement객체 얻기
		csmt=conn.prepareCall("{call SP_UDTMEMBER(?,?,?,?)}");
		//2]파라미터 설정
		//2-1]인파라미터(?)설정: 오라클의 인파라미터에 해당하는 ? 설정 setxxxx()로
		csmt.setString(1, getValue("수정할 아이디"));
		csmt.setString(2, getValue("비밀번호"));
		csmt.setString(3, getValue("이름"));
		//2-2]오라클의 아웃파라미터에 해당하는? 설정 registerOutParameter()로
		csmt.registerOutParameter(4, Types.NCHAR);
		//3]프로시저 실행-execute(): 결과값 4번?에 저장 
		System.out.println(csmt.execute());//영향받은 행이 없거나 있을 때 수로 반환하면 false, RS로 반환시 true, 즉 false 나와야 성공임
		//4]아웃파라미터에 저장된 값 읽어오기: CallableStatement객체의 getxxxx()계열 메소드
		System.out.println("프로시저의 아웃 파라미터 값:"+csmt.getString(4));
		//5]자원반납
		close();
	}
	public static void main(String[] args) throws Exception {

		new UpdateProc().execute();
	}

}
