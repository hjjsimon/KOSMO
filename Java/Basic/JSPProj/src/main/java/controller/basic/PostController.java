package controller.basic;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//1.HttpServlet상속
public class PostController extends HttpServlet{

	//post방식이니까 doPost로 처리해야함
	//POST method로 전달되는 요청을 doGet()으로 받기: HTTP 상태 405에러 - 허용되지 않는 메소드
	//클라이언트의 요청방식과 서버의 요청방식이 다르면 405에러
	/*
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	*/
	
	//2.POST방식으로 요청이 들어옴:doPost오버라이딩
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//3.파라미터 받기
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		//4.리퀘스트 영역에 데이터 저장
		req.setAttribute("POST", String.format("POST방식 요청입니다.아이디:%s,비밀번호:%s", id, pwd));
		//5.뷰(JSP페이지)로 포워딩
		req.getRequestDispatcher("/servlet13/ServletBasic.jsp").forward(req, resp);
		
	}
	
	
	
	
	
	
	
	
}
