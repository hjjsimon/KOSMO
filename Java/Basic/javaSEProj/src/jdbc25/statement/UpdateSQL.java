package jdbc25.statement;

import java.sql.SQLException;

import jdbc25.service.IConnectImpl;

public class UpdateSQL extends IConnectImpl{

	public UpdateSQL(String url,String user,String password) {
		super(url, user, password);//super찍고 가보면 connect로 연결함
	}
	
	@Override
	public void execute() throws Exception {
		//1]쿼리전송용 Statement객체 생성
		stmt=conn.createStatement();
		//1-1]쿼리문 작성
		String sql="UPDATE MEMBER SET NAME='리길동',PASSWORD='1234' WHERE UPPER(USERNAME)='LEE'";
		//2]쿼리 실행
		try {
			System.out.println(stmt.executeUpdate(sql)+"행이 수정되었습니다");//에러나면 빨간줄감, 이런건 trycatch로 잡아줘야함
		}
		catch(SQLException e) {
			System.out.println("수정시 오류:"+e.getMessage());
		}
		finally {
			//3]자원 반납
			close();
		}
	}

	public static void main(String[] args) throws Exception {
		//이번엔 기본생성자 말고 인자생성자로 해보자~
		new UpdateSQL(ORACLE_URL,"kosmo","kosmo1234").execute();
	
	}////main

}////class
