<%@page import="model.PagingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">out태그</legend><!-- 얘도 jsp가 out내장객체 사용하듯, 출력시 out -->
			<c:set var="htmlString"><h4>h4로 감싼 문자열</h4></c:set>        	
        	<h3>EL로 출력</h3><!-- h4태그를 감싸서 EL로 출력 -->
        	\${htmlString } : ${pageScope.htmlString }
        	<h3>JSTL의 out태그로 출력 - escapeXml="false"</h3><!-- (true가 기본값 false하면 완전 EL 동일, EL이 편함, 밑에꺼 안씀) -->
        	<c:out value="${htmlString }" escapeXml="false"/>
        	<h3>JSTL의 out태그로 출력 - escapeXml="true"(기본값,안써도됨-HTML태그가 그대로 출력)</h3>
        	<c:out value="${htmlString }" escapeXml="true"/><!-- <h4>h4로 감싼 문자열</h4> 출력됨(EL은 h4가 해석 후 출력이라 태그 안보임) -->
        	<h3>default속성</h3>
        	<!-- 
        		out태그의 value속성의 값이 null일때
        		default속성에 지정한 값을 출력함
        		(EL보다 기능이 많아 필요시만 사용)
        	 -->
        	<ul class="list-unstyled">
        		<li>value="" : <c:out value="" default="빈문자열"/> </li><!-- null은 아님, 빈문자열이라 출력안될뿐 -->
        		<li>value="null이라는 문자열" : <c:out value="null" default="이건 null값이 아니고 null문자열이다"/><!-- null이면 출력이 안돼야함 -->
        		<li>value="null값" : <c:out value="${null }" default="null값이다"/> </li><!-- 이건 진짜 null임 -->
        	</ul>
        	<h3>페이지 링크에 응용</h3>	
        	<%
        		String currentPage = PagingUtil.NOWPAGE;
        	%>
        	<c:set var="currentPage" value="<%=currentPage %>"/>
        	<c:out value="${PagingUtil.NOWPAGE }"/><br/><!-- 위에 JSP로 안가져오면 null이라 출력x, PagingUtil에서 값을 못가져옴 -->
        	<c:out value="${PagingUtil.NOWPAGE == null }"/><br/>
        	<c:out value="${param[currentPage] }" default="1"/><br/>
        	<!-- http://localhost:8080/JSPProj/jstl10/OutTag.jsp?nowPage=99 쿼리스트링하면 윗코드 99결과 나옴(나우페이지 안넘어오면 기본값 1) -->
        	<c:url value="/bbs08/List.jsp?${currentPage }"/><br/>
        	<a href='<c:url value="/bbs08/List.jsp?${currentPage }"/><c:out value="${param[currentPage] }" default="1"/>'>BBS목록</a><br/>
        	<!-- BBS목록 마우스호버시 주소에 nowPage뜸 -->
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>