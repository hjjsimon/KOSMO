<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    %>
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
    <script src="<%=request.getContextPath() %>/bootstrap/js/jquery.slim.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/0b4621b427.js" crossorigin="anonymous"></script>
    <title>Out.jsp</title>
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
      <a class="navbar-brand" href="#"><i class="fa-solid fa-house-chimney"></i></a>
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
          <li class="nav-item">
            <a class="nav-link active" href="#">Link 1</a>
          </li>
          <li class="nav-item">
            <a class="nav-link " href="#">Link 2</a>
          </li>
          <!--Navbar With Dropdown-->
          <!--하단고정일때는 dropdown을 dropup으로-->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Link 3</a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="#">SubLink 1</a>
              <a class="dropdown-item" href="#">SubLink 2</a>
              <a class="dropdown-item" href="#">SubLink 3</a>
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
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Out내장객체</h1>            
        </div><!--jumbotron-->
        <h2>버퍼와 관련된 메소드</h2><!-- 서블릿 변환시 out.write됨, <!DOCTYPE html>부터 버퍼에 쓰임 -->
        <%
        	out.println("버퍼에 저장된 문자열<br/>");//바로 브라우저 저장x, 버퍼에 먼저 저장됨(버퍼 기본크기 8kb, 다 채우면 자동 flush)
        	out.flush();//버퍼에 저장된 내용이 웹브라우저로 바로 전송됨
        	out.println("웹브라우저에 출력 안될 문자열");
        	out.clearBuffer();//버퍼에 저장된 문자열 지움, 8kb전이라 출력 바로 x, 8kb넘으면 오토플러싱(기본값 true)으로 보냄
        	out.println("마지막으로 저장될 문자열");//이후 아래 코드 없으면 또 자동 플러싱
        %>        
        <h2>기타 메소드</h2>
        <ul class="list-unstyled">
        	<li>자동 플러쉬 여부:<%=out.isAutoFlush() %></li><!-- true출력 -->
        	<li>버퍼크기:<%=out.getBufferSize()/1024 %>KB</li><!-- 바이트단위로 출력, 1024 나누면 kb단위 출력 -->
        	<li>남은 버퍼크기:<%=out.getRemaining()/1024 %>KB</li><!-- 남은거 출력 -->
        </ul>
        <h2>print와 println 차이</h2>
        <h4>print메소드</h4>
        <%
        	out.print("<table class='table table-bordered'>");
        	out.print("<tr>");
        	out.print("<td>가</td><td>나</td>");
        	out.print("</tr>");
        	out.print("</table>");
        %>
        <h4>println메소드</h4><!-- print랑 출력결과는 동일, 소스보기 시 줄바꿈으로 가독성이 좋을 뿐 -->
        <%
        	out.println("<table class='table table-bordered'>");
        	out.println("<tr>");
        	out.println("<td>가</td><td>나</td>");
        	out.println("</tr>");
        	out.println("</table>");
        %>
        
        	
    </div><!--container-->
</body>
</html>