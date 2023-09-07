<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>File Upload/Download</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">업로드된 파일 목록</legend>
			<ul class="list-unstyle">
				<c:forEach var="file" items="${files}">
	 	        	<li><a href="<c:url value="/FileUpDown/FileDownload?filename=${file.name }"/>">파일명 :${file.name}, 파일크기 :${fn:replace(Math.ceil(file.length()/1024),'.0','' )}KB</a></li>
				</c:forEach>
        	</ul>
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>