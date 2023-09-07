package model.dataroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import jakarta.servlet.ServletContext;
import model.PagingUtil;
import service.DaoService;

public class DataRoomDAO implements DaoService<DataRoomDTO> {//DaoService<T>의 T에 지정
	
	//멤버변수(속성들)
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public DataRoomDAO(ServletContext context) {
		try {
			//커넥션 풀 사용.즉 커넥션 풀에서 커넥션 객체 가져오기(리스너에서 컨텍스트 영역에 저장한 데이타소스 가져오기)
			DataSource source = (DataSource)context.getAttribute(DATA_SOURCE);
			conn=source.getConnection();
		}
		catch(SQLException e) {e.printStackTrace();}
	}//////////////////
	//자원반납용-사용한 커넥션 객체를 다시 풀에 반납
	@Override
	public void close() {
		try {
			//메모리 해제
			if(rs !=null) rs.close();
			if(psmt !=null) psmt.close();
			//커넥션 풀에 커넥션 객체 반납-메모리 해제 아님]
			if(conn !=null) conn.close();
		}
		catch(SQLException e) {e.printStackTrace();}
		
	}///////////////////
	//전체 목록용]
	/*
	 * 페이징 순서
	 * 1. 전체 목록용 쿼리를 구간쿼리로 변경
	 * 2. 전체 레코드수 얻기용 메소드 추가
	 * 3. 페이징 로직을 리스트 컨트롤러에 추가
	 * 4. 리스트.JSP페이지에 결과값 출력
	 */
	@Override
	public List<DataRoomDTO> selectList(Map map) {
		List<DataRoomDTO> records = new Vector<>();
		//페이징 적용 전 쿼리
		//String sql="SELECT * FROM dataroom ORDER BY no DESC";
		//페이징 적용 쿼리(구간 쿼리)
		String sql="SELECT * FROM (SELECT T.*,ROWNUM R FROM (SELECT * FROM dataroom ORDER BY no DESC) T) WHERE R BETWEEN ? AND ?";
		//시작행번호? AND 끝행번호?
		try {
			psmt = conn.prepareStatement(sql);
			//시작 및 끝 행번호 설정]
			psmt.setString(1, map.get(PagingUtil.START).toString());
			psmt.setString(2, map.get(PagingUtil.END).toString());
			rs= psmt.executeQuery();
			while(rs.next()) {
				DataRoomDTO dto = new DataRoomDTO();
				dto.setAttachFile(rs.getString(6));//순서 모르면 컬럼명도 가능
				dto.setContent(rs.getString(5));
				dto.setDownCount(rs.getString(7));
				dto.setName(rs.getString(2));
				dto.setNo(rs.getString(1));
				dto.setPassword(rs.getString(3));
				dto.setPostDate(rs.getDate(8));
				dto.setTitle(rs.getString(4));				
				records.add(dto);
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		return records;
	}

	//상세보기용-레코드 하나 조회
	@Override
	public DataRoomDTO selectOne(String... one) {
		DataRoomDTO dto = null;
		try {
			String sql = "SELECT * FROM dataroom WHERE no=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, one[0]);//var...args로 배열이므로 0번방
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new DataRoomDTO();
				dto.setAttachFile(rs.getString(6));
				dto.setContent(rs.getString(5));
				dto.setDownCount(rs.getString(7));
				dto.setName(rs.getString(2));
				dto.setNo(rs.getString(1));
				dto.setPassword(rs.getString(3));
				dto.setPostDate(rs.getDate(8));
				dto.setTitle(rs.getString(4));	
			}
		}
		catch (SQLException e) {e.printStackTrace();}
		
		return dto;
	}
	//전체 레코드 수 얻기용
	@Override
	public int getTotalRecordCount(Map map) {
		int totalCount=0;
		try {
			psmt = conn.prepareStatement("SELECT COUNT(*) FROM dataroom");//전체 레코드 수 얻음
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		} 
		catch (Exception e) {e.printStackTrace();}
		return totalCount;
	}
	//입력용
	@Override
	public int insert(DataRoomDTO dto) {
		int affected=0;
		String sql="INSERT INTO dataroom VALUES(SEQ_DATAROOM.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getPassword());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getAttachFile());			
			affected=psmt.executeUpdate(); 
		}
		catch(SQLException e) {e.printStackTrace();}		
		return affected;
	}////////////////

	@Override
	public int update(DataRoomDTO dto) {
		int affected=0;
		String sql="UPDATE dataroom SET name=?,password=?,title=?,content=?,attachfile=? WHERE no=? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getPassword());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getAttachFile());			
			psmt.setString(6, dto.getNo());
			affected=psmt.executeUpdate(); 
		}
		catch(SQLException e) {e.printStackTrace();}		
		return affected;
	}

	@Override
	public int delete(DataRoomDTO dto) {
		int affected=0;
		String sql="DELETE dataroom WHERE no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getNo());
			affected=psmt.executeUpdate();
		}
		catch(SQLException e) {e.printStackTrace();}		
		return affected;
	}
	//비밀번호 확인용]
	public boolean isCorrectPassword(String no, String password) {
		
		try {
			String sql = "SELECT COUNT(*) FROM dataroom WHERE no=? AND password=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1) == 1) return true;//비밀번호 일치O
		} 
		catch (SQLException e) {e.printStackTrace();}
		return false;
	}
	//다운로드 수 증가용]
	public void updateDownCount(String no) {
		try {
			String sql = "UPDATE dataroom SET downcount=downcount+1 WHERE no=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			psmt.executeUpdate();
		}
		catch (SQLException e) {e.printStackTrace();}
	}

}
