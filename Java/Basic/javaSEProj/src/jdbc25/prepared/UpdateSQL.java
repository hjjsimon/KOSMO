package jdbc25.prepared;

import java.sql.SQLException;

import jdbc25.service.IConnectImpl;

public class UpdateSQL extends IConnectImpl {

	@Override
	public void execute() throws Exception {
		//0]DB연결
		connect(ORACLE_URL, "kosmo", "kosmo1234");
		try {
			//1]명령실행하기 위해 PreparedStatement 객체 생성
			psmt=conn.prepareStatement("UPDATE MEMBER SET NAME=?,PASSWORD=? WHERE USERNAME=?");//USERNAME이 ?인 것들의 NAME, PASSWORD 수정할 것
			//2]인파라미터 설정
			psmt.setString(3, getValue("수정할 아이디"));//이거부터 입력받으면 좋음
			psmt.setString(1, getValue("이름"));
			psmt.setString(2, getValue("비밀번호"));
			//3]쿼리 실행
			try {
				System.out.println(psmt.executeUpdate()+"행이 수정되었습니다");
			}
			catch(SQLException e) {
				System.out.println("수정시 오류:"+e.getMessage());
			}
		}
		finally {
			//4]자원반납
			close();
		}
	}
	
	public static void main(String[] args) throws Exception {

		new UpdateSQL().execute();
		
	}////main

}////class
