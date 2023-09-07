package jdbc25;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
오라클 등 3개 관계형데이터베이스이므로 테이블(표)형태로 데이터 저장
CRUD->CREATE(INSERT), READ(SELECT), UPDATE, DELETE: 데이터를 다루는 것을 의미
오라클의 데이터를 CRUD하려고 JAVA언어로 프로그램을 짰다고 하자, 회사가 어려워져서 오라클 못쓰게됨, MS-SQL쓰기로함, 그러면 MS-SQL용으로 또 프로그램 짜야함, 더 어려워지면 MY-SQL용 또 만들어야함
근데 3개랑 프로그램을 연결하는 중간에 API(APPLICATION=프로그램 PROGRAMMING=만드는 INTERFACE=자판기에 커피 뽑아먹는 출구, 동전넣는 입구, 버튼들 있음. 이런게 자판기의 인터페이스임)인 JDBC가 있음
(자바기준으로 클래스와 인터페이스랑의 모음이라고 치면됨, 메소드 등을 가져다쓰는게 API래)
JDBC(JAVA언어로 DATA BASE에 CONNECTIVITY 접속해서 CRUD작업을 할 수 있게 도와주는 API가 JDBC)-> 여튼 프로그램 1개만 짜도 된다~ 그러면 오라클,MS-SQL,MY-SQL 모두 가능!
추상메소드로 JDBC안에 CONNECT() 메소드를 만들어놓음, 착하게 오라클,MS,MY에서 갖다쓰도록 3개 회사가 알아서 지들꺼에 구현해놓음, JAVA에서는 CONNECT()만 가져다쓰면됨(이전에 도형무관 AREA 호출하듯)
JDBC가 각 벤더(3개회사)에 맞는 API를 사용할 수 있도록 각 벤더의 드라이버 다운받아 JAVA에 설정해야한다
우리는 11G 쓰고있으므로 OJDBC6.JAR 필요함 -> LIB에서 .JAR 찾아서 오라클에 빌드패스 해야함
오라클 드라이버 jdbc:oracle:thin <-이건 외워야함, 이 드라이버종류는 순수 java언어로 만들어져서 thin드라이버라고함
->5개 패턴 기억하면 JDBC 프로그래밍 끝!***
1. class.forName() 으로 드라이버를 메모리로 로딩
2. dirvermanager.getConnection() 연결->connection반환
3. con.으로 statement객체 생성
4. statemenet객체로 execute로 쿼리전송
5. 작업끝나면 DB연결 끊는 close
*/
public class DBConnection {

	public static void main(String[] args) {
		
		Connection conn=null;
		Statement stmt=null;//sql에 있는거	
		ResultSet rs=null;
		
		try {
			//1]오라클용 jdbc드라이버 메모리에 로딩
			//Class.forname("패키지를 포함한 클래스명"):클래스명으로 해당 클래스를 찾아서 메모리에 로딩(작동)하는 정적메소드(new)하지않고!
			//메모리에 로딩된 드라이버가 DriverManager라는 클래스에 등록됨
			//자바에 class라는 class가 있음, 파일명이지 클래스명 아니니까 .class빼줌 ojdbc6.jar->oracle.jdbc->oracledriver.class를 주소카피
			//Class.forName("oracle.jdbc.OracleDriver");
			//2]DriverManager클래스의 getConnection()메소드로 오라클에 연결시도(getter니까 Connection get해서 Connection반환)
			//연결되면 주소반환, 실패하면 null반환
			//오라클 주소설정-> 드라이버종류:@오라클이 설치된 주소:포트:전역데이터베이스명(SID) 
			//예]jdbc:oracle:thin-드라이버 종류, localhost 혹은 127.0.0.1(ip로따진것)- 오라클이 설치된 주소(내 PC에 설치된 경우)
			//오라클이 설치된 주소]
			//String url="jdbc:oracle:thin:@localhost:1521:xe";//이거 외워야함
			//접속계정정보] scott으로 접속할거임
			//String user="scott";
			//String password="scott";
			Properties props=new Properties();//properties에서 읽어오기
			props.load(new FileReader("src/jdbc25/config.properties"));//Reader로 가져옴
			Class.forName(props.getProperty("driver"));//file에서 만든키값 넣어주면 됨
			String url=props.getProperty("url");
			String user=props.getProperty("user");
			String password=props.getProperty("password");
			
			conn=DriverManager.getConnection(url, user, password);//DB연결메소드, Connection반환함			
			System.out.println("연결 성공:"+conn);
			try {
				//3]쿼리문(SQL)전송을 위한 Statement계열의 객체생성
				// 연결된 Connection객체(conn)을 통해서 생성
				stmt=conn.createStatement();//Statement객체반환, 연결된 쿼리 전송->이것도 sqlexception던지면 캐치로 잡힘, getconnection메소드도 이거 던짐, 여튼 이건 별도로 묶자
				try {
					//4]Statement계열 클래스(stmt)의 execute계열 메소드를 이용해서 쿼리문 실행(오라클에 명령전송)
					String sql="SELECT * FROM EMP ORDER BY HIREDATE DESC";//단, 이때 세미콜론 넣으면 안됨
					rs=stmt.executeQuery(sql);//쿼리문 넣어주면 됨->sql이 오라클로 전송됨, ResultSet반환함, select하면 표형태 반환하니까!
					//ResultSet에 담긴 select쿼리 결과를, 커서를 아래로(forward)이동하면서 각 컬럼에 있는 값 꺼내오자
					//인덱스(더 빠름)나 컬럼명 뭐든 꺼내올때 무관, 컬럼도 인덱스가 있음, sql은 무조건 첫번째가 1번, empno가 1번임
					while(rs.next()){//next():커서를 다음으로 옮겨서 next, 꺼낼 레코드 없을 때되면 false 반환, 그러면 안으로 안들어옴
						int empno=rs.getInt(1);//1번인덱스 컬럼->empno NUMBER(4)라 int로 받음
						String ename=rs.getString(2);
						String job=rs.getString("JOB");//대소문자 무관
						int sal=rs.getInt(6);
						Date hiredate=rs.getDate(5);//당연히 Date는 java.sql꺼
						System.out.println(String.format("%-5s%-11s%-10s%-6s%s",empno,ename,job,sal,hiredate));//사번(4)인데 넉넉하게 5칸,걍 선생님이 맞춘거
					}
				}	
				catch(SQLException e) {//지금 이건 executeQuery 잡음
					System.out.println("SELECT 쿼리 실행 오류:"+e.getMessage());
				}
			}
			catch(SQLException e){//지금 이건 createStatement 잡는거임
				System.out.println("Statement객체 생성 오류:"+e.getMessage());
			}
		}
		catch (IOException e) {//props.load(new FileReader("src/jdbc25/config.properties")); -> 에서 에러 잡음
			System.out.println("데이터베이스 접속 설정파일 로드 실패:"+e.getMessage());
		}
		catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패:"+e.getMessage());
		}
		catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 클래스가 없어요");//나는 있음
		}
		finally {
			try {
				//5]데이터베이스 연결끊기(statement도 반환해줘야함)->생성한 역순으로 끊는게 좋음
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} 
			catch (SQLException e) {}
		}
		
		
		
		
	}

}









