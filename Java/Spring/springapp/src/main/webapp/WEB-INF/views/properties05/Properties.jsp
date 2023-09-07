<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>Properties</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">속성파일</legend>
        	<p>${message}</p>
	    	<ul class="list-unstyled">
	    		<li><a href="<c:url value="/Properties/ValueDefault"/>">@Value어노테이션(디폴트 속성파일)</a></li>
	    		<li><a href="<c:url value="/Properties/ValueCustom"/>">@Value어노테이션(NOT 디폴트 속성파일)</a></li>
	    	</ul>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>