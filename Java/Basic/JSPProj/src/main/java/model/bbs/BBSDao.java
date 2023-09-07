package model.bbs;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletContext;
import model.PagingUtil;
import service.DaoService;

/*
  DAO(Data Access Object):데이타에 접근해서 CRUD작업을
                          수행하는 업무처리 로직을 갖고 있는 객체
  
*/
public class BBSDao implements DaoService<BBSDto> {

	//멤버변수(속성들)
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	//생성자
	public BBSDao(ServletContext context) {
		try {
			//드라이버 로딩]
			Class.forName(context.getInitParameter("ORACLE-DRIVER"));
			
			//방법1]ResourceBundle(java.util패키지) API 사용
			//.properties파일은 반드시 클래스 패스인 src/main/java에 위치시키자
			//파일명만(.properties빼고)			
			ResourceBundle resource=ResourceBundle.getBundle("/resources/database");
			String username = resource.getString("username");
			String password = resource.getString("password");
			//방법2]ServletContext의 getResourceAsStream()메소드와 Properties API 사용
			/*InputStream is= context.getResourceAsStream("/resources/database.properties");
			Properties props = new Properties();
			props.load(is);
			String username= props.getProperty("username");
			String password= props.getProperty("password");*/
			//데이타베이스 연결]
			//conn = DriverManager.getConnection(context.getInitParameter("ORACLE-URL"),username,password);
			
			//연결 변형1] ConnectionPool 이후 변형
			//Context ctx = new InitialContext();
			//DataSource source=(DataSource)ctx.lookup("java:comp/env/jsp");
			//conn = source.getConnection();
			
			//연결 변형2]컨텍트 리스너(MyContextListener)에 데이터소스를 얻는 코드 추가
			DataSource source = (DataSource)context.getAttribute("DataSource");//MyContextListener 확인
			//위 코드는 DataSource source = (DataSource)ctx.lookup("java:comp/env");	Dao마다 이거 해야하는데 지금은 이거 한줄이면 됨
			
			conn = source.getConnection();
			
			//System.out.println("데이타베이스 연결 성공:"+conn);
		}
		catch(Exception e) {e.printStackTrace();}
		
	}//////////////
	//자원반납용
	@Override
	public void close() {
		try {
			if(rs !=null) rs.close();
			if(psmt !=null) psmt.close();
			if(conn !=null) conn.close();
		}
		catch(SQLException e) {e.printStackTrace();}
		
	}////////////////////
	//전체 목록 가져오기]
	/*
	 * 페이징 로직 추가하기
	 * DAO에서 할일
	 * 1. 전체 목록 쿼리를 구간 쿼리로 변경
	 * 2. 총 레코드수 구하는 메소드 추가	
	 * 3. List.jsp에 페이징관련 코드 추가
	 */
	@Override
	public List<BBSDto> selectList(Map map) {
		
		List<BBSDto> articles= new Vector<>();
		//페이징 적용 前 쿼리- 전체 목록 쿼리
		/*
		String sql="SELECT b.*,name FROM bbs b JOIN member m ON b.id=m.id ";		
		//검색시 추가 시작
		if(map.get("searchColumn") !=null) {
			sql+=" WHERE "+map.get("searchColumn")+ " LIKE '%"+map.get("searchWord")+"%' ";
		}		
		sql+=" ORDER BY no DESC";*/
		//페이징 적용 - 구간 쿼리
		String sql="SELECT * FROM (SELECT T.*,ROWNUM R FROM (SELECT b.*,name FROM bbs b JOIN member m ON b.id=m.id ";		
		//검색시 추가 시작
		if(map.get("searchColumn") !=null) {//null이 아니면 검색어 넘어옴
			sql+=" WHERE "+map.get("searchColumn")+ " LIKE '%"+map.get("searchWord")+"%' ";//한칸씩 띄워놔야 쿼리 붙었을 때 에러 안뜸
		}		
		sql+=" ORDER BY no DESC) T) WHERE R BETWEEN ? AND ?";//전체를 서브쿼리로 만들고 T로 별칭(첫번째?:시작ROWNUM번호, 두번째?:끝ROWNUM번호)
		
		try {
			psmt = conn.prepareStatement(sql);
			//페이징을 위한 시작 및 종료 rownum설정
			psmt.setString(1, map.get(PagingUtil.START).toString());
			psmt.setString(2, map.get(PagingUtil.END).toString());			
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				BBSDto dto = new BBSDto();
				dto.setContent(rs.getString(4));
				dto.setHitCount(rs.getString(5));
				dto.setId(rs.getString(2));
				dto.setNo(rs.getString(1));
				dto.setPostDate(rs.getDate(6));
				dto.setTitle(rs.getString(3));
				dto.setName(rs.getString(7));
				articles.add(dto);
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		
		return articles;
	}///////////
	//총 레코드 수 얻기용
	@Override
	public int getTotalRecordCount(Map map) {//검색시에도 적용가능하도록 map 받음, 검색하면 글 개수 달라지니까 map
		int totalRecordCount=0;		
		String sql="SELECT COUNT(*) FROM bbs ";//검색시 페이지적용이 없으면 이것만으로도 충분함
		
		//검색시 추가 시작(검색어 입력값 추가) 이거 추가해줘야 검색 후 페이징이 바뀜
		if(map.get("searchColumn") !=null) {
			sql+=" WHERE "+map.get("searchColumn")+ " LIKE '%"+map.get("searchWord")+"%' ";
		}
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalRecordCount=rs.getInt(1);
		}
		catch(SQLException e) {e.printStackTrace();}		
		return totalRecordCount;
	}///////////////////
	
	
	//상세보기용-레코드 하나 조회
	@Override
	public BBSDto selectOne(String... one) {
		BBSDto dto=null;
		try {
			//목록에서 넘어온 경우에만 조회수 증가
			if(one.length >= 2 && (one[1].toUpperCase().startsWith("LIST") || one[1].toUpperCase().startsWith("VIEW"))) {
				psmt = conn.prepareStatement("UPDATE bbs SET hitcount = hitcount+1 WHERE no=?");
				psmt.setString(1, one[0]);
				psmt.executeUpdate();
			}
			
			String sql="SELECT b.*,name FROM bbs b JOIN member m ON b.id=m.id WHERE no=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, one[0]);//var...args라서 배열임, 0번방!
			rs = psmt.executeQuery();//쿼리 틀리지 않는이상 1개 또는 0개 나옴
			if(rs.next()) {
				dto = new BBSDto();
				dto.setContent(rs.getString(4));
				dto.setHitCount(rs.getString(5));
				dto.setId(rs.getString(2));
				dto.setNo(rs.getString(1));
				dto.setPostDate(rs.getDate(6));
				dto.setTitle(rs.getString(3));
				dto.setName(rs.getString(7));
			}////////
		}
		catch(SQLException e) {e.printStackTrace();}
		
		
		return dto;
	}////////////////////////////
	
