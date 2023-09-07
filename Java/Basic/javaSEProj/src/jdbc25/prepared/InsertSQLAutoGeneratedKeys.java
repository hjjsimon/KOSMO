package jdbc25.prepared;

import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;

import jdbc25.service.IConnectImpl;

/*
 Statement와 PreparedStatement 차이] 둘다 쿼리전송함
 생성시 차이 conn.createStatement() / conn.preparedStatement(SQL) <-Statement객체 생성시 SQL 먼저 넣어줌, 준비한다고해서 프리페어드, 객체생성시 쿼리파싱(분석) 이미 끝
 값 넣을 때 차이 insert into values('LEE') / insert into values(?) <-이따구로 해도됨, ?에 해당하는걸 나중에 처리는 해줘야함, 문자든 숫자든 ''무관 ?처리하면 쉬움(장점2) 
 쿼리실행시 stmt.execute(SQL) <-실행전 쿼리파싱(분석)/ psmt.execute() <-이때는 안넣어줌, 파싱(분석)없이 바로 실행함(장점1)
 */

public class InsertSQLAutoGeneratedKeys extends IConnectImpl {

	public InsertSQLAutoGeneratedKeys(String url,String user,String password) {
		connect(url,user,password);
	}
	@Override
	public void execute() throws Exception {
		//1]쿼리문 미리 준비
		String sql="INSERT INTO BBS VALUES(SEQ_BBS.NEXTVAL,?,?,SYSDATE)";//SYSDATE도 자바에서 값 넣고싶음	
		try {
			//2]쿼리실행을 위한 Statement계열 객체 생성
			psmt=conn.prepareStatement(sql,new int[] {1});//pk는 인덱스1이지
			try {
				//3]쿼리실행-실행시 쿼리문 전달 불필요
				psmt.setString(1, getValue("제목"));//seq옆의 물음표가 처음으로 나오는 물음표라 1번임
				psmt.setString(2, getValue("아이디"));
				System.out.println(psmt.executeUpdate()+"행이 입력되었어요");
				//입력된 행의 키(PK)값 가져오기
				ResultSet rs=psmt.getGeneratedKeys();
				rs.next();
				System.out.println("입력된 행의 키값:"+rs.getInt(1));//getInt로 가져오는건 자동으로 생기게한 시퀀스값 같은것, 제목같은건 가져오지 못함, 다섯번째 입력되는거라 키값 5반환
			}
			catch (SQLException e){
				System.out.println("입력시 오류:"+e.getMessage());
			}			
		}
		catch (SQLException e) {
			System.out.println("PreparedStatement객체 생성실패:"+e.getMessage());
		}
		finally {
			//4]자원반납
			close();
		}
	}
	public static void main(String[] args) throws Exception {		
			new InsertSQLAutoGeneratedKeys(ORACLE_URL, "KOSMO", "kosmo1234").execute();
		
	}////main

}