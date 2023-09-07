<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	/*
		EL에서는 각 영역에 저장된 속성을 읽어올 수 있는 xxxScope계열 내장 객체를 제공함.
		즉 pageScope/requestScope/sessionScope/applicationScope
		
		읽어 올때 : xxxxScope.속성명 혹은 xxxxScope["속성명"]
		또한 xxxxScope는 생략가능, 생략시 가장 작은 영역에 있는 값을 읽어옴
		ex) pageScope부터 단계별로 Scope넘어가며 값을 찾음(대부분 request영역에 저장)
	*/
	//나중에는 서블릿 만들겠지만 지금은 JSP로 간단히
	//각 영역에 속성 저장(JSP의 기본 내장객체로)]
	pageContext.setAttribute("scopeVar", "페이지 영역");
	request.setAttribute("scopeVar", "리퀘스트 영역");
	session.setAttribute("scopeVar", "세션 영역");
	application.setAttribute("scopeVar", "어플리케이션 영역");
	
%>
<!-- BuiltinObjectOfScopeIndex.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>EL의 xxxScope계열 내장객체</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">각 영역에 저장된 속성 읽기</legend>
        	<h1 class="display-4">xxxScope계열 객체 지정</h1>
        	<ul class="list-unstyled">
        		<li>\${pageScope.scopeVar } : ${pageScope.scopeVar }</li>
        		<li>\${requestScope.scopeVar } : ${requestScope.scopeVar }</li><!-- 지정한 영역에서 scopeVar읽어오기 -->
        		<li>\${sessionScope.scopeVar } : ${sessionScope.scopeVar }</li>
        		<li>\${applicationScope.scopeVar } : ${applicationScope.scopeVar }</li>
        	</ul>
        	<h1 class="display-4">xxxScope계열 객체 지정</h1>
        	<ul class="list-unstyled">
        		<li>\${scopeVar } : ${scopeVar }</li><!-- 제일 작은 것부터 찾으므로 모두 페이지영역 출력 -->
        		<li>\${scopeVar } : ${scopeVar }</li>
        		<li>\${scopeVar } : ${scopeVar }</li>
        		<li>\${scopeVar } : ${scopeVar }</li>
        	</ul>
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>
<jsp:forward page="BuiltinObjectOfScopeResult.jsp"/>