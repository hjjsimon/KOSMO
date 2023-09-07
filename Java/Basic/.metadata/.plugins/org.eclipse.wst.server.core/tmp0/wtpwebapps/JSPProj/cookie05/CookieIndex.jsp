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
    <title>CookieIndex.jsp</title>
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
            <h1>cookie</h1>            
        </div><!--jumbotron-->
        
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">쿠키</legend>
        	<h2>쿠키 설정하기</h2>
        	<%

        	//쿠키(상태정보, 작은 데이터)는 사용자PC(하드디스크)나 브라우저에 저장, 쿠키는 서버에 저장x
        	//ex. 아이디 저장, 장바구니 담기 등 키값으로 불러옴
        	//요즘은 잘 안씀, 웹스토리지를 대신 씀, 쿠키는 서버랑 주고받으므로 사용자 많으면 서버가 부담됨, 암호화도 안됨(해킹가능)
        	//웹스토리지는 서버와 주고받는게 없어서 부담없음(HTML5부터 나온 API)
        	
        	//http프로토콜은 요청과 그에 따른 응답 끝나면 연결이 끊어짐
        	//그러면 id,pwd입력하고 연결 끊어지면 로그인 된지 어떻게 알아
        	//그래서 쓰는게 쿠키, 클라이언트의 접속상태 등을 알아내기 위해 쓰는게 쿠키
        	//클라이언트가 페이지 요청시 서버에서 쿠키를 하나 구움, 응답 시 바디가 브라우저에 뿌려짐
        	//응답헤더(작업지시서)에 쿠키를 포함해서 뿌려줌, 쿠키를 사용자 브라우저 또는 pc에 저장함(클라이언트 저장이라 보안에 취약, 보낼 때 암호화도 안됨)
        	//이후 새로 요청시 요청헤더에 쿠키를 포함해서 서버에 뿌림, 서버측에서는 자기가 구웠던 쿠키를 다시 확인하게됨
        	//이놈이 나한테 접속해있는 상태구나~ 알게됨
        	
        	//이후 과정 중 최초로 .jsp로 새롭게 서버 요청시 서버에서 받는 응답헤더에 쿠키가 있던 과정은 보이지 않음, 키자마자 요청한게 없으니까
        	//이후 뭔가 체크박스 체크를 하던 데이터저장 이후 서버에 요청시 request헤더에 쿠키가 있는 것을 볼 수 있음 
        	
        	//근데 쿠키는 유통기한이 있어서 지나면 자동삭제됨
        	//서버에서는 서버가 구웠던 쿠키 없어진걸 보면 새로운 접속이구나라고 인식함
        	//웹스토리지는 쿠키처럼 보내지 않으므로 보안, 서버부하측면에서 훨씬 좋음 
        	
        		//1]쿠키객체 생성: new Cookie("쿠키명","쿠키값");
        		Cookie cookie = new Cookie("UserID","KOSMO");
        		//2]쿠키가 적용되는 PATH설정
				cookie.setPath(request.getContextPath());//JSPProj이하 모든 폴더에 적용됨
				//3]쿠키 유효기간 설정-초 단위
				/*
				   유효기간 미 설정시 쿠키는 웹브라우저에 저장됨
				   -웹브라우저 닫을때 사라짐
				    유효기간 설정시는 사용자의 PC(HDD의 텍스트 파일형태)에 저장됨.
				   -유효기간이 지나면 파일은 자동으로 삭제됨
				*/
				cookie.setMaxAge(60);//1분
				
        		//4]생성된 쿠키를 응답헤더에 설정:addCookie()
				response.addCookie(cookie);//응답헤더에 만든 쿠키가 쓰임, 없으면 요청헤더에만 쓰임
				//요청헤더 안에 UserID=KOSMO 있음
				//Set-Cookie: UserID=KOSMO; Path=/JSPProj ->이건 응답헤더 안에 있음
				//request.getCookies();
				//요청하면 쿠키 구워서 클라이언트에 보냄, 클라이언트는 브라우저에 쿠키저장함, 지금 코드는 요청헤더에서 읽어오는것(새로고침해야 읽어옴)	
				//따라서 위의 코드는 의미 없대
				
				//인덱스 페이지에서 새로고침하면 요청헤더에 생김
				//리절트에는 요청헤더에만 존재, 1분 지나고 보면 USERID 사라짐
				
        	%>
        	<h2>클릭으로 페이지 이동후 쿠키값 확인</h2>
        	<a href="CookieResult.jsp">쿠키값 확인</a>
        	
        	
        </fieldset>        
        
    </div><!--container-->
</body>
</html>