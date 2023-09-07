<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- PageScopeIncludedPage.jsp -->
<hr/>
<h1 class="display-4">삽입된 페이지</h1>   
<ul class="list-unstyled">
	<li>Integer타입 : <%=pageContext.getAttribute("pageInteger") %></li>
	<li>String타입 : <%=pageContext.getAttribute("pageString") %></li>
	<li>Date타입 : <%=pageContext.getAttribute("pageDate") %></li>
	<li>MemberDTO타입 1 : <%=pageContext.getAttribute("pageMemberFirst") %></li>
	<li>MemberDTO타입 2 : <%=pageContext.getAttribute("pageMemberSecond") %></li>
</ul>