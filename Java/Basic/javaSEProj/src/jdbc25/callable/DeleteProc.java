package jdbc25.callable;

import java.sql.Types;

import jdbc25.service.IConnectImpl;
/*
create or replace PROCEDURE SP_DELMEMBER(
    USERNAME_ IN MEMBER.USERNAME%TYPE,--지울때 아이디만 받으면 됨
    APPECTED OUT NUMBER
)
IS
BEGIN   
    DELETE MEMBER WHERE USERNAME=USERNAME_;
    IF SQL%FOUND THEN
        DBMS_OUTPUT.PUT_LINE(USERNAME_||'가 삭제되었어요');
        APPECTED :=SQL%ROWCOUNT;
        COMMIT;
    ELSE --존재하지 않는 아이디(PK)로 삭제시
        DBMS_OUTPUT.PUT_LINE(USERNAME_||'가 존재하지 않아요');
        APPECTED :=0;
    END IF;
    EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('자식이 참조하고 있어요');--자식이 참조하는경우 에러
        APPECTED :=-1;
END;
 */

public class DeleteProc extends IConnectImpl {//프로시져는 반환값 없음, 함수 호출이랑 이것만 다름!

	public DeleteProc() {
		connect(ORACLE_URL, "kosmo", "kosmo1234");
	}
	@Override
	public void execute() throws Exception {
		//1]프로시저를 실행하기 위한 CallableStatement객체 얻기
		csmt=conn.prepareCall("{call SP_DELMEMBER(?,?)}");
		//2]파라미터 설정
		//2-1]인파라미터(?)설정: 오라클의 인파라미터에 해당하는 ? 설정 setxxxx()로
		String username=getValue("삭제할 아이디");
		csmt.setString(1, username);
		//2-2]오라클의 아웃파라미터에 해당하는? 설정 registerOutParameter()로
		csmt.registerOutParameter(2, Types.NUMERIC);//number 특이하게 numeric으로 담음
		//3]프로시저 실행-execute(): 결과값 4번?에 저장 
		System.out.println(csmt.execute());
		//4]아웃파라미터에 저장된 값 읽어오기: CallableStatement객체의 getxxxx()계열 메소드
		int result=csmt.getInt(2);
		switch(result) {
			case -1:System.out.println("삭제불가:자식이 참조해...");break;
			case 0:System.out.println("삭제불가:아이디가 존재하지않아");break;
			default: System.out.println(username+"가(이) 삭제되었어...");			
		}
		//5]자원반납
		close();
	}
	
	public static void main(String[] args) throws Exception {

		new DeleteProc().execute();
	}

}
