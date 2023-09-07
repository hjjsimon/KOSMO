<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	-application영역에 저장된 속성은 모든 JSP페이지(서블릿)에서 공유한다.
	-링크로 페이지 이동하든 리다이렉트나 포워드로 자동 이동하든 모든 JSP페이지는
	 하나의 application영역안에 있기때문에.....
	*/
	//[어플리케이션 영역에 속성저장]
	//저장할 객체 생성
	MemberDTO first = new MemberDTO("KIM","1234","김길동","20");
	MemberDTO second = new MemberDTO("LEE","5678","이길동","10");
	MemberDTO third = new MemberDTO("PARK","9999","박길동","26");
	//리스트 계열 컬렉션에 저장
	List<MemberDTO> list = Arrays.asList(first,second,third);//first, second, third를 하나씩 저장
	//맵 계열 컬렉션에 저장
	Map<String,MemberDTO> map = new HashMap<>();
	map.put("first",first);//키,값
	map.put("second",second);
	map.put("third",third);
	//application영역에 컬렉션들 저장(현재 페이지 요청해야 저장됨)/ 파라미터에 저장한거x, application에 저장한거 넘김
	application.setAttribute("list", list);//키,값
	application.setAttribute("map", map);
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
    <title>ApplicationScopeIndex.jsp</title>
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
            <h1>application 영역</h1>            
        </div><!--jumbotron-->
        <h2>사용자 클릭으로 페이지 이동</h2>
        <a href="ApplicationScopeResult.jsp">클릭하세요</a>
        <!-- 클릭으로 새로운 요청을 보낸건데도 application영역에 저장한거라 모든 서블릿에서 정보 공유함 -->
        <!-- 따라서 application영역에 저장시에 포워드, 리다이렉트 무관하게 다 정보 뜨게 됨 -->
        
        <h2>포워드로 페이지 자동 이동</h2>
        <%
	        /*
	        	※절대 경로로 지정시]			
		   	 	포워드 방식:컨텍스트 루트 제외(자동으로 포함됨)
		   	    리다이렉트 방식:컨텍스트 루트 반드시 포함(단,server.xml에서 Context태그의 path속성을 빈문자열로 지정시에는 신경 안써도 된다.)		   	   
		   	*/
        	//request.getRequestDispatcher("/builtinobjectscope04/ApplicationScopeResult.jsp").forward(request, response);
		   	//요청과 요청에 관한 응답을 그대로 보내줌
		   	//자동이동이라 바로 Result.jsp로 이동, 단 url은 Index.jsp
        %>
        <h2>리다이렉트로 페이지 자동 이동</h2>
        <%
        	response.sendRedirect(request.getContextPath()+"/builtinobjectscope04/ApplicationScopeResult.jsp");
        	//response.sendRedirect("/JSPProj/builtinobjectscope04/ApplicationScopeResult.jsp");//둘 다 됨
        	//url은 Result.jsp
        	
        %>
        
        
        
    </div><!--container-->
</body>
</html>