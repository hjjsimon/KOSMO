<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!--
	컨트롤러 메소드 작성 규칙]
	
	 접근지정자 : public
	 반환타입 : 주로 String(뷰정보를 문자열로 반환)
	 메소드명: 임의
	 인자 : 원하는 타입을 사용할 수 있다(단, 사용할 수 있는 타입이 정해져 있다)
	       어노테이션도 가능
	 예외를 throws할 수 있다(선택)
 -->
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>Controller</small></h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">컨트롤러 <span class="small bg-danger text-white">${requestScope.message }</span></legend>
        	<h3>하나의 컨트롤러 클래스,하나의 컨트롤러 메소드로 여러 요청하기1(패스 파라미터로 구분)</h3>
	    	<ul class="list-unstyled">
	    		<li><a href="<c:url value="/Controller/OneClass/List.do"/>">목록요청</a></li>
	    		<li><a href="<c:url value="/Controller/OneClass/Edit.do"/>">수정요청</a></li>
	    		<li><a href="<c:url value="/Controller/OneClass/Delete.do"/>">삭제요청</a></li>
	    		<li><a href="<c:url value="/Controller/OneClass/View.do"/>">상세요청</a></li>
	    	
	    	</ul>
	    	<h3>하나의 컨트롤러 클래스,하나의 컨트롤러 메소드로 여러 요청하기2(쿼리 스트링으로 구분)</h3>
	    	<ul class="list-unstyled"><!-- 패스파라미터가 /crud.do로 동일, 쿼리스트링이 다름 -->
	    		<li><a href="<c:url value="/Controller/OneClassParam/Crud.do?crud=list"/>">목록요청</a></li>
	    		<li><a href="<c:url value="/Controller/OneClassParam/Crud.do?crud=edit"/>">수정요청</a></li>
	    		<li><a href="<c:url value="/Controller/OneClassParam/Crud.do?crud=delete"/>">삭제요청</a></li>
	    		<li><a href="<c:url value="/Controller/OneClassParam/Crud.do?crud=view"/>">상세요청</a></li>
	    	</ul>
	    	
	    	<h3>하나의 컨트롤러 클래스,여러개의 컨트롤러 메소드로 여러 요청하기</h3>
	    	<ul class="list-unstyled">
	    		<li><a href="<c:url value="/Controller/MultiMethod/List.do"/>">목록요청</a></li>
	    		<li><a href="<c:url value="/Controller/MultiMethod/Edit.do"/>">수정요청</a></li>
	    		<li><a href="<c:url value="/Controller/MultiMethod/Delete.do"/>">삭제요청</a></li>
	    		<li><a href="<c:url value="/Controller/MultiMethod/View.do"/>">상세요청</a></li>
	    	</ul>
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>
