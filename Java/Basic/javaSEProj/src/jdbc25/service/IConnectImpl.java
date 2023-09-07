package jdbc25.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class IConnectImpl implements IConnect{//앞의 IConnect를 implements한 클래스~ 이름 짓는법

	//멤버변수
	public Connection conn;
	public ResultSet rs;//Select결과 담음, 지금 뭘 할지 모름, 다 만들어놔야함
	public Statement stmt;
	public PreparedStatement psmt;
	public CallableStatement csmt;
	private Scanner sc=new Scanner(System.in);
	//static블락
	static {		
		try {
			//드라이버 로딩]
			Class.forName(OACLE_DRIVER);
		} 
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e.getMessage());
		}
	}
	//기본생성자]
	public IConnectImpl() {}
	//인자생성자]
	public IConnectImpl(String url, String user, String password) {
		connect(url,user,password);//connect 호출시 db연결
	}
	//DB 연결하는 메소드
	@Override
	public void connect(String url, String user, String password) {
		try {
			conn=DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e) {
			System.out.println("데이터베이스 연결실패:"+e.getMessage());
		}
	}
	//쿼리실행 메소드(상황마다 달라지므로 코딩구현안함)
	@Override
	public void execute() throws Exception {
	}
	//DB연결 끊는 메소드
	@Override
	public void close() {
		try {//뭘 쓸지 모르므로 다 구현해놔야함, null이 아닌 경우에만 닫음, null이면 안닫음
			if(csmt!=null) csmt.close();
			if(psmt!=null) psmt.close();
			if(stmt!=null) stmt.close();
			if(rs!=null) rs.close();
			if(conn!=null) conn.close();
		}
		catch (SQLException e) {}
	}
	//사용자 입력용 메소드
	@Override
	public String getValue(String message) {
		System.out.println(message+"를(을) 입력하세요?");
		String value=sc.nextLine().trim();
		if("EXIT".equalsIgnoreCase(value)) {
			close();//DB연결끊기(자원반납)
			System.out.println("프로그램 종료합니다!!!");
			System.exit(0);//종료전에 꼭 연결 끊어야한다~
		}
		return value;
	}
	@Override
	public String getQueryString() {
		System.out.print("SQL>");
		return sc.nextLine();
	}

}
