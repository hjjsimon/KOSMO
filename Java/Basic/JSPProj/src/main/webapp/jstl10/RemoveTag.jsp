<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="http://java.sun.com/jsp/jstl/core" %><!-- 이번엔 my라는 이름써보기 -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">remove태그</legend>
        	<!-- 각 영역에 JSTL을 이용해서 속성저장 -->
        	<my:set var="pageVar" value="페이지 영역"/><!-- page영역에(기본) 저장한 pageVar이름인 변수 -->
        	<my:set var="pageVar" value="리퀘스트 영역1" scope="request"/><!-- request영역에 저장한 pageVar이름인 변수 -->
        	<my:set var="requestVar" value="리퀘스트 영역2" scope="request"/>
        	<my:set var="sessionVar" value="세션 영역" scope="session"/>
        	<my:set var="applicationVar" value="어플리케이션 영역" scope="application"/>
        	<h3>삭제전 출력</h3>
        	<ul class="list-unstyled">
        		<li>pageVar : ${pageVar }</li>
        		<li>pageVar : ${requestScope.pageVar }</li>
        		<li>requestVar: ${requestVar }</li>
        		<li>sessionVar : ${sessionVar }</li>
        		<li>applicationVar : ${applicationVar }</li>
        	</ul>
        	<my:remove var="requestVar" scope="session"/><!-- 삭제될게 없음 -->
        	<h3>영역이 다른 scope지정후 속성을 삭제한경우</h3>
        	<ul class="list-unstyled">
        		<li>pageVar : ${pageVar }</li>
        		<li>pageVar : ${requestScope.pageVar }</li>
        		<li>requestVar: ${requestVar }</li>
        		<li>sessionVar : ${sessionVar }</li>
        		<li>applicationVar : ${applicationVar }</li>
        	</ul>
        	<my:remove var="pageVar"/><!-- 영역 미지정시, 모든 영역의 동일한 이름의 속성 (pageVar) 삭제함 -->
        	<h3>scope지정하지 않고 속성을 삭제한경우</h3>
        	<ul class="list-unstyled">
        		<li>pageVar : ${pageVar }</li>
        		<li>pageVar : ${requestScope.pageVar }</li>
        		<li>requestVar: ${requestVar }</li>
        		<li>sessionVar : ${sessionVar }</li>
        		<li>applicationVar : ${applicationVar }</li>
        	</ul>
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>