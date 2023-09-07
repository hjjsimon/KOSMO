<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model.JWTOkens"%>
<%@page import="model.bbs.BBSDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- LoginProcessBasedToken.jsp -->
<%
	//사용자 입력값 받기
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	//아뒤가 KIM이고 비번이 1234면 회원이라고 가정]	
	//if("KIM".equals(id) && "1234".equals(pwd)){
	//실제 회원 테이블과 연동]
	BBSDao dao = new BBSDao(application);
	boolean flag=dao.isMember(id, pwd);
	if(flag){
		//1.로그인 처리]-토큰 생성		
		Map<String,Object> payloads = new HashMap<>();//사용자 임의 데이타 추가(Map이 Claims)
		payloads.put("password",pwd);//추가적인 사용자 정보 저장(원래 넣으면 안됨)
		long expirationTime = 1000 * 60 * 60;//토큰의 만료시간 설정(1시간)
		String token=JWTOkens.createToken(id, "/resources/tokens","secret-key", payloads, expirationTime);
		Cookie cookie = new Cookie(application.getInitParameter("COOKIE-NAME"),token);
			//토큰 발행해서 쿠키에 구움,컨텍스트 초기화 파라미터에 설정한 상수값으로 쿠키명
		cookie.setPath(request.getContextPath());//인증이니 모든 디렉토리 적용해야함, request.get~~
		response.addCookie(cookie);
		//2.로그인 처리후 마이 페이지로 이동]
		response.sendRedirect("MyPageBasedToken.jsp");//여기 헤더에 쿠키 포함됐을것
	}
	else{
		//리퀘스트 영역에 필요한 데이터 저장
		request.setAttribute("NOT-LOGIN", "아이디/비번 불일치");
		//로그인 페이지로 포워드
		request.getRequestDispatcher("LoginBasedToken.jsp").forward(request, response);
	}
%>