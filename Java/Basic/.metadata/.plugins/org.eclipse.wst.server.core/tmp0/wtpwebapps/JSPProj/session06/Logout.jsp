<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Logout.jsp -->
<%
	//로그아웃 처리]-세션영역에 저장된 속성 삭제
	session.invalidate();
	//로그아웃 처리후 로그인 페이지로 이동]
	response.sendRedirect("Login.jsp");
%>