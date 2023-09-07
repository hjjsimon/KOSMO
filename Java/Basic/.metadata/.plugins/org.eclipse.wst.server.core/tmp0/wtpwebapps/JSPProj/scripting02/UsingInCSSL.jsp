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
    <title>UsingInCSSL.jsp</title><!-- CSSL안에서 사용하기 -->
    <%
    	//사용자 선택값 받기
    	String color = request.getParameter("color");
    	String font = request.getParameter("font");
    	//out.print(String.format("색상:%s,글꼴:%s",color,font));
    	//처음엔 받은거 파라미터 서버에 받은게 없으니 null(body안에 색상:null,글꼴:null 출력, 상단태그에 가려 안보임)
    	if(color == null || color.length() == 0) color="black";//칼라 기본 블랙, 최초 null이므로 이걸로 세팅됨
    	if(font == null || font.equals("")) font="Helvetica Neue";
    %>
    <style>
        /*점보트론 세로폭 및 마진바텀 줄이기*/
        .jumbotron{
            padding-top: 1rem;
            padding-bottom: 1rem;
            margin-bottom: .5rem;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
        span{
        	color: <%=color%>;
        	font-family: <%=font%>;
        }
    </style>
</head>
<body>
	<!-- 네비게이션바 -->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
        <a class="navbar-brand" href="#">KOSMO</a>
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
            <h1>스크립팅 요소를 CSSL안에서 사용하기</h1>
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">HTML안에서 사용하기</legend><!-- 서블릿 후 HTML로 보내진 후 브라우저에서 실행됨 -->
        	<%for(int i=1;i<=6;i++) {%><!-- 중괄호 필수, 한줄코드면 없어도 되지만 jsp는 엔터가 다음값으로 들어감 -->
        	<h<%=i %>>Bootstrap heading</h<%=i %>>
        	<%} %>
        	<form class="form-inline">
				<input type="number" class="form-control mb-2 mr-2" name="counter" /> <!-- 사용자 입력값 파라미터 counter -->
				<input type="submit" class="btn btn-danger mb-2" value="이미지 카운터" />
			</form>
			<%
				String counter = request.getParameter("counter");//최초에는 카운터가 서버에 전달 안되므로 null
				if(counter != null){//counter라는 파라미터가 전달된건 이미지카운터 버튼을 눌러 submit한것
					char[] ch = counter.toCharArray();//입력값 문자열 배열로 자름
					for(char num : ch){//ch 배열에서 꺼내와 num에 담음
			%>
        	<img src="../images/num_<%=num %>.gif" alt="<%=num %>번이미지"/>
        	<%}}//for,if%>
        </fieldset>
        
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">CSS안에서 사용하기</legend>
        	<span class="lead">CSS를 적용한 텍스트</span>
			<form method="post"><!-- post방식 URL안보임, 이전에는 다 get방식 -->
				<label>글자색</label>
				<select name="color" class="form-control w-50"><!-- 파라미터명 color -->
					<option value="">색상 선택요망</option>
					<option value="Red">빨강</option><!-- 전송될 값이 value -->
					<option value="Green">그린</option>
					<option value="Blue">블루</option>
				</select> <label>글꼴</label>
				<select name="font" class="form-control w-50">
					<option value="">글꼴 선택요망</option>
					<option value="굴림체">굴림체</option>
					<option value="바탕체">바탕체</option>
					<option value="휴먼옛체">휴먼옛체</option>
				</select>
				<input class="btn btn-info mt-2" type="submit" value="글꼴 및 색상 변경" />
			</form>
        </fieldset>
        
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">자바스크립트안에서 사용하기</legend>
        	<form class="form-inline">
				<label>아이디</label>
				<input class="form-control mx-2 mt-2" type="text" name="id" />
				<label>비밀번호</label>
				<input class="form-control mx-2 mt-2" type="password" name="pwd" />
				<input class="btn btn-info ml-2 mt-2" type="submit" value="로그인" />
			</form>
        </fieldset>
        <%
        	//사용자 입력값 받기
        	String id = request.getParameter("id");
        	String pwd = request.getParameter("pwd");
        %>
        <script>	
        	//사용자가 아이디 입력시 100% is not defined에러
        	//console.log(<%=id%> === null);//t,서버측에서 먼저 실행되므로 log(null); 이랑 동일, === 자스임 타입, 값 모두 같아야 true
        	//console.log(<%=id%> == null);//t
        	console.log('<%=id%>' === null);//f 'null' 문자열과 null 값,타입 을 비교한것, 값, 타입 달라 f
        	console.log('<%=id%>' == null);//f 'null' 문자열과 null 값을 비교한것, 문자열 null이랑 값 null은 값자체가 다른거임
        	//''싱글은 string이라 false나옴, -> string으로 'null' 이라 null이 아님, 값, 타입 틀려서 false나옴
        	//console.log(<%=id%> === 'KIM');
        	//최초null이면 f, 서버측에서 스크립틀릿먼저 실행 KIM이 됨, 근데 KIM변수 선언안해서 not defined 에러남
        	console.log(<%=pwd%> === '1234');
        	//최초null이면 f, 숫자는 자동으로 변환돼서 에러안남 숫자 1234 = 문자열 1234 타입만 달라 false
        	
        	//아래 2개가 멀쩡한거임, 이렇게 해야함
        	console.log('<%=id%>' === 'KIM');//console.log('KIM' === 'KIM'); 동일
        	console.log('<%=pwd%>' === '1234');//console.log('1234' === '1234'); 동일
        	
        	//방법1] 자바스크립트로 null체크
        	/*
        	if('<%=id%>'!='null'){//그냥 다른 애들도 string 처리해주는방법
				if('<%=id%>'.toUpperCase()=='KIM' && '<%=pwd%>'=='1234')
					alert('<%=id%>님 즐감하세요');//서버측에서 id 바뀐 후 브라우저로 내려옴
				else
					alert('회원가입후 이용하세요');
        	}
        	*/
        	//방법2] <script>태그 안에서 스크립팅 요소로 null 비교
        	<%
        		if(id != null){//서버에서 이부분이 먼저 실행, KIM이고 1234면 if 안의 문자열 출력
        			if("KIM".equals(id.toUpperCase()) && "1234".equals(pwd))//(id.toUpperCase().equals('KIM'))해도 되는데 앞이 더 좋음
        				out.println("alert('"+id+"님 즐감하세요');");
        			else
        				out.println("alert('회원가입후 이용하세요');");
        		}
        	%>
        	//팁1: jsp는 여기 폴더가 아니고 .metadata에 있는걸로 작업함
        	//팁2: 이것저것 했는데 안될때 1) 캐시삭제 2) 서버 우클릭, 클린2개/ project 클릭후 클린
        </script>
    </div><!--container-->
</body>
</html>