	//입력용
	@Override
	public int insert(BBSDto dto) {
		int affected=0;
		String sql="INSERT INTO bbs(no,id,title,content) VALUES(SEQ_BBS.NEXTVAL,?,?,?)";
		try {
			psmt= conn.prepareStatement(sql,new String[] {"no"});
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			affected = psmt.executeUpdate();
			
			//입력된 행의 키값 가져오기			
			rs=psmt.getGeneratedKeys();
			if(rs.next()) {
				System.out.println("방금 입력한 행의 키값:"+rs.getLong(1));
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}////////////////

	@Override
	public int update(BBSDto dto) {
		int affected=0;
		String sql="UPDATE bbs SET title=?,content=? WHERE no=?";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setString(3, dto.getNo());
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			affected = psmt.executeUpdate();			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}//////////////////
	//삭제용
	@Override
	public int delete(BBSDto dto) {
		int affected=0;
		String sql="DELETE bbs WHERE no=?";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, dto.getNo());			
			affected = psmt.executeUpdate();			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}/////////////////////////
	
	//이전글/다음글 조회
	/*
	 가정: 현재 보고 있는 글의 idx값 10이라고 가정하자
	//이전글 얻어오기(idx값과 title동시에 얻어 오기)
	-키값만 얻기
	select min(b_no) from bbs where b_no > 현재글
	-키값하고 다른 컬럼 얻기
	select b_no,title from from bbs WHERE NO=(SELECT MIN(NO) FROM bbs WHERE NO > 현재글번호 )
	//다음글 얻어 오기
	-키값만 얻기
	select max(b_no) from bbs where b_no < 현재글
	-키값하고 다른 컬럼 얻기
	select b_no,title from bbs WHERE NO=(SELECT MAX(NO) FROM bbs WHERE NO < 현재글번호 )
	 */
	public Map<String, BBSDto> prevNext(String currentNo){
		Map<String, BBSDto> map = new HashMap<>();
		try {
			//이전글 얻기]
			String sql="SELECT no,title FROM bbs WHERE no=(SELECT MIN(no) FROM bbs WHERE no > ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, currentNo);
			rs = psmt.executeQuery();
			if(rs.next()) {//rs.next() true면 꺼내올 행이 있는 것
				map.put("PREV", new BBSDto(rs.getString(1), null,rs.getString(2), null,null, null));
				//map에 키,벨류 설정/ 컬럼 위에 2개꺼냄 no, title, 1번 가져옴, 필요없는 값은 null주면 됨
			}
			//다음글 얻기]
			sql="SELECT no,title FROM bbs WHERE no=(SELECT MAX(no) FROM bbs WHERE no < ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, currentNo);
			rs = psmt.executeQuery();
			if(rs.next()) {//rs.next() true면 꺼내올 행이 있는 것
				map.put("NEXT", new BBSDto(rs.getString(1), null,rs.getString(2), null,null, null));
				//컬럼 위에 2개꺼냄 no, title, 1번 가져옴, 필요없는 값은 null주면 됨
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		
		return map;
	}/////////////////////////
	
	//회원여부 판단용
	public boolean isMember(String username,String password) {
		String sql="SELECT COUNT(*) FROM member WHERE id=? AND pwd=?";//사용자 입력값으로 ?값 세팅(회원이면1,아니면0)
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			rs.next();//0이거나 1이거나 무조건 하나 나옴
			if(rs.getInt(1)==0) return false;//인덱스1부터 시작, 0이면 회원x
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}////////////////

}
