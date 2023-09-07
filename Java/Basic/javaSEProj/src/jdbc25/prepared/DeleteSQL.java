package jdbc25.prepared;

import java.sql.SQLException;

import jdbc25.service.IConnectImpl;

public class DeleteSQL extends IConnectImpl{

	@Override
	public void execute() throws Exception {
		//0]DB연결
		connect(ORACLE_URL, "kosmo", "kosmo1234");
		try {
			//1]PreparedStatement객체 생성
			psmt=conn.prepareStatement("DELETE MEMBER WHERE USERNAME=?");//물음표가 하나
			//2]인파라미터 설정
			psmt.setString(1, getValue("삭제할 아이디"));//물음표 1개라 인덱스1
			try {
				//3]쿼리 실행
				System.out.println(psmt.executeUpdate()+"행이 삭제되었습니다");
			}
			catch(SQLException e) {
				System.out.println("삭제시 오류:"+e.getMessage());//KIM은 자식이 참조중이라 삭제불가
			}
		}
		finally {//던지니까 캐치할 필요는 없음
			//4]자원반납
			close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		new DeleteSQL().execute();
	}

}
