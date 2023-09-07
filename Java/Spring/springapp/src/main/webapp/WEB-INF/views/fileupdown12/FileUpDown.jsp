<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>File Upload/Download</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">파일 업로드/다운로드</legend>
        	<a class="btn btn-info" href="<c:url value="/FileUpDown/FileList"/>">목록으로 가기</a>
			<hr/>
			${maxUploadSizeError}
			<form action="<c:url value="/FileUpDown/FileUpload"/>"
				method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label><kbd class="lead">올린이</kbd></label> <input type="text"
						 class="form-control" name="writer" value="${param.writer }"
						>
				</div>
				<div class="form-group">
					<label><kbd class="lead">제목</kbd></label> <input type="text"
						 class="form-control" name="title"
						>
				</div>
				<div class="form-group">
					<label><kbd class="lead">첨부파일</kbd></label> <input type="file"
						class="form-control-file border" name="files" multiple
						>
				</div>
				<button type="submit" class="btn btn-primary">업로드</button>
			</form>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>