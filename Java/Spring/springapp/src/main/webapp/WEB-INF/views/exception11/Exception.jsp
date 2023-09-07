<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>Exception</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">스프링 예외처리 <small>${message}</small></legend>
        	<form class="form-inline"
			action="<c:url value="/Exception"/>"
			method="post"><!-- .do 처음으로 뺌, 부트는 상관없음, 레거시는 .do로 설정시 필수 -->
				<label>나이</label>
				<input type="text" name="years"	class="form-control mx-2" />
				<input	type="submit" class="btn btn-danger mx-2" value="로그인" />
			</form>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>