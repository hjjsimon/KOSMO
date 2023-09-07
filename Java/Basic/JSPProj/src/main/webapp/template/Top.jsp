<%@page import="model.JWTOkens"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	//토큰기반 인증인 경우 유효한 토큰 여부 판단
	boolean isValidToken=JWTOkens.verifyToken(
			JWTOkens.getToken(request, application.getInitParameter("COOKIE-NAME")), "/resources/tokens", "secret-key");

%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
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
</head>
<body>
	<!-- 네비게이션 바  -->
	<!--상단 고정-->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">   
      <!--Brand / Logo 표시-->      
      <a class="navbar-brand" href="${pageContext.servletContext.contextPath }"><i class="fa-solid fa-house-chimney"></i></a>
      <!-- JSPProj 프로젝트 실행과 동일하게 링크 걸어줌, 아래 request.getContextPath()와 동일한 것, index.jsp로 이동하므로 자료실 나옴, 모델2방식 -->
      <!-- Navbar text-->
      <span class="navbar-text">
        솔로를 위한 플랫폼
      </span>
      
      <!-- Toggler/collapsibe Button -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
       
        <!-- Links -->
        <ul class="navbar-nav mr-3"><!--mr-3은 Navbar text와의 여백용-->
        <% if(session.getAttribute("USER-ID")==null){ %>
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath() %>/session06/Login.jsp">로그인(세션) <i class="fa fa-user-o"></i></a>
          </li>
        <%}else{ %>
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath() %>/session06/Logout.jsp">로그아웃(세션) <i class="fa fa-user-plus"></i></a>
          </li>
         <%}%>
          <% if(!isValidToken){ %>
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath() %>/session06/LoginBasedToken.jsp">로그인(토큰) <i class='fas fa-sign-in-alt'></i></a>
          </li>
         <%}else{ %>
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath() %>/session06/LogoutBasedToken.jsp">로그아웃(토큰) <i class='fas fa-sign-out-alt'></i></a>
          </li>
          <%} %>
          <li class="nav-item">
            <a class="nav-link " href="#">마이페이지</a>
          </li>
          <li class="nav-item">
            <a class="nav-link " href="<%=request.getContextPath() %>/bbs08/List.jsp">게시판</a>
          </li>   
          <li class="nav-item">
            <a class="nav-link " href="<c:url value="/DataRoom/List.kosmo"/>">자료실</a>
          </li>    
          
        </ul>
      </div>
    </nav>