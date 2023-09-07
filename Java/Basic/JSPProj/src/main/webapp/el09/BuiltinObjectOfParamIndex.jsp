<%@page import="org.eclipse.jdt.internal.compiler.lookup.Scope"%>
<%@page import="model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 처음 쓰는 태그, 지금 써야함, 위에서 3번째, WEB-INF에 라이브러리 넣어서 uri 내부 뜸 -->
    
<!-- BuiltinObjectOfParamIndex.jsp -->
<!-- param 내장객체 -->

<!-- 페이지 이동전 리퀘스트 영역에 속성 3개저장 -->
<c:set var="dtoObject" value='<%=new MemberDTO("KIM","1234","김길동","20") %>' scope="request"/>
<!-- page기본, request 설정 -->
<!-- 표현식 가능하나 나중에 안씀, ""이나 ''상관없음(자바만 문자열 더블 필수), 뭐 자바는 더블임, 여튼 value 싱글로 감쌈(자바만 구림) -->
<c:set var="stringObject" value="문자열 객체" scope="request"/>
<c:set var="integerObject" value="<%=new Integer(1000) %>" scope="request"/>

<!-- forward로 2개 전달 -->
<jsp:forward page="BuiltinObjectOfParamResult.jsp">
	<jsp:param value="10" name="first"/>
	<jsp:param value="5" name="second"/>
</jsp:forward>	


