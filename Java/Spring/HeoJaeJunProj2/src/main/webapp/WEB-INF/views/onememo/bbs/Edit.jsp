<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="login-wrap">
  	<div class="login-html">
    <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
    	<label for="tab-1" class="tab">게시판<small> 수정 페이지</small></label>
   	<input id="tab-2" type="radio" name="tab" class="sign-up">
    	<label for="tab-2" class="tab" style="float: right; font-size: 18px;">
    	<a class="no-hover" href="<c:url value="/onememo/auth/List.do"/>"><small><b>목록 페이지로 이동</b></small></a></label>
    
       <c:if test="${! empty InputError}"><!-- InputError 비어있지 않으면 뿌려줌 -->
	        <div class="alert alert-success alert-dismissible fade show">
			  	<button type="button" class="close" data-dismiss="alert">&times;</button>
			  	<strong>Failure!</strong> ${InputError}
			</div>
		</c:if>
		<!-- 이거 바꿔야지 -->
		<form method="post" action="<c:url value="/onememo/bbs/Edit.do"/>">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			
			<!-- no 넘겨야 받지!!!!!!!!!!!!!!!!!!!!!! -->
			<input type="hidden" name="no" value="${empty record.no? param.no : record.no }"/>
			
			<!-- 
				※ 매우매우매우매우매우매우매우매우매우매우매우매우매우 중요합니다
				1) 처음 수정폼 들어가면 record.에서 읽어옴 (param.은 없으니 그냥 출력 안되고 끝, 이게 EL문의 장점)
				2) 수정했는데 오류시 forward된거라 파라미터 그대로 넘어와 param.으로 읽어옴
			-->
			
	      	<div class="form-group mt-3">
		        <label><kbd class="lead">제목</kbd></label>
		        <input type="text" value="${record.title}${param.title}" class="form-control" placeholder="제목을 입력하세요" name="title">
	      	</div>
	     	<div class="form-group">
				<label><kbd class="lead">내용</kbd></label>
				<textarea class="form-control" rows="5" name="content">${record.content}${param.content}</textarea>
		  	</div>
	    	<button type="submit" class="btn btn-primary">수정</button>
	    </form>
	</div>
    </div>
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>