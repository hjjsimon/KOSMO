package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContentTypeServlet extends HttpServlet{
	//[JSP로 페이지 이동 안하고 서블릿에서 웹브라우저로 직접 출력]
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getServletContext() 해야 가져올 수 있음
		//jsp에서 맨위에 컨텐츠타입 써있는거, 서블릿은 이 코드로 대신함(바로 브라우저 출력시에만 필수)	
		//F12 후 Network에서 보면 응답헤더의 응답헤더명 중 Content-Type 하나한거, 오른쪽에 있는 값들이 응답헤더값 
		resp.setContentType("text/html; charset=UTF-8");
		
		//a태그는 form태그에서 method를 post해야 get방식으로 보냄, 외에 100% get방식
		//직접 출력이므로 응답, resp써야함, 브라우저에 빨대를 꽂아야함, 네트워크 연결하는 것
		PrintWriter out = resp.getWriter();//대보면 타입나옴
		//웹브라우저로 출력
		out.println("<h2>서블릿에서 웹브라우저로 출력</h2>");//네트웤을 통해서 브라우저에 스트림 연결
		//스트림 닫기
		out.close();
	}
}
