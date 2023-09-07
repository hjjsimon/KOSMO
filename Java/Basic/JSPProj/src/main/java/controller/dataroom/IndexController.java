package controller.dataroom;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DataRoom/index.kosmo") //index.jsp에서 이 요청 발생시 처리하는 서블릿으로 만든 것
public class IndexController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//WEB-INF밑에 있는 뷰(JSP)는 컨트롤러를 거치지 않고는 직접 url접근 불가(404에러): 간접 보안 효과
		//req.getRequestDispatcher("WEB-INF/dataroom14/List.jsp").forward(req, resp);
		//WEB-INF/dataroom14/List.jsp 하면 안됨, List처리하는 컨트롤러로 이동해야함
		req.getRequestDispatcher("/DataRoom/List.kosmo").forward(req, resp);//JSP경로니까 webapp아래 폴더구조 따라야함
		
	}
}

