package jdbc25.etc;

import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.List;
import java.util.Vector;

import jdbc25.service.IConnectImpl;

//메타데이터? -> 데이터를 설명하기 위한 데이터 ex.컬럼명, 데이터만 주면 뭔지모름, 컬럼명 나이,키,몸무게.. 그러면 알 수 있음
//ResultSetMetaData -> 자바에서는 이걸 인터페이스로 추상화해놓음, 여기 메소드를 이용해서 RS에 있는 컬럼명, 컬럼의 타입 등을 알아낼 수 있음
//쿼리로 SELECT해서 RS를 가져옴, 이 객체로 getMetaData()메소드 쓰면 컬럼명, 컬럼타입, null여부 등 컬럼의 정보를 가져올 수 있다~
//결론: 사용자가 무슨 RS를 SELECT로 뽑아와도 괜찮다
public class ResultSetMetaDataSQL extends IConnectImpl {
	
	public ResultSetMetaDataSQL() {
		connect(ORACLE_URL, "scott", "scott");
	}
	
	@Override
	public void execute() throws Exception {
		try {
			//1]쿼리문 준비
			String sql=getQueryString();
			//2]PreparedStatement객체 생성
			psmt=conn.prepareStatement(sql);
			//3]쿼리 실행(RS로 뽑는거라 인파라미터 당연히 없음,오라클처럼 끝에 ;입력은 불가, 에러남/숙제 시 ;로 끝내면 출력되도록 하면됨, ;입력전까지는 계속 입력 받아야함, 실행시에는 ;제거)
			rs=psmt.executeQuery();
			//SELECT 쿼리 실행시 컬럼에 대한 정보 얻기
			//가]ResultSet객체의 getMetaData()로 ResultSetMetaData얻기
			ResultSetMetaData rsmd=rs.getMetaData();
			//나]총 컬럼수 얻기-ResultSetMetaData의 int getColumnCount()
			int columnCount=rsmd.getColumnCount();
			System.out.println("총 컬럼수:"+columnCount);
			//다]컬럼명 얻기-String getColumnName(int column)
			for(int i=1;i<=columnCount;i++) {
				String columnName=rsmd.getColumnName(i);
				int length=columnName.length()+2;//컬럼명 password면 8반환, 그리고 2칸 여백 줌
				System.out.print(String.format("%-"+length+"s", columnName));//length에 숫자 들어가면 늘 하던 %-9s 이런것	
			}
			//라]컬럼타입 얻기-int getColumnType(int column)->타입을 반환하는데 int? 상수로 반환하는것, 타입과 관련된 상수는 java.sql.Types클래스에 정의됨
			System.out.println("\r\n[자바의 컬럼타입으로 얻기]");
			for(int i=1;i<=columnCount;i++) {
				int columnType=rsmd.getColumnType(i);
				switch(columnType) {
				case Types.VARCHAR: System.out.println("오라클의 VARCHAR2"); break;
				case Types.NVARCHAR: System.out.println("오라클의 NVARCHAR2"); break;
				case Types.CHAR: System.out.println("오라클의 CHAR"); break;
				case Types.NCHAR: System.out.println("오라클의 NCHAR"); break;
				case Types.NUMERIC: System.out.println("오라클의 NUMBER"); break;//NUMERIC 주의
				case Types.TIMESTAMP: System.out.println("오라클의 DATE"); break;//TIMESTAMP 주의
				default: System.out.println("오라클의 기타자료형");
				}
			}
			//마]String getColumnTypeName(int column): 오라클의 타입명 그대로로 반환
			System.out.println("[오라클의 컬럼타입으로 얻기]");
			for(int i=1;i<=columnCount;i++) {
				String columnTypeName=rsmd.getColumnTypeName(i);
				System.out.println(columnTypeName);
			}
			//바] 컬럼의 NULL허용여부: int isNullable(int column): NULL허용시 1, NOT NULL은 0 반환(보통 is~는 t/f반환, 이게 특이)
			System.out.println("[컬럼의 NULL허용여부 얻기]");
			for(int i=1;i<=columnCount;i++) {
				int isNull=rsmd.isNullable(i);//
				System.out.println(isNull==1?"NULL 허용":"NOT NULL");
			}
			//사] 컬럼의 크기 얻기: int getPrecision(int column): DATE처럼 ()괄호 없는 자료형은 0반환(ex.NUMBER(7.2)는 전체자리수 7반환 
			System.out.println("[컬럼의 크기 얻기]");
			for(int i=1;i<=columnCount;i++) {
				int columnSize=rsmd.getPrecision(i);
				System.out.println(columnSize);
			}			
			//각 컬럼의 자리수 설정하기(오라클처럼 컬럼명 밑에---만들기!)
			//1)오라클처럼 NUMBER타입(자리수 지정안한 NUMBER 포함)은 무조건 10자리
			//2)CHAR계열(VARCHAR2)는 해당 자리수로 설정(단 NCHAR계열은 자리수의 2배로 설정, 글자니까)
			//3)DATE타입은 10자리(원래 오라클은 8자리)
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
		}
		finally {
			close();
		}
	}
		
	public static void main(String[] args) throws Exception {
		new ResultSetMetaDataSQL().execute();
	}

}
