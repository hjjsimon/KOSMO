<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css">
    <script src="<%=request.getContextPath() %>/bootstrap/js/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.bundle.min.js"></script> 
    
    <script src="https://kit.fontawesome.com/16e1907a6b.js" crossorigin="anonymous"></script>
    <title>RequestForHeader.jsp</title>
    <style>
        /*점보트론 세로폭 및 마진바텀 줄이기*/
        .jumbotron{
            padding-top: 1rem;
            padding-bottom: 1rem;
            margin-bottom: .5rem;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>
<body>
	<!-- 네비게이션바 -->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
        <a class="navbar-brand" href="#"><i class="fa-solid fa-house-chimney"></i></a>
		<span class="navbar-text">모두를 위한 플랫폼</span>        
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
            <ul class="navbar-nav mr-3">
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Dropdown link</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Link 1</a>
                        <a class="dropdown-item" href="#">Link 2</a>
                        <a class="dropdown-item" href="#">Link 3</a>
                    </div>
                </li>
                <li class="nav-item">
                    <form class="form-inline" action="#">
                        <input class="form-control mr-sm-2" type="text" placeholder="검색어 입력">
                        <button class="btn btn-success" type="submit">검색</button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container" style="margin-top:50px;">
        <div class="jumbotron bg-info">
            <h1>HttpServletRequest : 요청헤더 정보</h1>
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">요청헤더명 : 요청헤더값</legend>
        	<ul class="list-group">
        		<%
        			//request객체의 메소드]
        			//1]요청 헤더명 얻기: getHeaderNames()
					//2]헤더명에 따른 헤더값: getHeader(헤더명)
					Enumeration<String> names = request.getHeaderNames();
					while(names.hasMoreElements()){
						//헤더명 얻기
						String headerName = names.nextElement();
						//헤더값 얻기
						String headerValue = request.getHeader(headerName);
        		%>
        		<li calss="list-group-item"><%=headerName %> : <%=headerValue %></li>
        		<% }//while %>
        	</ul>
        	
        	<span class="lead"><%=request.getHeader("user-agent").toUpperCase().indexOf("FIREFOX") != -1 ? "파이어폭스 브라우저" :
        		request.getHeader("user-agent").toUpperCase().indexOf("EDG") != -1 ? "엣지 브라우저" : "파이어폭스도 엣지도 아니다" %></span>
        	<!-- 지금까지 요청에 대한 서버정보, 요청에 대한 클라이언트정보, 헤더 들을 가져옴
        		HttpServletRequest 추상화 된 request 객체를 사용함 -->
        </fieldset>
        
    </div><!--container-->
</body>
</html>