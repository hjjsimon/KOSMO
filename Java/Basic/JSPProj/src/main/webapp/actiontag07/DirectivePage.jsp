<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- DirectivePage.jsp -->
<%
	//-독립적인 서블릿으로 변환 안됨
	// 즉 페이지에 삽입되서 서블릿으로 변홤됨으로 같은 페이지다.	(리퀘스트 영역 동일, 페이지 영역 동일)
	//변수 선언
	String directiveString="지시어로 삽입된 페이지안에서 선언한 변수";//같은 페이지라 변수사용가능

%>
<hr/>
<h1 class="display-4">DirectivePage.jsp페이지 입니다</h1>
<ul class="list-unstyled">
	<li>PAGE SCOPE:<%=pageContext.getAttribute("pageVar") %></li><!-- 페이지 영역입니다 -->
	<li>REQUEST SCOPE:<%=request.getAttribute("requestVar") %></li><!-- 리퀘스트 영역입니다 -->


</ul>