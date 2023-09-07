<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">url태그</legend><!--url을 문자열로 생성해주는 태그 -->
        	<!-- 
        		url생성시 사용(value필수)]
		     	-절대경로로 생성시에는 역시
		                  컨텍스트 루트 제외 즉 컨텍스트 루트 신경 쓸 필요 없다
		        -var속성 미 지정시에는 해당 위치에
		         		url이 출력됨(param태그에 지정한 파라미터가 쿼리스트링으로 연결됨)
		        *HTML페이지 안에서 절대경로 지정시 주로 사용
    		-->
        	<h3>var속성 미지정</h3>
        	<c:url value="/jstl10/ImportedPage.jsp?username=KOSMO&password=1234"/></br>
        	<!-- /JSPProj/jstl10/ImportedPage.jsp?username=KOSMO&password=1234 출력(컨텍스트 루트 자동으로 붙어서 나옴, webapp부터 시작하면 됨) -->
        	
        	<c:url value="/jstl10/ImportedPage.jsp"><!-- 이렇게 써도 쿼리스트링으로 연결되어 url나옴 -->
        		<c:param name="username" value="KOSMO"/>
        		<c:param name="password" value="1234"/>
        	</c:url>
        	<h3>var속성 지정</h3><!---var속성을 지정하지 않은 경우 현재 위치에 url을 출력하고, 지정한 경우에는 해당 EL변수에 저장된다-->
        	<c:url value="/jstl10/ImportedPage.jsp" var="url">
        		<c:param name="username" value="KOSMO"/>
        		<c:param name="password" value="1234"/>
        	</c:url>
        	<h3>내가 원하는 위치에서 생성한 URL(절대경로) 사용</h3>
        	<a href="${url }">ImportedPage.jsp</a>       	
        	
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>