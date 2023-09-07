<%@page import="model.JWTOkens"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- LogoutBasedToken.jsp -->
<%
	//로그아웃 처리-]-쿠키에서 토큰 삭제
	JWTOkens.removeToken(request, response);
	//로그아웃 처리후 로그인 페이지로 이동]
	response.sendRedirect("LoginBasedToken.jsp");

	//KIM,1234로 로그인해보면
	//프로세스: 응답헤더에 쿠키 있음, 요청헤더에 쿠키 없음
	//마이페이지: 응답헤더에 쿠키 없음, 요청헤더에 쿠키 있음
	//로그아웃해보면
	//로그아웃: 응답헤더 쿠키 있음
	//로그인: 요청헤더에 쿠키 없음 
%>