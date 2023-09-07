<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
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
    <title>ApplicationForMajorMethod_2.jsp</title>
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
	<!-- 네비게이션 바 -->
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
        	<h2>자바 IO를 사용해서 파일의 내용을 브라우저로 전송</h2>
        	<!-- 브라우저에 이미지 띄우는법 ex. img태그 src="A.jpg", 브라우저에 태그쓰이면 브라우저가 서버에서 다운받음, 그리고 사용자 컴퓨터에 보여줌 
        		img태그 대신 stream으로 연결해서 보내기 가능 -->
        	<%
        		//데이터소스: 서버에 저장된 파일- 노드스트림:FileReader(문자단위로 서버에서 읽음)
        		//데이터목적지: 네트워크로 연결된 웹브라우저- out내장객체
        		//1]서버의 물리적 경로 얻기
        		String path = application.getRealPath("/builtinobject03/Out.jsp");//
        		//2]입력스트림 생성-서버의 파일에 빨대(당연히 필터 쓰는게 빠름)
				BufferedReader br = new BufferedReader(new FileReader(path));
        		//3]br로 읽고 out내장객체로 출력
				String data;
        		while((data = br.readLine())!=null){
        			out.println(data+"<br/>");
        		}
        		br.close();
        	%>
        	<h2>application내장객체의 getResourceAsStream(path)사용</h2>
        	<%
        		//InputStream getResourceAsStream("컨텍스트 루트를 제외한 /로 시작하는 웹상의 경로(ex 디렉토리03부터 시작)")
        		//웹 어플리케이션 안의 모든 자원(이미지,텍스트파일,동영상)에 빨대를 꽂을 수 있는 입력스트림을 반환하는 메소드
        		//물리적 경로 불필요, 웹상 경로만 알면 됨
        		InputStream is = application.getResourceAsStream("/builtinobject03/Out.jsp");
        		br = new BufferedReader(new InputStreamReader(is));
        		while((data = br.readLine())!=null){//br로 읽고 out내장객체로 출력은 동일
        			out.println(data+"<br/>");
        		}
        		br.close();
        	
        	%>
        	<!-- 4가지 영역에는 데이터저장가능, 그 영역 사용시 데이터를 가져다쓸 수 있음, 
        		영역에 데이터 저장방법은 setAttribute, 삭제는 removeAttribute, 가져오는건 getAttribute -->
        	<!-- 
        	
        		1)포워드방식 설명(요청1회)
        		서버에 A.jsp 페이지가 있음, <%--request.setAttribute("name","홍길동")--%> 으로 저장함
        		그리고 forward 시킴, forward메소드가 있음. B.jsp로 포워드 했음, 
        		포워드나 리다이렉트나 자동이돔, 
        		서버에 B.jsp 페이지가 생김, 여기서 바로 출력할 예정, 
        		< % = request.getAttribute("name"); 으로 읽고
        		<h2>안녕</h2> 이것도 출력
        		사용자가 서버에 A.jsp 요청함
        		서버에는 요청과 관련해서 A.jsp에 대해, request객체가 만들어짐 page영역도 만들어짐, 여기에 name값으로 홍길동 저장함
        		포워드는 서버내부에서의 요청, 서버내부에서의 이동, 기존요청인 A.jsp와 같은요청(같은 request객체)이 그대로 B.jsp로도 전달됨
        		브라우저는 새로운 요청을 하지 않음, request객체는 하나 계속 유지됨!
        		request객체에는 위에서 홍길동 전달함
        		request객체는 A.jsp, B.jsp랑 같이 사용함, B.jsp에서 홍길동 응답함
        		사용자는 A.jsp 요청, 근데 B.jsp로 응답되는 것, 홍길동 안녕 이 브라우저에 보임
        		페이지는 A -> B로 이동하며 page영역 사라짐
        		request객체는 응답완료할 때 사라지므로 page보다 더 오래 유지됨
        		
        		url에는 A.jsp가 찍히지만 실제 받는건 B.jsp임**********************(페이지 이동은 여러번이어도 요청은 단 1회!)
        		
        		그리고 홍길동 -> 이건 A에서 B로 값을 전송하는 것 
        		이전에 Form태그, 쿼리스트링으로 값을 전달했던 것 기억, 이건 String만 전달하는게 구림, 그러나 지금은 Object타입임 
        		
        		2)리다이렉트방식 설명(요청2회)
        		A.jsp에서 B.jsp로 리다이렉트함 메소드가 있음, < % request로 똑같이 홍길동 객체에 저장되는 코드 실행, page영역도 생김
        		redirect 시에는 이 때 바로 A.jsp 응답하며 요청 종료하며 request객체 사라짐
        		이 때 브라우저에는 location.href="B.jsp"; 라고 쓰임 -> 이건 자동으로 이동됨 이러면 B.jsp를 사용자가 다시한번 요청하는 것
				이 때 request.getAttribute("name")하면 request객체는 이전에 사라짐 새로 만들어진거라 안에 홍길동 없음, null임
				
				브라우저에는 null 안녕 출력됨        		
				
				url창에는 B.jsp 보임, 리다이렉트는 새로운 url을 요청하도록 하는거라 B.jsp 요청하는 것****************
        		
        		문제] A.jsp request에 홍길동저장, B.jsp로 포워드
        			B.jsp request에 홍길동에 추가로 20 저장, C.jsp로 리다이렉트, 요청에 대한 응답끝났으므로 request삭제
        			C.jsp 새로 요청이라 request 새로 생기며 null,null,안녕 출력됨
        			
        		결론: forward는 request값을 가져간다/ redirect는 request값을 초기화한다
        	
        	 -->
        	
        	
        	
        
        </fieldset>
    </div><!--container-->
</body>
</html>