<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	//선언부에서 물리적경로 얻는 방법들]
	//1]this키워드 사용
	private String getRealPath(){
		return this.getServletContext().getRealPath("/images");
	}
	//2]매개변수로 내장객체를 전달받으면 됨
	private String getRealPath(ServletContext app){//request받아도 됨
		return app.getRealPath("/images");
	}
	//3]멤버변수 사용
	private ServletContext app;
	private String getRealPath_(){
		return app.getRealPath("/images");//app는 두줄 위 코드, null임, 초기화 해줘야함
	}

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
    <title>ApplicationForMajorMethod_1.jsp</title>
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
            <h1>application 내장객체</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">주요 메소드</legend>
        	<h2>컨텍스트 초기화 파라미터 얻기: getInitParameter("파라미터명")</h2>
        	<ul class="list-unstyled">
        		<!-- web.xml에 파라미터명,값 추가하고옴 -->
        		<!-- 
        			컨텍스트 초기화 파라미터
        			목적] 웹어플리케이션(컨텍스트)을 구성하는 모든 jsp(서블릿)에서 스트링형 상수를 공유하기 위함
        			step1] web.xml에 컨텍스트 초기화 파라미터 등록
        			step2] ServletContext의 getInitParameter("파라미터명") 메소드로 얻기
        		 -->
        		<li>오라클 드라이버:<%=application.getInitParameter("ORACLE-DRIVER") %></li>
        		<li>오라클 주소:<%=application.getInitParameter("ORACLE-URL") %></li>
        		<!-- this.getInitParameter("파라미터명")는 서블릿 초기화 파라미터 
        			이건 해당 서블릿(해당 페이지)에서만 접근가능, 다른 페이지에서는 접근불가(위의 컨텍스트 초기화 중 공유랑 다른 것)-->
        		<li>this.getInitParameter("파라미터명"):<%=this.getInitParameter("ORACLE-DRIVER") %></li><!-- null -->
        		<!-- this는 현재 페이지(클래스)를 의미, 현재 페이지는 서블릿, 서블릿 초기화 파라미터 
        			근데 지금 jsp잖아 서블릿 아니고, jsp에서 초기화하는방법다 있음-->
        		<li>this.getInitParameter("파라미터명"):<%=this.getInitParameter("SERVLET-INIT-PARAMETER") %></li>
        	</ul>
        	<h2>모든 컨텍스트 초기화 파라미터명 얻기: getInitParameterNames();</h2>
       		<ul class="list-unstyled">
       			<% 
       				Enumeration<String> names = application.getInitParameterNames();
       				while(names.hasMoreElements()){
       					//컨텍스트 초기화 파라미터명 얻기
       					String paramName = names.nextElement();
       					//컨텍스트 초기화 파라미터값 얻기
       					String paramValue = application.getInitParameter(paramName);
       			%>
       			<li><%=paramName %> : <%=paramValue %></li>
       			<%}//while %>
       		</ul>
       		<h2>서버의 물리적 경로 얻기(서버에 파일업로드,다운로드시 사용):getRealPath("/로 시작하는 웹상의 경로")</h2>
       		<!-- webapp아래 /builtinobject03 이런 식으로 시작 -->
       		<!-- ServletContext의 메소드인 getRealPath(/로 시작하는 웹상의 경로)로 얻는다
       			웹상의 경로는 Context 루트를 제외한 /로 시작 -->
       		<ul class="list-unstyled">
       			<li>application내장객체:<%=application.getRealPath("/images") %></li>
       			<!-- 아래가 물리적경로 
       			D:\HJJ\Workspace\Java\Basic\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPProj\images -->
       			<li>request내장객체(주로 내가 만든 서블릿(web.xml에 등록한거)에서 사용): <%=request.getServletContext().getRealPath("/images") %></li>
       			<!-- 만든 서블릿은 클래스임 .jsp 아니라 application 사용 불가, getRealPath 불가 
       				서블릿에서 req 인자로 받음, req.으로 application 받아와서 getRealPath 해야함 
       				jsp는 application 지역변수로 선언돼있어서 바로 쓸 수 있음
       				.java는 req라는 이름이 request를 가져오는 방법임-->
       			<!-- 물리적경로 얻기위함, Servlet 요청은 실행중인 어플리케이션에 보냄 -->
       			<li>this키워드(주로 선언부나 내가 만든 서블릿에서 사용): <%=this.getServletContext().getRealPath("/images") %></li><!-- 이것도 클래스(.java)에서 사용가능, req처럼 -->
      		</ul>
    		<h3>선언부(내장객체 사용불가, 지역변수)에서 물리적 경로 얻기</h3>   		
        	<ul class="list-unstyled">
        		<li>this키워드 사용:<%=getRealPath() %></li>
        		<li>매개변수로 전달:<%=getRealPath(application) %></li>
        		<%
        			this.app = application; //초기화함
        		%>
        		<li>멤버변수 사용:<%=getRealPath_() %></li>
        	
        	</ul>
        	
        	
        </fieldset>  
    </div><!--container-->
</body>
</html>