package controller.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//서블릿 3.0부터 아래 어노테이션 지원, web.xml에 등록할 필요 없다
@WebServlet("/Basic/both.kosmo")//이런 패턴의 요청이 들어오면 BothController가 처리하도록 만든것, web.xml에 서블릿, 매핑된거 지우고옴
public class BothController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//하나의 컨트롤러로 GET 및 POST요청 처리하기 -> 방법1]service 오버라이딩, 방법2]get,post 한쪽으로 몰아넣기 
	//get방식, post방식 두가지로 들어왔을 때 처리하도록함
	//방법1]-service오버라이딩
	/*
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//super.service(request, response);//우리가 재정의 할거라 주석처리해도 됨
		request.setAttribute("BOTH", request.getMethod()+"방식 요청");
		//아이디, 비밀번호 입력 후 GET방식 태그, POST방식 버튼 누르면 무슨 방식인지 나옴
		request.getRequestDispatcher("/servlet13/ServletBasic.jsp").forward(request, response);
	}
	*/
	//방법2]doGet과 doPost오버라이딩해서 한쪽으로 몰아주자(즉 둘 중의 한 메소드에서만 요청 처리 로직 작성)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);//doPost로 넘겨버림
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청처리 로직 작성
		request.setAttribute("BOTH", request.getMethod()+"Method!!!");
		request.getRequestDispatcher("/servlet13/ServletBasic.jsp").forward(request, response);
	}

}
