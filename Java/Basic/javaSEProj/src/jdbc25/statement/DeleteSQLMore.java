package jdbc25.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteSQLMore {
	//멤버변수
	private Connection conn;
	private Statement stmt;
	//생성자
	public DeleteSQLMore() {
		
		try {
			//1]JDBC용 오라클 드라이버를 메모리에 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//2]오라클에 연결시도:DriverManager의 getConnection()메소드로
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kosmo","kosmo1234");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("드라이버가 없어요");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결실패");
		}
	}
	private void close() {
		try {
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} 
		catch (Exception e) {}
	}
	private void execute() {
		try {
			//3]쿼리 실행하기 위한 Statement객체 생성. 연결된 Connection객체(conn)로..
			stmt=conn.createStatement();
			//4]Statement계열 객체로 쿼리 실행
			//쿼리문이 DELETE,UPDATE,INSERT일 때는 int executeUpdate()
			//쿼리문이 SELECT일때는 ResultSet executeQuery()호출
			while(true) {
				try{
					//int affected=stmt.executeUpdate("DELETE MEMBER WHERE UPPER(USERNAME)='NULL'");//대문자로 바꾼 후 대문자 NULL과 비교, 영향받은 행의 수 반환
					int affected=stmt.executeUpdate("DELETE MEMBER WHERE UPPER(USERNAME)='"+InsertSQLMore.getValue("삭제할 아이디").toString()+"'");
					System.out.println(affected+"행이 삭제되었어요");
				}
				catch(SQLException e) {
					System.out.println("삭제시 오류:"+e.getMessage());
				}
				catch(NullPointerException e) {//삭제할 아이디 null로 잡으려면 이거 에러 잡아야함, 안그러면 에러남
					System.out.println("수고하셨습니다");
					break;
				}
			}//while->break시 여기 아래로 나옴	
		}
		catch (SQLException e) {
			System.out.println("Statement객체 생성실패");
		}
		finally {
			close();//삭제시오류 나오면 finally 여기로 옴
		}
	}
	
	public static void main(String[] args) {

		new DeleteSQLMore().execute();	
		
	}////////////////main

}///////////////////class
