<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 시큐리티 추가 태그 -->
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
           <h1>Spring Framework <small>로그인</small></h1> 
           
           <!-- 시큐리티에서 추가, 사용자 권한 출력 -->
           <c:set var="authorities"><!-- 로그인한 사용자의 정보가 이 변수에 담김 -->
           		<sec:authentication property="authorities"/>
           </c:set>
           <c:forEach var="authority" items="${authorities }">
           		${authority }<br/>
           </c:forEach>
           
           
           
        </div><!--jumbotron-->
        <!-- 로그인 실패시(아이디와 비번 불일치) -->
        <c:if test="${! empty NotMember }">
	        <div class="alert alert-success alert-dismissible fade show">
			   <button type="button" class="close" data-dismiss="alert">&times;</button>
			   <strong>Failure!</strong> ${requestScope.NotMember}
			</div>
		</c:if>
		<!--  로그인 전 -->
		<!-- 시큐리티시 세션에 저장한거 없어서 안먹음 < % 써서 주석처리 -->
		<%--
		<c:if test="${ empty sessionScope.id }" var="isLogin">
		--%>
		<sec:authorize access="isAnonymous()"><!-- 시큐리티 사용, 익명이냐()? 로그인 폼 보여줌 -->
			<form class="form-inline" action="<c:url value="/onememo/auth/LoginProcess.do"/>" method="post">				
				<input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" />
				<label>아이디</label> 
				<input type="text" name="id" class="form-control mx-2" /> 
				<label>비밀번호</label> 
				<input type="password" name="pwd" class="form-control mx-2" /> 
				<input type="submit" class="btn btn-danger mx-2" value="로그인" />
			</form>
		</sec:authorize>
		<%--
		</c:if>
		--%>
		<!-- 로그인 후 -->
		<%--
		<c:if test="${not isLogin }">
		--%>
		<sec:authorize access="isAuthenticated()"><!--시큐리티, 인증됐냐()? --> 
			<div class="alert alert-success">
			 	<button type="button" class="close" data-dismiss="alert">&times;</button>
			 	<strong>Success!</strong>
			 	${sessionScope.id}
			 	<sec:authentication property="principal.username"/><!-- 시큐리티! -->
			 	님 즐감하세요!<!-- 세션영역에서 id로 읽어옴 -->
			</div>
		</sec:authorize>
		<%--
		</c:if> 
		--%>
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>