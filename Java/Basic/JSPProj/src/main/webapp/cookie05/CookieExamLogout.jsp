<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- CookieExamLogout.jsp -->
<%
	//로그아웃 처리]-세션영역에 저장된 속성 삭제
	//방법1]
	//session.removeAttribute("USER-ID");
	//session.removeAttribute("USER-PWD");
	//방법2]
	session.invalidate();//이거 하나면됨
	
	//로그아웃 처리 후 인덱스로 이동
	response.sendRedirect("CookieExamIndex.jsp");

%>
