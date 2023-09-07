<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>Controller</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">스프링 프레임워크</legend>
        	<ul class="list-unstyled">
        		<li>올린이 :${writer }</li><!-- FileUpDownController.java에서 포워드로 받아옴 -->
        		<li>제목 :${title }</li>
        	</ul>
        	<h3>업로드한 파일들</h3>
        	<c:forEach var="file" items="${uploadFiles}"><!-- uploadFiles에서 가져와 하나씩 file -->
	        	<ul class="list-unstyled">
	        		<li>파일명 :${file.name}</li><!-- getName 있었으니 그냥 name -->
	        		<li>파일크기 :${fn:replace(Math.ceil(file.length()/1024),'.0','' )}KB</li>
	        		<!-- taglib에서 fn 가져와서 replace 사용, .0 나오는걸 빈문자열로 바꿈->
	        		<!-- 아까 만든 upload 폴더에 파일 들어감
	        		D:\HJJ\Workspace\Java\Spring\springapp\src\main\webapp\upload -->
	        	</ul>
        	</c:forEach>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>