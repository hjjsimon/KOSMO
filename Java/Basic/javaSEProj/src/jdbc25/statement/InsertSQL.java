package jdbc25.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertSQL {

	//멤버변수
	private Connection conn;//지역변수 아니니까 접근지정자o
	private Statement stmt;
	//생성자
	public InsertSQL() {
		
		try {
			//1]JDBC용 오라클 드라이버를 메모리에 로딩
			Class.forName("oracle.jdbc.OracleDriver");//외워라 그냥~
			//2]오라클에 연결시도:DriverManager의 getConnection()메소드로
			//이번엔 ip로!, ip는 컴퓨터주소임, naver.com 도메인은 다 기억함, ip기억못하니까 쓰는거, 도메인 대신 http://ip로도 naver접속됨
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","kosmo","kosmo1234");	//kosmo로 접속한다~
		} 
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스가 없어요. 드라이버 로딩 실패..");
		}
		catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");//getConnection 잡음
		}
	}////InsertSQL()
	//닫는 메소드 생성
	private void close() {
		try {
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		}
		catch (SQLException e) {}
	}////close() 
	//Exec 메소드 생성
	private void execute() {
		
		try {
			//3]쿼리 실행하기 위한 Statement객체 생성. 연결된 Connection객체로...
			stmt=conn.createStatement();
			//4]Statement계열 객체로 쿼리 실행
			//쿼리문이 DELETE,UPDATE,INSERT일때는 int executeUpdate()-> 영향받은 행의 수 int반환
			//쿼리문이 SELECT일때는 ResultSet executeQuery()호출
			try {
				//int affected=stmt.executeUpdate("INSERT INTO MEMBER VALUES('PARK','1234','박길동',SYSDATE)");//영향받은 행의 수
				int affected=stmt.executeUpdate("INSERT INTO MEMBER VALUES('KOSMO','1234','한소인',SYSDATE)");//오토커밋도 된다~, 근데 계속 입력 귀찮 
				System.out.println(affected+"행이 입력되었어요");
			}
			catch (SQLException e) {
				System.out.println("INSERT 쿼리문 실행오류:"+e.getMessage());
			}
		} 
		catch (SQLException e) {
			System.out.println("Statement객체 생성실패");
		}
		finally {
			close();
		}
	}////execute()
	
	public static void main(String[] args) {

		new InsertSQL().execute();//객체생성하고 execute호출, 생성자 호출시 위의 오라클DB연결됨
		
	}////main
	
}////class
