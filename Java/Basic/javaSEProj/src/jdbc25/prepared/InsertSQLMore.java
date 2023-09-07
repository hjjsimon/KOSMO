package jdbc25.prepared;

import java.sql.SQLException;
import java.sql.Date;

import jdbc25.service.IConnectImpl;

public class InsertSQLMore extends IConnectImpl {

	public InsertSQLMore(String url,String user,String password) {
		connect(url,user,password);
	}
	@Override
	public void execute() throws Exception {
		//1]쿼리문 미리 준비
		String sql="INSERT INTO MEMBER VALUES(?,?,?,SYSDATE)";//SYSDATE도 자바에서 값 넣고싶음
		try {
			//2]쿼리실행을 위한 Statement계열 객체 생성
			psmt=conn.prepareCall(sql);
			while(true) {
				try {
				//3]쿼리실행-실행시 쿼리문 전달 불필요				
				psmt.setString(1, getValue("아이디"));
				psmt.setString(2, getValue("비밀번호"));
				psmt.setString(3, getValue("이름"));
				System.out.println(psmt.executeUpdate()+"행이 입력되었어요");
				}
				catch (SQLException e){
				System.out.println("입력시 오류:"+e.getMessage());
				}			
			}
		}
		catch (SQLException e) {
		System.out.println("PreparedStatement객체 생성실패:"+e.getMessage());
		}
	}
	public static void main(String[] args) throws Exception {		
			new InsertSQLMore(ORACLE_URL, "KOSMO", "kosmo1234").execute();
	}////main

}
