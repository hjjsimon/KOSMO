<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>한줄 댓글-등록 페이지</small></h1> 
        </div><!--jumbotron-->
        <c:if test="${! empty InputError}"><!-- InputError 비어있지 않으면 뿌려줌 -->
	        <div class="alert alert-success alert-dismissible fade show">
			  	<button type="button" class="close" data-dismiss="alert">&times;</button>
			  	<strong>Failure!</strong> ${InputError}
			</div>
		</c:if>
		<form method="post" action="<c:url value="/onememo/bbs/Write.do"/>">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	      	<div class="form-group">
		        <label><kbd class="lead">제목</kbd></label>
		        <input type="text" value="${param.title}" class="form-control" placeholder="제목을 입력하세요" name="title">
		        <!-- param으로 넘기는 이유는 입력 문제시 그냥 값 냅두려고 -->
	      	</div>
	     	<div class="form-group">
				<label><kbd class="lead">내용</kbd></label>
				<textarea class="form-control" rows="5" name="content">${param.content}</textarea>
		  	</div>
	    	<button type="submit" class="btn btn-primary">등록</button>
	    </form>
		
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>