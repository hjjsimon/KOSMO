package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", "Hello Servlet!!!");
		req.getRequestDispatcher("/HelloWorld.jsp").forward(req, resp);//서블릿이 받은 요청 .jsp로 전달함, request영역에서 message키로 얻어올 수 있음
		
	}
}
