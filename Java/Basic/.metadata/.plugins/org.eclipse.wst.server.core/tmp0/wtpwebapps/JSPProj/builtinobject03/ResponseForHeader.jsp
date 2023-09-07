<%@page import="java.sql.Connection"%>
<%@page import="java.util.Collection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//[응답헤더 설정]: set계열(덮어쓰기), add계열(추가하기) 2가지가 있음
	//웹브라우저가 지원하는 캐쉬기능을 이용하지 않겠다
	//모든 웹브라우저는 캐시기능 있음, 서버로부터 다운받는 캐시들 기본적으로 있음, 새로고침으로 다시 받으면 local의 갖고있던 페이지내용 가져오는게 빠름
	//즉 현재 페이지를 매번 서버에 요청하도록 만드는 것
	//응답이니까 reponse객체
	
	//아래 둘 다 no-cache, 서버의 페이지 내용이 수시로 변하면 내 local의 캐시랑 다름, 이런건 local의 페이지를 cache에서 가져오는게 아니라
	//서버에서 다시 받아오는게 좋음(실제로 쓸 일 거의 없음)
	//HTTP/1.0에 적용시킬 때
	response.setHeader("pragma", "no-cache");//인자 2개, 응답헤더명, 응답헤더값 둘 다 정해진 것들이 있음
	//HTTP/1.1에 적용시킬 때
	response.setHeader("cache-control", "no-cache");
	//JSP에서는 페이지지시어의 contentType속성이 바로 아래 한줄 코드와 같다(이건 애초에 메소드명에 응답헤더명 있어서 인자 1개)
	//response.setContentType("text/plain; charset=UTF-8");//plain으로 바꾸면 소스코드나옴(HTML로 해석안됨)
	//웹브라우저가 인식하지 못하는 Content-Type(MIME타입)인 경우
	//웹브라우저는 파일 다운로드창을 보여준다(브라우저마다 다름)
	//다운로드를 jsp나 서블릿으로 구현시 적용
	//IE에서는 ""사이 아무값이나, 파이어폭스는 binary/octect-stream 또는 application/unknown 등 쓰면 됨
	//response.setContentType("binary/octect-stream");//ResponseForHeader.jsp가 다운로드됨
	//1)add계열: 기존 헤더명 존재시 계속 추가, 없을 시 생성
	//2)set계열: 기존 헤더명 존재시 기존 헤더명의 값이 변경됨, 없을 시 생성
	//add계열
	Date date = new Date();
	response.addDateHeader("current-date", date.getTime());//1970년부터 흘러온 시간을 밀리세컨단위로 반환
	//Current-Date: Mon, 15 May 2023 07:50:30 GMT
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	response.addHeader("current-date", dateFormat.format(date));//Current-Date:	2023-05-15
	response.addIntHeader("number-of-students", 29);
	//set계열
	response.setDateHeader("current-date", date.getTime());//current-date 2개인데 1개로 통합됨, 덮어쓰는것
	//Current-Date: Mon, 15 May 2023 07:52:54 GMT
	response.setHeader("current-date", dateFormat.format(date));//Current-Date:	2023-05-15로 덮어쓰게됨
	response.setIntHeader("number-of-students", 30);//30으로 덮어쓰게됨
	//setContentLength()는 파일 다운로드 구현시 파일의 크기로
	//Content-Length를 설정할 때 사용(다운로드 진행바가 표시된다)
	//response.setContentLengthLong(파일크기)

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
    <script src="<%=request.getContextPath() %>/bootstrap/js/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.bundle.min.js"></script> 
    
    <script src="https://kit.fontawesome.com/16e1907a6b.js" crossorigin="anonymous"></script>
    <title>ResponseForHeader.jsp</title>
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
            <h1>응답헤더</h1>
        </div><!--jumbotron-->
        <h3>설정한 응답헤더 출력하기</h3>
        <ul class="list-unstyled">
	        <%
	        	//1]응답헤더명 얻기: getHeaderNames().
				//add계열이나 set계열로 추가 설정된 응답헤더명을 가져온다
				//단, 기존의 응답헤더명은 set계열로 설정해도 헤더명을 가져오지 못한다(ex. setContentType(),setContentLength() 등)
				//(대신 위 값들은 getContentType(),getHeader() 등으로 얻어올 수 있다. getContentLength()는 안됨)
				Collection<String> names = response.getHeaderNames();
	        	for(String headerName : names){
	        		//2]응답헤더값 얻기: String getHeader("헤더명")
					String headerValue = response.getHeader(headerName);
	        		out.println(String.format("<li>%s : %s</li>",headerName,headerValue));
	        		/*
		        		pragma : no-cache
						cache-control : no-cache
						current-date : 2023-05-15
						number-of-students : 30
					*/
	        	}
	        %>
        </ul>
        <h3>응답헤더 존재여부 판단</h3>
        <!-- boolean containsHeader("응답헤더명"), 존재시 t, 없으면 f -->
        <%=response.containsHeader("content-type")? "content-type 존재": "content-type 없음"%><br/>
        <%=response.getContentType() %><!-- text/html;charset=UTF-8 -->
        <%=response.containsHeader("content-length")? "content-length 존재": "content-length 없음"%><br/><!-- 없대 -->
        <%=response.getHeader("content-length") %><!-- null -->
        
    </div><!--container-->
</body>
</html>