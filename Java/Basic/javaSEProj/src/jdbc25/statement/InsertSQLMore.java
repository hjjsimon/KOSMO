package jdbc25.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertSQLMore {

	//멤버변수
	private Connection conn;//지역변수 아니니까 접근지정자o
	private Statement stmt;
	//생성자
	public InsertSQLMore() {
		
		try {
			//1]JDBC용 오라클 드라이버를 메모리에 로딩
			Class.forName("oracle.jdbc.OracleDriver");//외워라 그냥~
			//2]오라클에 연결시도:DriverManager의 getConnection()메소드로
			//이번엔 ip로!, ip는 컴퓨터주소임, naver.com 도메인은 다 기억함, ip기억못하니까 쓰는거, 도메인 대신 http://ip로도 naver접속됨
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","kosmo","kosmo1234");//kosmo로 접속한다~
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
			while(true) {
				try {
					//int affected=stmt.executeUpdate("INSERT INTO MEMBER VALUES('PARK','1234','박길동',SYSDATE)");//영향받은 행의 수
					int affected=stmt.executeUpdate(
							//////////////////////////////////소스한번확인
							"INSERT INTO MEMBER VALUES('"+getValue("아이디").toString()+"','"+getValue("비밀번호").toString()+"','"+getValue("이름").toString()+"',SYSDATE)"
							);//오토커밋도 된다~, 근데 계속 입력 귀찮, 반복시킴, exit넣으면 null이 입력됨 
					System.out.println(affected+"행이 입력되었어요");
				}
				catch (SQLException e) {
					System.out.println("INSERT 쿼리문 실행오류:"+e.getMessage());
				}
				catch (NullPointerException e) {//null들어가면, null로 .할 수 없어서 이거 에러나옴, 여튼 이거 해야 exit된다
					System.out.println("수고하셨습니다");
					break;
				}
			}////while //while빠져나오면 close하고 끝남
			
		}	
		catch (SQLException e) {
			System.out.println("Statement객체 생성실패");
		}
		finally {
			close();
		}
	}////execute()
	//사용자 입력용 메소드]
	private static Scanner sc=new Scanner(System.in);
	public static String getValue(String message) {//message에 제목 들어오면 제목을 입력하세요~
		System.out.println(message+"를(을) 입력하세요?");
		String value=sc.nextLine();
		if("EXIT".equalsIgnoreCase(value)) {//exit입력시 종결
			return null;//String반환 메소드라 null반환하게함
		}
		return value;
	}////getValue(String message)
	
	public static void main(String[] args) {

		new InsertSQLMore().execute();//객체생성하고 execute호출, 생성자 호출시 위의 오라클DB연결됨
		
	}////main
	

}////class
