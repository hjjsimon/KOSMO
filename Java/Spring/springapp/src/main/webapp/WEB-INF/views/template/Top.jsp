<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %><!-- 시큐리티 추가 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- webjars로 변경 하면 이거
	<link rel="stylesheet" href="/webjars/bootstrap/4.6.2/css/bootstrap.min.css">-->
	<!-- C:\Users\kosmo\.m2\repository\org\webjars -> resources가 루트, webjars부터 쓰면 됨 -->
	

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script><!-- jquery.slim.min에서 slim뺌 -->
    <!-- Ajax.jsp때문에 잠깐 주석처리 
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/0b4621b427.js" crossorigin="anonymous"></script>
    <title></title>
    <style>
        /*점보트론 세로폭 및 마진바툼 줄이기*/
        .jumbotron{
            padding-top:1rem;
            padding-bottom:1rem;            
            margin-bottom: .5rem;
            
            border-top-left-radius:0;
            border-top-right-radius:0;
        }
    </style>
    <script>
    	//시큐리티 사용 - csrf를 활성화 시킬 때
    	//POST로 토큰 전달해야함, 이렇게 하면 로그아웃 처리 완료 
    	function logout(){
    		$('#logoufForm').submit();
    	}
    
    </script>
</head>
<body>
	<!-- 시큐리티 csrf적용: 로그아웃 GET방식에서 POST방식으로 변경하기 위한 폼태그 추가 -->
	<form id="logoutForm" action="<c:url value="/onememo/auth/Logout.do"/>" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

	<!-- 네비게이션 바  -->
	<!--상단 고정-->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">   
      <!--Brand / Logo 표시-->      
      <a class="navbar-brand" href="<c:url value="/"/>"><i class="fa-solid fa-house-chimney"></i></a>
      <!-- JSPProj 프로젝트 실행과 동일하게 링크 걸어줌, 아래 request.getContextPath()와 동일한 것, index.jsp로 이동하므로 자료실 나옴, 모델2방식 -->
      <!-- Navbar text-->
      <span class="navbar-text">
        모두를 위한 플랫폼
      </span>
      
      <!-- Toggler/collapsibe Button -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
       
        <!-- Links -->
        <ul class="navbar-nav mr-3">
        	<%--시큐리티로 주석
	    	<c:if test="${empty sessionScope.id }" var="isLogin">
	    	--%>
	    	<sec:authorize access="isAnonymous()"><!-- 시큐리티 -->
	        	<li class="nav-item">
	            	<a class="nav-link active" href="<c:url value="/onememo/auth/Login.do"/>">로그인<i class="fa fa-user-o"></i></a>
	         	</li>
	        </sec:authorize>
	        <%--
	        </c:if>
	        <c:if test="${! isLogin }">
	        --%>
	        <sec:authorize access="isAuthenticated()">
	        	<li class="nav-item">
	        		<!-- csrf 미적용시, 주석확인, POST는 csrf가 꼭 들어가야함, 
	            	<a class="nav-link active" href="<c:url value="/onememo/auth/Logout.do"/>">로그아웃<i class="fa fa-user-plus"></i></a>
	        		-->
	        		<!-- csrf 적용시 -->
    			    <a class="nav-link active" href="<c:url value="/onememo/auth/Logout.do"/>">로그아웃<i class="fa fa-user-plus"></i></a>        		
	        	</li>
	        	
	        	
	        </sec:authorize>
	        <%-- 
	        </c:if>
	        --%>
            <li class="nav-item">
            	<a class="nav-link " href="#">마이페이지<i class='fas fa-user-lock'></i></a>
            </li>
        	<li class="nav-item">
        		<a class="nav-link " href="<c:url value="/onememo/bbs/List.do"/>">게시판<i class='fas fa-edit'></i></a>
        	</li>   
        </ul>
      </div>
    </nav>