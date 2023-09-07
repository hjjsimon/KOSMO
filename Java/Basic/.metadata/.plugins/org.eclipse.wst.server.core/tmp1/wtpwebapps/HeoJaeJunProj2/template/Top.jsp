<%--@page import="model.JWTOkens"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" --%>    
<%--
	//토큰기반 인증인 경우 유효한 토큰 여부 판단
	boolean isValidToken=JWTOkens.verifyToken(
			JWTOkens.getToken(request, application.getInitParameter("COOKIE-NAME")), "/resources/tokens", "secret-key");

--%>
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

		html {
		  height: 100%;
		}
		body {
		  margin:0;
		  padding:0;
		  font-family: sans-serif;
		  background: linear-gradient(#141e30, #243b55);
		}
		
		.login-box {
		  position: absolute;
		  top: 50%;
		  left: 50%;
		  width: 400px;
		  padding: 40px;
		  transform: translate(-50%, -50%);
		  background: rgba(0,0,0,.5);
		  box-sizing: border-box;
		  box-shadow: 0 15px 25px rgba(0,0,0,.6);
		  border-radius: 10px;
		}
		
		.login-box h2 {
		  margin: 0 0 30px;
		  padding: 0;
		  color: #fff;
		  text-align: center;
		}
		
		.login-box .user-box {
		  position: relative;
		}
		
		.login-box .user-box input {
		  width: 100%;
		  padding: 10px 0;
		  font-size: 16px;
		  color: #fff;
		  margin-bottom: 30px;
		  border: none;
		  border-bottom: 1px solid #fff;
		  outline: none;
		  background: transparent;
		}
		.login-box .user-box label {
		  position: absolute;
		  top:0;
		  left: 0;
		  padding: 10px 0;
		  font-size: 16px;
		  color: #fff;
		  pointer-events: none;
		  transition: .5s;
		}
		
		.login-box .user-box input:focus ~ label,
		.login-box .user-box input:valid ~ label {
		  top: -20px;
		  left: 0;
		  color: #03e9f4;
		  font-size: 12px;
		}
		
		.login-box form a {
		  position: relative;
		  display: inline-block;
		  padding: 10px 20px;
		  color: #03e9f4;
		  font-size: 16px;
		  text-decoration: none;
		  text-transform: uppercase;
		  overflow: hidden;
		  transition: .5s;
		  margin-top: 40px;
		  letter-spacing: 4px
		}
		
		.login-box a:hover {
		  background: #03e9f4;
		  color: #fff;
		  border-radius: 5px;
		  box-shadow: 0 0 5px #03e9f4,
		              0 0 25px #03e9f4,
		              0 0 50px #03e9f4,
		              0 0 100px #03e9f4;
		}
		
		.login-box a span {
		  position: absolute;
		  display: block;
		}
		
		.login-box a span:nth-child(1) {
		  top: 0;
		  left: -100%;
		  width: 100%;
		  height: 2px;
		  background: linear-gradient(90deg, transparent, #03e9f4);
		  animation: btn-anim1 1s linear infinite;
		}
		
		@keyframes btn-anim1 {
		  0% {
		    left: -100%;
		  }
		  50%,100% {
		    left: 100%;
		  }
		}
		
		.login-box a span:nth-child(2) {
		  top: -100%;
		  right: 0;
		  width: 2px;
		  height: 100%;
		  background: linear-gradient(180deg, transparent, #03e9f4);
		  animation: btn-anim2 1s linear infinite;
		  animation-delay: .25s
		}
		
		@keyframes btn-anim2 {
		  0% {
		    top: -100%;
		  }
		  50%,100% {
		    top: 100%;
		  }
		}
		
		.login-box a span:nth-child(3) {
		  bottom: 0;
		  right: -100%;
		  width: 100%;
		  height: 2px;
		  background: linear-gradient(270deg, transparent, #03e9f4);
		  animation: btn-anim3 1s linear infinite;
		  animation-delay: .5s
		}
		
		@keyframes btn-anim3 {
		  0% {
		    right: -100%;
		  }
		  50%,100% {
		    right: 100%;
		  }
		}
		
		.login-box a span:nth-child(4) {
		  bottom: -100%;
		  left: 0;
		  width: 2px;
		  height: 100%;
		  background: linear-gradient(360deg, transparent, #03e9f4);
		  animation: btn-anim4 1s linear infinite;
		  animation-delay: .75s
		}
		
		@keyframes btn-anim4 {
		  0% {
		    bottom: -100%;
		  }
		  50%,100% {
		    bottom: 100%;
		  }
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
        모두를 위한 플랫폼
      </span>
      
      <!-- Toggler/collapsibe Button -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
       
        <!-- Links -->
        <ul class="navbar-nav mr-3"><!--mr-3은 Navbar text와의 여백용-->
        <%-- if(session.getAttribute("USER-ID")==null){ --%>
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath() %>/session06/Login.jsp">로그인(세션) <i class="fa fa-user-o"></i></a>
          </li>
        <%--}else{ --%>
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath() %>/session06/Logout.jsp">로그아웃(세션) <i class="fa fa-user-plus"></i></a>
          </li>
         <%--}--%>
          <%-- if(!isValidToken){ --%>
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath() %>/session06/LoginBasedToken.jsp">로그인(토큰) <i class='fas fa-sign-in-alt'></i></a>
          </li>
         <%--}else{ --%>
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath() %>/session06/LogoutBasedToken.jsp">로그아웃(토큰) <i class='fas fa-sign-out-alt'></i></a>
          </li>
          <%--} --%>
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