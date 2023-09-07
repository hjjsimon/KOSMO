<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- IncludedPage.jsp , 또 들어갈 필요 없는 아래 항목 지웠음-->
<!-- 여러 JSP페이지에서 공통으로 사용할 변수나 상수, 메소드 정의 -->
<%!
	private SimpleDateFormat dateFormat = new SimpleDateFormat();
	//공통 메소드] 모든 jsp 페이지에서 가져다 쓰도록 함
	private String getDate(String pattern){
		dateFormat.applyPattern(pattern);
		return dateFormat.format(new Date());
	}
%>
<%
	//공통 변수]
	//C:\Program Files\Java\tomcat-10.1.8\work\Catalina\localhost\ROOT\org\apache\jsp
	//에서 확인하면 index_jsp.java 있음, _jspService()메소드 확인하면 매개변수 request 존재
	String requestURL = request.getRequestURI().toString();//request라는 내장객체(service메소드의 매개변수) 있음(exception도 내장객체)
	String currentPageName = requestURL.substring(requestURL.lastIndexOf("/")+1);
%>