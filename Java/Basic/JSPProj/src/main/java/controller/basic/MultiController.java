package controller.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/Basic/multi.kosmo")
public class MultiController extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String crud = req.getParameter("crud");
		String message;
		switch (crud.toUpperCase()){
			case "CREATE":message="입력처리 요청입니다.";break; 
			case "READ":message="조회처리 요청입니다.";break; 
			case "UPDATE":message="수정처리 요청입니다.";break; 
			default:message="삭제처리 요청입니다";
		}
		req.setAttribute("MULTI", message);
		req.getRequestDispatcher("/servlet13/ServletBasic.jsp").forward(req, resp);
	}//////////////////////////

}
