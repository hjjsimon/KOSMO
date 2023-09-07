package com.kosmo.springapp.basic.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatabaseController {

	@Autowired//DataSource가 어디에 있어야 빈을 주입받음, 컨테이너에 없어서 에러남, required=true가 기본값 필수라는뜻
	private DataSource dataSource;//디폴트가 히카리
	
	@GetMapping("/Database/HikariConnectionPool.do")//get방식이니까 GetMapping으로 한번 해봄
	public String connectionPool(Model model) throws SQLException {
		
		//System.out.println("dataSource:"+dataSource);
		//히카리 커넥션 풀 사용 클릭시 출력  
		//dataSource:HikariDataSource (HikariPool-7)

		//주입받은 DataSource객체로 Connection객체 얻기
		Connection conn = dataSource.getConnection();
		//데이터 저장
		model.addAttribute("message",conn == null?"[데이터베이스 연결실패]":"[데이터베이스 연결성공]");
		//커넥션 객체 풀에 반납
		if(conn != null) conn.close();
		//뷰정보 반환
		return "database06/Database";
	}
	
	
	
}
