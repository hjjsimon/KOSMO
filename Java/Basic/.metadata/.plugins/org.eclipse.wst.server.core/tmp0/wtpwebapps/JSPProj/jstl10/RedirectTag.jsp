<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--
	redirect방식으로 페이지 이동
	request영역 공유 안함
	
	절대경로로 설정시 url속성에 역시	컨텍스트 루트 제외(유일한 차이점, 나머지는 이전에 배운 redirect 동일)
	단,JSP내장객체의 response의 sendRedirect()로 이동시에는 컨텍스트 루트 포함
 -->
 <!-- 리퀘스트 영역에 속성 저장 -->
 <c:set var="requestvar" value="리퀘스트 영역" scope="request"/>
 <%--
 <c:redirect url="/jstl10/ImportedPage.jsp?username=KOSMO&password=1234"/><!-- context루트(/JSPProj) 빼라 -->
 --%>
 <!-- 아래도 위 쿼리스트링과 결과 동일 -->

 <c:redirect url="/jstl10/ImportedPage.jsp?username=KOSMO&password=1234">
 	<c:param name="username" value="KOSMO"/>
 	<c:param name="password" value="1234"/>
 </c:redirect>

 <!-- 웹어플리케이션 전체 영역을 context라고 함, 어플리케이션의 컨텍스트 루트를 넣음, webapp아래 바로 TestProj있어야함 -->
 <!-- JSTL로 리다이렉트 (다른 어플안에 있는 페이지로) -->
  <!-- 다른 어플리케이션안에 있는 페이지로 redirect
  context속성:다른 웹 어플의 /로 시작하는 프로젝트명(컨텍스트 루트)만
  url속성:프로젝트명을 제외한 /로 시작하는 절대 경로
 -->
 <%--서버.xml에 뭐가 추가된댔는데 못봄
 <c:redirect url="/index.jsp" context="/TestProj">
 	<c:param name="username" value="KOSMO"/>
 	<c:param name="password" value="1234"/>
 </c:redirect>
--%>
 
 
 