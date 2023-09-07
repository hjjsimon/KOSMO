<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ForwardActionTagIndex.jsp -->
<%
	//포워드전 영역에 속성저장
	pageContext.setAttribute("pageVar", "페이지 영역");//포워드로 다른 페이지 되면 사라짐
	request.setAttribute("requestVar", "리퀘스트 영역");//포워드니까 request그대로 
%>
<!-- 액션 태그로 포워딩 -->
<jsp:forward page="/actiontag07/ForwardActionTagResult.jsp"/>
<!-- 
	PAGE SCOPE : null
	REQUEST SCOPE : 리퀘스트 영역 
-->


