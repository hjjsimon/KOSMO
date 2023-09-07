<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ActionTagPage.jsp -->
<%
	//-이 페이지는 서블릿으로 변환됨
	// 즉 다른 페이지다.
	// 소스코드가 아닌 변환된 HTML결과가 페이지에 삽입된다
	// 요청은 같은 요청이다 즉 리퀘스트영역 같다(리퀘스트 영역 동일, 페이지 영역 다름)
	
	//변수 선언
	String actionString="액션태그로 삽입된 페이지안에서 선언한 변수";


%>
<hr/>
<h1 class="display-4">ActionTagPage.jsp페이지 입니다</h1>
<ul class="list-unstyled">
	<li>PAGE SCOPE:<%=pageContext.getAttribute("pageVar") %></li><!-- null -->
	<li>REQUEST SCOPE:<%=request.getAttribute("requestVar") %></li><!-- 리퀘스트영역입니다 -->


</ul>