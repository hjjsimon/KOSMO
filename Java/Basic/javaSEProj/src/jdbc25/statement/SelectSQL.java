package jdbc25.statement;

import java.sql.SQLException;

import jdbc25.service.IConnectImpl;

public class SelectSQL extends IConnectImpl {

	@Override
	public void execute() throws Exception {
		//1]데이터베이스 연결
		connect(ORACLE_URL, "scott", "scott");
		try {
			//System.out.println(conn);//위에 connect연결해줘야 conn에 주소 들어감
			//2]Statement 객체 생성
			stmt=conn.createStatement();
			try {//쿼리실행시 sqlexception 생기는거 잡아줌
				//3]쿼리문 작성
				//3-1]여러개 레코드를 반환하는 SELECT문
				//String sql="SELECT * FROM EMP ORDER BY HIREDATE DESC";
				//3-2]한개나 0개 레코드를 반환하는 경우(주로 PK로 검색하는 경우)
				//String sql="SELECT * FROM EMP WHERE EMPNO="+getValue("사원번호");//없는 사원번호 입력시 꺼낸 레코드 없어서 while 안으로 안들어감, while써도 되는데 선생님은 안쓴대
				//3-3]무조건 레코드 1개 반환하는 경우(그룹함수 사용하는 경우)
//				String sql="SELECT AVG(SAL) FROM EMP";
				//3-4]LIKE 연산자를 포함한 SELECT문
				/*
				 * 자바코드에서 LIKE 연산자 사용법
				 * ※[PreparedStatement객체 사용시]
				 * -오라클: 자바방식도 되지만 아무도 안씀
				 *  1) LIKE '%A%' :A로 시작하거나 A로끝나거나 A가 중간에 포함되거나
				 *  2) LIKE '%A' :A로 끝나는 경우
				 *  3) LIKE 'A%' :A로 시작
				 * -자바: 반드시 이렇게 써야한다! 뭐 지금하는 Statement는 된대, 근데 이건 불편해서 다 PreparedStatement 쓴대(이건 문자열 '' 안감싸도 돼서 편하대 JDBC는 무조건이래)
				 *  1) LIKE  '%' || 'A' || '%'
				 *  2) LIKE '%' || 'A'
				 *  3) LIKE 'A' || '%'
				 *
				 */
				//String sql="SELECT * FROM EMP WHERE UPPER(ENAME) LIKE '%S%'"; //이것도 된다!
				//String sql="SELECT * FROM EMP WHERE UPPER(ENAME) LIKE '%'||'S'||'%'";
				//String sql="SELECT * FROM EMP WHERE UPPER(ENAME) LIKE '%"+getValue("찾을 문자열").toUpperCase()+"%'"; //중간에 값 받을때 이렇게함, upper로 하면 소문자도됨
				String sql="SELECT * FROM EMP WHERE UPPER(ENAME) LIKE '%'||'"+getValue("찾을 문자열").toUpperCase()+"'||'%'";
				//4]쿼리실행
				rs=stmt.executeQuery(sql);//rs는 부모에서 선언해놨음
				/*
				SELECT문 실행시 실행결과는 ResultSet타입의 객체에 저장됨. 최초 커서는 첫번째 레코드 바로 위에 가 있다.
				ResultSet객체의 next()메소드로 커서를 아래로 이동시키면서 더이상 꺼내올 레코드가 없을때까지 반복하면서 추출.			
				ResultSet객체의 getXXX()계열 메소드로 해당 레코드의 각 컬럼에 저장된 값을 읽어 온다.
				예를들면
			  	----------------------+------------------------------
			     ORACLE자료형          |       ResultSet
			     ---------------------+----------------------------
			     NUMBER               |     getInt(인덱스 혹은 컬럼명)
			     ---------------------+-------------------------------
			     CHAR/NCHAR           |
			     VARCHAR2/NVARCHAR2   |     getString(인덱스 혹은 컬러명)
			     ---------------------+---------------------------------
			     DATE                 |     getDate(인덱스 혹은 컬러명)
			     ---------------------+---------------------------------
			 	 ※인덱스는 SQL에서는 1부터 시작(자바가 0!)
			     ※단,ORACLE의 자료형에 상관없이 getString(인덱스 혹은 컬러명)으로 읽어와도 무방하다.			    
			     ※오라클에 저장된 데이타가 없는 경우 즉 null 인 경우	rs.getInt(인덱스번호 혹은 컬럼명) 는 0 반환, rs.getString(인덱스번호 혹은 컬럼명)는 null 반환
				   0을 가져오면 null인지 0인지 모르므로 getString으로 가져오는 것이 좋다
				 */
				//3-1]여러개 레코드를 반환하는 경우(3-4]도 사용)
				while(rs.next()) {//next()로 커서 이동시키며 꺼내옴, 끝에 가면 false, 꺼내올 레코드 없을때까지 반복
					int empno=rs.getInt(1);//인덱스로 꺼내옴
					String ename=rs.getString("ENAME");
					String job=rs.getString("JOB");
					//int comm=rs.getInt("comm");//컬럼명 대소문자 무관,getInt는 null을 0으로 가져와 문제임		
					String comm=rs.getString("comm")==null?"":rs.getString("comm");//가져온게 null이면 ""빈문자열, 아니면 정상출력
					//오라클의 Date타입을 getDate()로 가져오는 경우]-년월일만 반환					
					//java.sql.Date hiredate=rs.getDate(5);//날짜 출력, 이것도 getString으로 가능, 근데 날짜는 getDate가 나음
					//오라클의 Date타입을 getString()으로 가져오는 경우]-시분초까지 반환
					String hiredate=rs.getString(5).substring(0,10);//뒤에 어차피 00:00:00이니까 잘라주자, 자바는 인덱스 0부터 시작, 10넣으면 10-1까지 가져옴
					System.out.println(String.format("%-5s%-10s%-10s%-7s%s",empno,ename,job,comm,hiredate));
				}
				//3-2]한개나 0개 레코드를 반환하는 경우(주로 PK로 검색하는 경우)----------왜 안되지
				//if(rs.next()) {//꺼낼게 없으면 false라 안들어옴
				//	System.out.println(String.format("%-5s%-10s%-10s%-7s%s"
				//			,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(7)==null?"":rs.getString(7),rs.getDate(5)));
				//}
				//3-3]무조건 레코드 하나 반환하는 경우
				//rs.next();
				//System.out.println("평균 연봉:"+rs.getDouble(1));//ex.AVG(SAL)이면 컬럼 1개,레코드1개니까~
			}
			catch (SQLException e) {
				System.out.println("SELECT쿼리 실행오류:"+e.getMessage());
			}
		}	
		finally {//try,finally만씀
			close();
		}
		
	}

	public static void main(String[] args) throws Exception {
		
		new SelectSQL().execute();//기본생성자에서 아무일도 안함, connect호출 안하므로 conn에 담기는게 없어 null임, 위에서 execute()오버라이딩함
		
	}///////main

}//////class
