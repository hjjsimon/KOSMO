package jdbc25.etc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Vector;

import jdbc25.service.IConnectImpl;

public class ExecuteSQL extends IConnectImpl {
	
	public ExecuteSQL() {
		connect(ORACLE_URL, "kosmo", "kosmo1234");
	}
	@Override
	public void execute() throws Exception {
		while(true) {
			//1]쿼리문 준비
			String sql=getQueryString();//사용자로부터 쿼리문 받음
			if("EXIT".equalsIgnoreCase(sql)) {
				System.out.println("Disconnected from Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production");
				close();
				System.exit(0);
			}
			//2]Statement계열 객체 생성-쿼리 실행용
			psmt=conn.prepareStatement(sql);
			//3]쿼리 실행-boolean execute():쿼리문이 미정이므로 execute()는 쿼리문이 SELECT이면 true,그외 SQL문일때는 false반환.
			//1)execute()메소드로 쿼리 실행후 쿼리문이 SELECT일때, ResultSet에 담긴 결과를 가져오려면 Statement객체의 getResultSet()메소드 호출(executeQuery하면 바로 나오는데 귀찮)
			//2)쿼리문이 INSERT/DELETE/UPDATE일때 영향받은 행의 수를 반환 받을때는 getUpdateCount()메소드 호출
			//1,2)방법은 두단계라 귀찮지만, 사용자가 뭘 입력할지 모르므로 execute()써야함
			try {
				boolean flag=psmt.execute();
				if(flag) {//쿼리문이 SELECT면 t반환
					rs=psmt.getResultSet();
					ResultSetMetaData rsmd=rs.getMetaData();//컬럼정보
					int columnCount=rsmd.getColumnCount();//컬럼수만큼반복
					List<Integer> dashCount=new Vector<>(); 
					for(int i=1;i<=columnCount;i++) {
						int columnType=rsmd.getColumnType(i);
						int columnSize=rsmd.getPrecision(i);
						switch(columnType) {
							case Types.NCHAR:
							case Types.NVARCHAR: dashCount.add(columnSize*2); break;
							case Types.NUMERIC:
							case Types.TIMESTAMP: dashCount.add(10); break;
							default: dashCount.add(columnSize);
						}//switch
						//컬럼명 출력]: 컬럼명의 길이가 대쉬의 숫자(자료형의 크기)보다 크다면 (ex. GENDER CHAR(1)면 G하고 - 하나만 출력)
						String columnName=rsmd.getColumnName(i).length()> dashCount.get(i-1)?//컬럼명이 대시카운트보다 크면,컬럼명 자를거야,dashCount는 자바의 List컬렉션이라 0 시작임
										  rsmd.getColumnName(i).substring(0,dashCount.get(i-1)):
									      rsmd.getColumnName(i);
						System.out.print(String.format("%-"+(dashCount.get(i-1)+1)+"s", columnName));//여백+1줌
					}
					System.out.println();//줄바꿈
					//(-) Dash 출력
					for(Integer count:dashCount) {
						for(int i=0;i<count;i++) {
							System.out.print("-");//작을동안 -뿌려줌
						}	
						System.out.print(" ");
					}
					System.out.println();//줄바꿈
					//데이터 출력]
					while(rs.next()) {
						//각 컬럼값 뽑아오기]
						for(int i=1;i<=columnCount;i++) {
							int columnType=rsmd.getColumnType(i);
							String columnValue;
							if(columnType==Types.TIMESTAMP) columnValue=rs.getDate(i).toString();//date를 string으로 바꿔서 담음
							else columnValue=rs.getString(i);
							System.out.print(String.format("%-"+(dashCount.get(i-1)+1)+"s", columnValue==null?"":columnValue));//여백+1줌,null은 공백으로만듦
						}
						System.out.println();//줄바꿈
					}
				}//if(flag)
				else {//기타쿼리문(I,D,U,C,A 등)
					int affected=psmt.getUpdateCount();
					if(sql.trim().toUpperCase().startsWith("UPDATE"))//create,alter는 나보고하래, 여튼 UPDATE문이면 if들어옴
						System.out.println(affected+"행이 수정되었어요");
					else if(sql.trim().toUpperCase().startsWith("DELETE"))//create,alter는 나보고하래, 여튼 UPDATE문이면 if들어옴
						System.out.println(affected+"행이 삭제되었어요");
					else if(sql.trim().toUpperCase().startsWith("INSERT"))//create,alter는 나보고하래, 여튼 UPDATE문이면 if들어옴
						System.out.println(affected+"행이 입력되었어요");
				}
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());//오라클 에러메세지랑 똑같은거 나옴
			}
		}
	}
	//DESC MEMBER는 오라클명령어, SQL명령어가 아니라 안됨 -> 이것도 가능하다~
	public static void main(String[] args) throws Exception {
		
		new ExecuteSQL().execute();
	}

}
