<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <script src="https://kit.fontawesome.com/16e1907a6b.js" crossorigin="anonymous"></script>
    <title>RequestForServerNClient.jsp</title>
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
            <h1>HttpServletRequest : 요청한 서버와 클라이언트에 대한 정보</h1>
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">서버 및 클라이언트 정보</legend>
        	<form method="post"><!-- 이거 쓰면 post방식으로 가져옴 -->
        		<input class="btn btn-info" type="submit" value="서버로 전송"/>
        	</form>
        	<h3>HTTP 메소드: <small><%=request.getMethod() %></small></h3><!-- html태그로 전송시 get, post방식만 가능 -->
        	<!-- get방식: 메소드 지정x, get으로 요청 -->
        	<!-- 현재페이지를 서버에 요청, 현재 페이지 get방식 -->
        	<!-- getMethod()로 HTTP메소드 가져옴 GET 또는 POST로 대문자로 가져옴 -->
        	<h3>전체 URL: <small><%=request.getRequestURL() %></small></h3>
        	<h3>도메인을 제외한 URI(=자원의 위치, path): <small><%=request.getRequestURI() %></small></h3>
        	<h3>요청 프로토콜: <small><%=request.getProtocol() %></small></h3>
        	<h3>요청 서버명: <small><%=request.getServerName() %></small></h3>
        	<h3>요청 서버포트: <small><%=request.getServerPort() %></small></h3>
        	<!-- 
        		IPV4값으로 변환시
				톰캣 실행시의 JVM에 환경변수를 추가.
				이클립스 > Run > Run Configuration > Arguiments > -Djava.net.preferIPv4Stack=true				
				
				실제 톰캣 서버에서는 
				bin/catalina.bat 파일 수정 (169 라인 부근)
				set CLASSPATH= 를 찾아서 그 아래에 
				set CATALINA_OPTS=-Djava.net.preferIPv4Stack=true 라인을 추가.
        		
        	 -->
        	
        	<h3>클라이언트의 IP주소: <small><%=request.getRemoteAddr() %></small></h3><!-- local의 반대는 remote -->
        	<h3>클라이언트의 IP주소: <small><%=request.getRemoteHost() %></small></h3><!-- 네트워크에서는 컴퓨터x, host라고함 -->
        	<h3>요청과 관련된 세션객체 얻기(내가만든 서블릿에서 사용-인증처리): <small><%=request.getSession() %></small></h3>
        	<h3>세션 내장객체: <small><%=session %></small></h3>
        	<!-- jsp는 의미x jsp는 session이라는 내장객체 제공해줌, 내가 만든 서블릿에서 유의미 사용-로그인 등 인증처리 -->
        	<!-- jsp는 <%--=session --%> 하면 제공해주니까 굳이 위에처럼 얻어올 필요 없음 -->        	
        	<!-- 웹어플리케이션이 싫애되는 전체영역을 context(문맥)이라고 함 -> 여기에 root는 프로젝트 폴더명, 이 하위에 .jsp다 넣음 -->
        	<!-- server.xml에 들어가서 맨 밑 보면 <Context~~~>가 있음 -->
        	<!-- 이걸 추상화한게 servletcontext, getContextPath 하면 /JSPProj를 가져옴 -->
        	<!-- JSP에서는 웹어플리케이션 전체영역을 Context라 칭함
        			getContextPath(): server.xml의 Context태그에 path속성에 지정한 값을 얻어옴
        			※HTML에서 절대경로로 링크를 걸 때 주로 사용
        			(단, JSTL사용시에는 사용하지 않는다(모델2방식)) 
        			(빨리 어플리케이션 만드는 모델1방식에 쓴다는 뜻)-->
        	<h3>Context Path(프로젝트명,server.xml 맨 밑): <small><%=request.getContextPath() %></small></h3>
        	<!-- 요청은 요청헤더+요청바디, 응답은 응답헤더+응답바디로 감, 
        		각 헤더는 작업지시서임, 작업지시서인 응답헤더(브라우저가 읽음)대로 응답바디(F12에 Preview라고나옴)를 브라우저에 출력함 
        		요청헤더도 서버에 요청할 때 양식에 대한 요약본, 서버는 요청양식 대로 응답을 해줌
        		요청바디에는 실질적인 요청내용이 들어있음-->
        	
        </fieldset>
        
    </div><!--container-->
</body>
</html>