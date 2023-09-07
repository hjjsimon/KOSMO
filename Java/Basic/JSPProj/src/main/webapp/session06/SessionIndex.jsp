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
    <title>SessionIndex.jsp</title>
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
            <h1>세션</h1>            
        </div><!--jumbotron-->
       	<!-- 
       		세션이 삭제되는 경우?
       		1. 유효시간동안 서버에 아무런 요청을 보내지 않았을 때
       		2. 브라우저를 닫았을 때
       		-> 현재 브라우저 안에서 모든 JSP(서블릿)은 동일한 세션영역에 있다
       
       		세션도 클라이언트의 상태정보를 저장하기 위한 기술, 쿠키와 동일
       		쿠키는 클라이언트에 저장, 세션은 서버에 저장되어 안전
       		그러나 세션도 내부적으로 쿠키 사용함
       		세션도 쿠키처럼 요청, 응답 1회시 세션 끊어짐
       		요청시 내부적으로는 서버에서 쿠키를 만듦, 요청에 관한 request객체 만들어짐
       		그리고 session객체 1개 만들어지고 서버에 저장(request, application도 다 서버에 저장)
       		session마다 유니크한 ID값이 있음, 브라우저 1개당 1개 생김
       		사용자가 브라우저 열어 내 플랫폼(서버) 접속시 session객체 만들어짐
       		JSESSIONID 이걸로 쿠키를 만듦
       		다시 요청시 제이세션을 서버로 보내는 것, 요청헤더에 나옴, 서버가 아 또 요청 보냈구나 인지함
       		page<request<session<application영역
       		request보다 수명이 긺
       		목록페이지 -> 상세페이지 넘어가면 페이지 다 다름, 그래도 1개 브라우저 내에서 바뀌는거라 session영역은 1개
       		브라우저 닫으면 당연히 session종료됨, 서버에서 사라짐
       		
       		네이버 접속시 브라우저 유지하면 같은 세션객체라 유지, 계속 브라우징 하면서 페이지 넘어가도 유지
       		페이지 넘어가며 요청할 때마다 30분씩 연장이됨, 근데 켜놓고 요청없이 가만히 있으면 30분 후 세션객체 삭제(접속끊긴걸로 간주)
       		이후 요청시 새로운 세션객체가 만들어짐
       		ex.세션 끊어져서 로그인 날아갈 수 있음 
       		
       		최근에는 세션인증 보다 토큰기반인증을 많이 씀
       		세션객체가 서버에 저장, 노드밸런스가 앞쪽에 있대, 많이 부하 걸리는 서버가 있으면 안되니까 여기저기 뿌리게 조절해줌
       		근데 다른 서버에는 세션객체가 없으니까 로그인 안된걸로 알고있으니 로그인을 또 해야함(단점)
       		컴퓨터는 선으로 연결, 모바일은 무선, 그래서 세션이 할당되더라도 자주 끊김
       		토큰은 그냥 문자열임, 서버와 상태를 계속 유지할 필요가 없음
       		세션으로 상태를 유지할 필요 없이, 서버는 쿠키처럼 토큰을 발행하고, 토큰 받고.. 여튼 주고받는다~  
       		       		
       		
       	-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">session(HttpSession)내장 객체의 주요 메소드</legend>
        	<ul class="list-unstyled">
        		<li>세션의 유효시간(Servers폴더의 web.xml:분단위) : <%=session.getMaxInactiveInterval()/60 %>분</li>
        		<!-- 초단위로 반환, 60나눠서 분으로 바꾸기, 30분 출력 -->
        		<!-- Server의 web.xml값을 현재 프로젝트 WEB-INF의 web.xml에서 오버라이딩, 30분->1440분 -->
        		<%-- session.setMaxInactiveInterval(30); --%><!-- 이건 또 초단위임,설정바꾼게 아니고 자바코드로 바꾼것 -->
        		
				<li>세션 아이디: <a href="SessionResult.jsp"><%=session.getId() %></a></li>	
				<!-- result에서 getId()해도 같은 브라우저이므로 같은 세션아이디 출력됨 -->
			</ul>
        </fieldset>        
        <!-- 세션 및 리퀘스트 영역에 속성 저장 -->
        <%
	        request.setAttribute("requestScope", "리퀘스트 영역입니다");//리퀘스트 영역에 저장
	        //이건 페이지 부르자마자 응답되므로, 저장해봤자 의미가 없음, 리절트로 넘어가면 새로운 요청,응답 끝남, requestScope로 저장한거 없으니 null
	        session.setAttribute("sessionScope", "세션 영역입니다");//세션 영역에 저장
        %>
        
        
    </div><!--container-->
</body>
</html>