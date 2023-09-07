package jdbc25.etc;

import java.sql.ResultSet;

import jdbc25.service.IConnectImpl;

//커서 전진(아래로)만 가능, 커서를 후진(위로)불가, 특정 레코드로 커서 설정도 불가, 근데 ResultSet객체의 메소드로 다 가능해짐
//디폴트가 forward only임, psmt=conn.prepareStatement(sql,ResultSet.~~,Result~~) 써야함
public class ResultSetType extends IConnectImpl{

	public ResultSetType(String url, String user, String password) {
		super(url,user,password);
	}
	@Override
	public void execute() throws Exception {
		//1]쿼리문 미리 준비
		String sql="SELECT * FROM EMP ORDER BY SAL DESC";
		//2]쿼리 실행용 객체생성(preparedStatement)
		//psmt=conn.prepareStatement(sql); ->이걸로는 last 쓸 수 없음, next만 가능
		//2-2]커서를 전/후진 가능하도록 설정
		psmt=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//SCROLL_SENSITIVE랑 인센서티브 차이 없음, Concur도 2개 나오는데 동일
		try {
			//3]쿼리 실행
			rs=psmt.executeQuery();
			System.out.println("커서를 마지막 레코드로 이동:"+rs.last());//커서가 첫번째 레코드 위에서 바로 맨 아래 레코드로 이동
			System.out.println("총 레코드 수:"+rs.getRow());//커서가 위치한 행의 인덱스 반환, emp테이블 총 12개 레코드 있음, 12줄 있다는 뜻
			rs.beforeFirst();//커서를 다시 첫번째 레코드 위로 이동
			System.out.println("[연봉이 높은 순]");
			while(rs.next()) {//맨아래인데 한칸 더 아래로 이동시 읽을 레코드 없어 false나옴
				System.out.println(String.format("%-6s%-8s%-11s%-6s%s"
						,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(6),rs.getDate(5)));//getString은 시분초까지 나오니까 getDate				
			}
			System.out.println("[연봉이 낮은 순]");
			while(rs.previous()) {//위 next에서 맨끝 아래로 내려옴, previous쓰면 거꾸로 위로 이동함, 역순이 됨
				System.out.println(String.format("%-6s%-8s%-11s%-6s%s"
						,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(6),rs.getDate(5)));//getString은 시분초까지 나오니까 getDate				
			}
		}
		finally {
			//4]자원반납
			close();
		}
	}
	public static void main(String[] args) throws Exception {
		
		new ResultSetType(ORACLE_URL,"scott","scott").execute();//오랜만에 인자생성자로
	}
}
