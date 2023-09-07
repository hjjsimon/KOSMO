<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        		<li>\${pageScope.scopeVar } : ${pageScope.scopeVar }</li><!-- null이라 아무것도 안나옴 -->
        		<li>\${requestScope.scopeVar } : ${requestScope.scopeVar }</li>
        		<!-- 리퀘스트 영역, 출력, scopeVar를 페이지영역에서 찾았는데 forward로 페이지영역 바껴서 못찾음, request영역에서 찾아와서 리퀘스트 영역 출력  -->
        		<li>\${sessionScope.scopeVar } : ${sessionScope.scopeVar }</li>
        		<li>\${applicationScope.scopeVar } : ${applicationScope.scopeVar }</li>
        	</ul>
        	<h1 class="display-4">xxxScope계열 객체 지정</h1>
        	<ul class="list-unstyled">
        		<li>\${scopeVar } : ${scopeVar }</li><!-- 제일 작은 것부터 찾으므로 이번엔 모두 리퀘스트 영역 출력 -->
        		<li>\${scopeVar } : ${scopeVar }</li>
        		<li>\${scopeVar } : ${scopeVar }</li>
        		<li>\${scopeVar } : ${scopeVar }</li>
        	</ul>
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>