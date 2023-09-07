<%@page import="model.bbs.BBSDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- LoginProcess.jsp -->
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
		//1.로그인 처리]-세션영역에 속성(주로 식별자만) 저장
		session.setAttribute("USER-ID", id);
		//2.로그인 처리후 마이 페이지로 이동]
		response.sendRedirect("MyPage.jsp");
	}
	else{
		//리퀘스트 영역에 필요한 데이터 저장
		request.setAttribute("NOT-LOGIN", "아이디/비번 불일치");
		//로그인 페이지로 포워드
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}
%>