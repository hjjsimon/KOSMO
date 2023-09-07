<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>Database</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">데이터베이스 연결하기</legend>
        	<p>${message}${param.method}</p>
        	<!-- 포워드니까 param.으로 받음 -->
        	<!-- [데이터베이스 연결성공]HIKARI_CONNECTION_POOL -->
	    	<ul class="list-unstyled">
	    		<li><a href="
	    			<c:url value="/Database/HikariConnectionPool.do?method=HIKARI_CONNECTION_POOL"/>
	    			">히카리 커넥션 풀 사용</a></li><!-- 이 요청 받으면 데이터베이스 연결 -->
	    	</ul>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>