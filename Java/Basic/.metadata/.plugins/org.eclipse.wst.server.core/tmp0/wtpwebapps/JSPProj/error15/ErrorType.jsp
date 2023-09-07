<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>웹 어플리케이션 예외처리</h1><!-- 프로젝트 완료 후 사용자에게 보여주기 전에 빨간줄 가면 안되니까 처리 -->    
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">예외클래스로 예외처리</legend><!-- 이게 ErrorCode.jsp보다 세부적이므로 우선함 -->
        	<h1 class="display-4">관리자에게 문의하세요</h1>
        	<h1 class="display-1">REQUEST SCOPE:${reqVar }</h1>
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>