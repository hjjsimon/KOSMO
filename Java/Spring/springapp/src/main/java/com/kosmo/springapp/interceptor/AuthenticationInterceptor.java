package com.kosmo.springapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor{

	//한줄 메모 게시판(인증이 필요)의 인증 사전 체크
	//인증이 안된 경우 로그인화면으로 
	//인증 된 경우 요청을 그대로 컨트롤러에게 보내준다
	//디스패처서블릿에서 컨트롤러 이동 전 체크이므로 prehandle만 오버라이딩하면된다
	//컨트롤러에서는 인증여부를 판단하는 코드를 제거해도 된다
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("id")==null) {//로그인 안됨
			//1)인증이 안된경우(로그인x 후) 게시판 누르면 루트로 리다이렉트
			//////response.sendRedirect(request.getContextPath()+"/");
			//2)인증이 안된경우(로그인x 후) 게시판 누르면 로그인 화면으로 포워드
			request.setAttribute("NotMember", "Please Login...");
			request.getRequestDispatcher("/onememo/auth/Login.do").forward(request, response);
			return false;//컨트롤러 메소드로 요청이 전달이 안됨
		}
		return true;
	}
}
