<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="login-wrap">
  	<div class="login-html">
    <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
    	<label for="tab-1" class="tab">게시판<small> 작성 페이지</small></label>
   	<input id="tab-2" type="radio" name="tab" class="sign-up">
    	<label for="tab-2" class="tab" style="float: right; font-size: 18px;">
    	<a class="no-hover" href="<c:url value="/onememo/auth/List.do"/>"><small><b>목록 페이지로 이동</b></small></a></label>
    
        <c:if test="${! empty InputError}"><!-- InputError 비어있지 않으면 뿌려줌 -->
	        <div class="alert alert-success alert-dismissible fade show">
			  	<button type="button" class="close" data-dismiss="alert">&times;</button>
			  	<strong>Failure!</strong> ${InputError}
			</div>
		</c:if>
		<form method="post" action="<c:url value="/onememo/bbs/Write.do"/>">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	      	<div class="form-group mt-3">
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
		</div>
    </div>
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>