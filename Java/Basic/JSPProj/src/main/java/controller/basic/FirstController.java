package controller.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Basic/first.kosmo")
public class FirstController extends HttpServlet {
	//private static final long serialVersionUID = 1L; //구분자?라서 없어도 된대
   
	//SecondController에서 post로 이동됨, 여기서 405에러 피하려면 service()메소드 오버라이딩하자
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("WHERE") == null)
			request.setAttribute("WHERE", "첫번째 컨트롤러");
		//서블릿폼으로 포워드
		request.getRequestDispatcher("/servlet13/ServletForm.jsp").forward(request, response);
	}
	
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("WHERE", "첫번째 컨트롤러");
		//입력 폼(ServletForm.jsp)으로 포워드
		request.getRequestDispatcher("/servlet13/ServletForm.jsp").forward(request, response);
	}
	 */
	
}
