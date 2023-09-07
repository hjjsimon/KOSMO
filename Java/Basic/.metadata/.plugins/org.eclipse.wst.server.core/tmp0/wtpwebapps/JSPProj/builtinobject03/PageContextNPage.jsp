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
    <title>PageContextNPage.jsp</title>
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
            <h1>pageContext 및 page 내장객체</h1>            
        </div><!--jumbotron-->
        <!-- 웹어플리케이션이 실행되는 전체영역이 context, context라는 개념을 추상화한게 ServletContext, 
        	jsp에서는 application(지역변수로 서비스 안에 선언돼어있음)으로 사용
        	application 1개당 객체 1개씩 생김, 서버 스타트 시에 웹어플리케이션 가동시킴, 
        	그러면 JSPProj실행, 이 때 ServletContext객체 1개 생김, 서버 끄면 객체 사라짐
        	
        	request는 요청 1개당 객체 1개씩 만들어줌(한 사람이 요청 수없이 많음, 객체 엄청 많이 생김, 응답하는 순간 사라지게함, 서버에 안쌓임) 
        	
        	pageContext내장객체는 jsp에서는 잘 안씀 나중에 씀, jsp페이지에 대한 정보저장, 모든 내장객체를 얻어올 수 있음
        	request, response, out, session, application 얻어오는 get 메소드들이 있음 
        	jsp에서 그냥 내장객체 얻으면 돼서 이렇게 얻어올 일은 없음
        	
        	page내장객체는 Obeject타입 A.jsp -> A_jsp.java 이런식, 이 때 jsp자체를 나타냄 그래서 모두 담도록 object, 
        	즉 this키워드(현재 클래스 의미)랑 동일
        	
        	서블릿 하나에 대한 정보를 추상화한게 ServletConfig, 서블릿 하나당 하나씩 만들어짐
        	웹어플리케이션 만들때 수많은 jsp만들것, 그것도 다 서블릿 되므로 1개 생김
        	사용자 요청시 서블릿 뜸, 서버 끄면(웹어플리케이션 중지시) 서블릿이 메모리에서 내려감(서블릿 라이프사이클이라고함)
        	-->
        <h2>pageContext</h2>
        <h4>pageContext 내장객체로 전송방식 얻기</h4>
        <%
        	//출력시 사용하는 언어인 EL에서 pageContext(EL용 내장객체)를 주로 사용한다
        	//1]pageContext로 request객체 얻기
			//원래 request는 HttpServletRequest타입, < ServletRequest가 부모
			ServletRequest req = pageContext.getRequest();
        	//getMethod()는 HTTPServletRequest에서 새롭게 정의한 메소드라 현재 req에서 바로 안뜸, 바꿔줘야함
        	String method = ((HttpServletRequest)req).getMethod();
        	out.println(method);//GET 출력
        %>
        <h4>request내장객체로 전송방식 얻기</h4>
        <%=request.getMethod() %><!-- 이렇게하지 위처럼 안함 -->
        <h4>pageContext내장객체로 출력하기</h4>
        <% pageContext.getOut().println("메소드 체인"); %>
        <h4>pageContext내장객체로 서버의 물리적경로 얻기</h4>
        <%=pageContext.getServletContext().getRealPath("/images") %>
        <!-- D:\HJJ\Workspace\Java\Basic\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPProj\images -->
        <!-- 페이지의 자원을 안씀, 메타데이터의 자원을 사용함, 위에 .metadata 나옴 -->
        <h2>page내장객체(현재페이지 의미, this와 동일)</h2>
        <%
        	//page내장객체(Object타입)는 jsp페이지 자체를 의미하고 this키워드와 같은 의미이다
        	out.println("page내장객체의 클래스명:"+page.getClass().getName()+"<br/>");
        	//반환타입 class, class란 class가 있는 것, 그리고 클래스명 가져옴(패키지 포함)
        	//org.apache.jsp.builtinobject03.PageContextNPage_jsp 출력
        	//PageContextNPage_jsp <-클래스명
        	out.println("this의 클래스명:"+this.getClass().getName()+"<br/>");
        	out.println(page instanceof HttpServlet ? "page는 서블릿이다" : "page는 서블릿이 아니다" );
        	// instanceof란? 우리는 종종 그림과 같이 부모를 상속해서 만들어진 자식 객체가 여러 타입인 경우에 특정 클래스가 맞는지 확인
        %>
        
        
        
        
        
        
               
    </div><!--container-->
</body>
</html>