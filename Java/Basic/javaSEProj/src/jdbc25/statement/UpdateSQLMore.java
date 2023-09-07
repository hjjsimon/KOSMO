package jdbc25.statement;

import java.sql.SQLException;

import jdbc25.service.IConnectImpl;

public class UpdateSQLMore extends IConnectImpl{

	public UpdateSQLMore(String url,String user,String password) {
		super(url, user, password);//super찍고 가보면 connect로 연결함
	}
	
	@Override
	public void execute() throws Exception {
		//1]쿼리전송용 Statement객체 생성
		stmt=conn.createStatement();
		while(true) {
			//2]쿼리 실행
			try {
				//1-1]쿼리문 작성
				String username=getValue("수정할 아이디");
				String sql="UPDATE MEMBER SET NAME='"+getValue("이름")+"',PASSWORD='"+getValue("비밀번호")+"' WHERE UPPER(USERNAME)='"+username+"'";
				System.out.println(stmt.executeUpdate(sql)+"행이 수정되었습니다");//에러나면 빨간줄감, 이런건 trycatch로 잡아줘야함
			}
			catch(SQLException e) {
				System.out.println("수정시 오류:"+e.getMessage());
			}
		}//while
	}

	public static void main(String[] args) throws Exception {
		//이번엔 기본생성자 말고 인자생성자로 해보자~
		new UpdateSQLMore(ORACLE_URL,"kosmo","kosmo1234").execute();
	
	}////main

}////class
