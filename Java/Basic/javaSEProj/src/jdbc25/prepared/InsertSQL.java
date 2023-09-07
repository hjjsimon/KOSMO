package jdbc25.prepared;

import java.sql.SQLException;
import java.sql.Date;

import jdbc25.service.IConnectImpl;

/*
 Statement와 PreparedStatement 차이] 둘다 쿼리전송함
 생성시 차이 conn.createStatement() / conn.preparedStatement(SQL) <-Statement객체 생성시 SQL 먼저 넣어줌, 준비한다고해서 프리페어드, 객체생성시 쿼리파싱(분석) 이미 끝
 값 넣을 때 차이 insert into values('LEE') / insert into values(?) <-이따구로 해도됨, ?에 해당하는걸 나중에 처리는 해줘야함, 문자든 숫자든 ''무관 ?처리하면 쉬움(장점2) 
 쿼리실행시 stmt.execute(SQL) <-실행전 쿼리파싱(분석)/ psmt.execute() <-이때는 안넣어줌, 파싱(분석)없이 바로 실행함(장점1)
 */

public class InsertSQL extends IConnectImpl {

	public InsertSQL(String url,String user,String password) {
		//super();//이거 없어도 생략된거야~
		connect(url,user,password);
	}
	@Override
	public void execute() throws Exception {
		//1]쿼리문 미리 준비
		//1-1]인파라미터(?)가 없는 쿼리문
		//String sql="INSERT INTO MEMBER VALUES('KIM1','1234','김길동1',SYSDATE)";
		//1-2]인파라미터(?)가 있는 쿼리문
		String sql="INSERT INTO MEMBER VALUES(?,?,?,?)";//SYSDATE도 자바에서 값 넣고싶음
		
		try {
			//2]쿼리실행을 위한 Statement계열 객체 생성
			//PreparedStatement객체는 객체 생성시 미리 쿼리문을 준비해서 Connection객체의 prepareStatement(쿼리문)메소드의 인자로 전달해야 한다.
		    //PreparedStatement객체 생성시 전달된 쿼리문을 먼저 parsing하기때문에 실행시에는 바로 실행한다. 그러므로 실행시에는 쿼리문을 전달할 필요가 없다.
			psmt=conn.prepareCall(sql);//psmt 이미 선언해놓음 담기만함
			try {//insert할때 에러나면 아래 캐치로 들어가니 여기서 잡아줌
				//3]쿼리실행-실행시 쿼리문 전달 불필요
				//3-1]인파라미터 없는 쿼리문-인파라미터 설정 불필요, 바로 실행하면 됨
				//3-2]인파라미터가 있는 쿼리문-실행전 반드시 값을 설정
				//쿼리 실행전에 값에 해당하는 부분을 ? 처리한 경우, 값을 설정해줘야 함.
				//?(인파라미터)는 쿼리문의 값에 해당하는 부분에만 넣어줘야한다.
				//-인 파라미터를 설정할때는 PreparedStatement객체의 setXXXX(파라미터인덱스,설정할 값)메소드로 설정한다
				//예] Oracle의 자료형이 NUMBER 라면 setInt(), char/varchar2라면 ->setString() => 자료형에 상관없이 setString()설정가능
				//즉 ResultSet객체로 값을 읽어오는 getXXXX()메소드와 사용법이 동일하다.
				psmt.setString(1, getValue("아이디"));
				psmt.setString(2, getValue("비밀번호"));
				psmt.setString(3, getValue("이름"));//getValue는 String반환임
				//1)날짜를 java.sql.Date타입으로 설정시]
				//Date regidate=new Date(new java.util.Date().getTime());
				//psmt.setDate(4, regidate);
				//2)날짜를 문자열(String)으로 설정시]
				psmt.setString(4, getValue("가입일"));//getValue를 String으로 반환, "2023-4-12"이든 .이든 @이든 어떤식으로 들어가도 입력 잘 됨(웬만한 날짜형식 다 됨, 오라클처럼)
				System.out.println(psmt.executeUpdate()+"행이 입력되었어요");
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
	public static void main(String[] args) {		
		try {
			new InsertSQL(ORACLE_URL, "KOSMO", "kosmo1234").execute();
		}
		catch (Exception e) {
			System.out.println("오류발생:"+e.getMessage());
		}
		
	}////main

}
