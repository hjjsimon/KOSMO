<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- CookieExamCartProcess.jsp -->
<%@ include file="/common/Validate.jsp" %>
<%
	//상품 최소 1개 선택, 체크박스 값 많으니 Values
	String[] values = request.getParameterValues("cart");
	if(!isValidate(out, values, "상품을 선택하세요", 1)) return;
	//상품코드를 쿠키로 저장, 즉 사용자 브라우저에 상품코드 저장
	for(String value : values){
		//상품명을 쿠키명으로 상품코드값을 쿠키값으로 하자
		String[] product = value.split(":");
		Cookie cookie = new Cookie(URLEncoder.encode(product[0], "UTF-8"), product[1]);
		//쿠키명(상품명), 쿠키값(코드값) -> 쿠키명이 한글이라 UTF-8로 인코딩해줌
		//%EC%82%AC%EA%B3%BC=PC_FR_001; 이따구로 요청헤더에 보임 앞에 %~~가 한글임, 불러올때는 디코드임
		cookie.setPath(request.getContextPath());//새로운 쿠키를 만들어서 응답할 때 씀
		//만든 쿠키를 응답헤더에 설정
		response.addCookie(cookie);//상품10개 선택시 쿠키10개 만들어짐, 응답헤더에 계속 추가됨, 끝나면 상품목록으로 이동
		
	}
	//상품목록페이지로 이동
	response.sendRedirect("CookieExamIndex.jsp");//선택하고 요청헤더 보면 프로덕트 쿠키 들어가있음
	
	

%>


