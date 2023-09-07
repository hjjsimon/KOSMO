<%@page import="java.util.Calendar"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
    <script src="<%=request.getContextPath() %>/bootstrap/js/jquery.slim.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/0b4621b427.js" crossorigin="anonymous"></script>
    <title>DynamicPage.jsp</title>
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
            <h1>카테고리</h1>            
        </div><!--jumbotron-->
        
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">페이지를 선택해서 동적으로 삽입하기</legend>
        	<form>
	   			<div class="form-group">			
					<select name="pageName" class="custom-select mt-3 custom-select-lg">
						<option value="">페이지를 선택하세요</option>
						<option value="DirectivePage.jsp">DirectivePage.jsp</option>
						<option value="ActionTagPage.jsp">ActionTagPage.jsp</option>					
					</select>
				</div>
	   			<input type="submit" value="확인" class="btn btn-info"/>
	   		</form>
        	<%
        		String pageName = request.getParameter("pageName");//페이지명 받음
        		if(pageName != null && pageName.length()!=0){//선택안하거나, 0이아님
        		        	
        	%>
        		<jsp:include page="<%=pageName %>"/><!-- 동적으로 삽입함 -->
        	<%} %>
        </fieldset> 	
       	<fieldset class="form-group border p-3">
       		<legend class="w-auto px-3">계절에 따른 페이지 변환</legend>
       		<%
       			// 3~5 봄, 6~8 여름, 9~11 가을, 12~2 겨울
       			String season="Spring.jsp";
       			/*
       			내코드
       			LocalDate today = LocalDate.now();
       			int middleNumber = today.getMonthValue();
       			if(middleNumber >=3 && middleNumber <=5) season = "Spring.jsp";
       			else if(middleNumber >=6 && middleNumber <=8) season="Summer.jsp";
       			else if (middleNumber >=9 && middleNumber <=11) season="Autumn.jsp";
       			else season="Winter.jsp";
       			*/
       			//선생님, 월만 뽑아옴
       			Calendar cal = Calendar.getInstance();
       			switch(cal.get(Calendar.MONTH)+1){//0이 1월이라 +1 해줘야함
	       			case 3:
	       			case 4:
	       			case 5: season="Spring.jsp";break;
	       			case 6:
	       			case 7:
	       			case 8: season="Summer.jsp";break;
	       			case 9:
	       			case 10:
	       			case 11: season="Autumn.jsp";break;
	       			default: season="Winter.jsp";break;
       			}
       			
       		%>
       		<jsp:include page="<%=season %>"/>
       		<%--=today --%> <!-- 2023-05-18 -->
       		<%--=middleNumber --%>
       		
       	</fieldset>
        
    </div><!--container-->
    
    <footer class="page-footer font-small blue">
		<!-- Copyright -->
		<div class="footer-copyright text-center py-3">
			© 2023 (주)한국소프트웨어아이엔씨 (153-759) 서울시 금천구 가산동 426-5 월드메르디앙 2차 413호 
			<a href="<%=request.getContextPath()%>"> ikosmo.co.kr</a>
			<!-- <a href="/JSPProj"> ikosmo.co.kr</a> 으로 들어감 -->
			<!-- web.xml 맨 위에 있는 <welcome-file>HelloWorld.jsp</welcome-file>로 이동함 -->
		</div>
		<!-- Copyright -->
 	</footer>
 	
</body>
</html>