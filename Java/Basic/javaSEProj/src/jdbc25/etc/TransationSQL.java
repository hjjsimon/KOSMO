package jdbc25.etc;

import java.sql.SQLException;

import jdbc25.service.IConnectImpl;

public class TransationSQL extends IConnectImpl {
	
	public TransationSQL() {
		connect(ORACLE_URL, "kosmo", "kosmo1234");
	}
	@Override
	public void execute() throws Exception {
		
		try {
			//1]오토커밋이 일어나지 않도록 설정(DDL,DCL문은 오토커밋이 된다!/DML만 오토커밋안됨)
			conn.setAutoCommit(false);
			//첫번째 쿼리문 작성
			String sql="DELETE MEMBER WHERE USERNAME=?";
			//2]PreparedStatement객체 생성
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, getValue("삭제할 아이디"));
			System.out.println(psmt.executeUpdate()+"행이 삭제되었습니다");
			//두번째 쿼리문 작성
			sql="INSERT INTO MEMBER VALUES(?,?,?,SYSDATE)";
			psmt=conn.prepareStatement(sql);//쿼리문 바꼈으니 새로 새팅
			psmt.setString(1, getValue("아이디"));
			psmt.setString(2, getValue("비밀번호"));
			psmt.setString(3, getValue("이름"));
			System.out.println(psmt.executeUpdate()+"행이 입력되었어요");
			//try절에서 커밋하기
			conn.commit();
			System.out.println("커밋되었습니다-실제 테이블에 반영되었어요");
		}
		catch(SQLException e){//삭제와 입력 작업 2개를 실시하는게 목적, 삭제성공 후 입력실패시 삭제를 롤백함 
			//에러발생시 catch절에서 롤백
			conn.rollback();
			System.out.println("롤백되었습니다-모든 작업이 취소되었어요");
		}
		finally {
			close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new TransationSQL().execute();
	}

}
