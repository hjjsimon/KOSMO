<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>포드워드된 페이지</h1>            
        </div><!--jumbotron-->		
		<h5>파라미터로 전달된 데이타</h5>
		<ul class="list-unstyled">
			<li><%=request.getParameter("id") %></li>
			<li><%=request.getParameter("pwd") %></li>
			<li><%=request.getParameter("name") %></li>
		</ul>
		
		<h5>리퀘스트 영역에 저장된 데이타</h5>
		<%=request.getAttribute("requestScope") %><!-- "속성명" -->
	</div><!--container-->
<jsp:include page="/template/Footer.jsp"/>