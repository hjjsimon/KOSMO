package jdbc25.prepared;

import jdbc25.service.IConnectImpl;

public class SelectSQL extends IConnectImpl {

	public SelectSQL() {
		//super(ORACLE_URL, "scott", "scott");//이걸로도 connect 가능
		connect(ORACLE_URL, "scott", "scott");//위처럼 두단계 거치는것보다 이게 낫대
	}
	@Override
	public void execute() throws Exception {
		//1]미리 쿼리문 준비
		//1-1]특정 문자로 시작하는 레코드 검색 ->Prepared에서는 'S'->?바꿈, ||필수
		//String sql="SELECT ENAME, TRIM(TO_CHAR(sal,'L99,999')) SAL,JOB,TO_CHAR(HIREDATE,'YYYY.MM.DD') FROM EMP WHERE UPPER(ENAME) LIKE ?||'%' ORDER BY HIREDATE DESC";
		//1-2]특정 문자로 끝나는 레코드 검색
		//String sql="SELECT ENAME, TRIM(TO_CHAR(sal,'L99,999')) SAL,JOB,TO_CHAR(HIREDATE,'YYYY.MM.DD') FROM EMP WHERE UPPER(ENAME) LIKE '%'||? ORDER BY HIREDATE DESC";
		//1-3]특정 문자를 포함한 레코드 검색
		String sql="SELECT ENAME, TRIM(TO_CHAR(sal,'L99,999')) SAL,JOB,TO_CHAR(HIREDATE,'YYYY.MM.DD') FROM EMP WHERE UPPER(ENAME) LIKE '%'||?||'%' ORDER BY HIREDATE DESC";
		//2]PreparedStatement객체 생성
		psmt=conn.prepareStatement(sql);
		//3]인파라미터 설정
		psmt.setString(1, getValue("찾을 문자열").toUpperCase());//대소문자 무관처리
		try {
			//4]쿼리실행
			rs=psmt.executeQuery();//rs반환
			while(rs.next()){
				System.out.println(String.format("%-8s%-8s%-10s%s",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));//가져올 행 4개인 쿼리
			}
		} 
		finally {
			close();
		}
	
	}
	
	public static void main(String[] args) throws Exception {
		new SelectSQL().execute();
	}

}
