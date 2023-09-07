package controller.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Basic/second.kosmo")
public class SecondCotroller extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("WHERE", "두번째 컨트롤러");
		//FirstController로 포워드(JSP페이지가 아닌)
		//뷰(jsp페이지)뿐만 아니라 컨트롤러(서블릿)로 요청을 보내는 URL도 지정가능
		//이 요청이면 FirstController로 가니까~, 여기선 doGet으로 받음, 근데 여기서 Post요청으로 보내는데..? 그래서 405에러 뜸
		request.getRequestDispatcher("/Basic/first.kosmo").forward(request, response);
	}

}
