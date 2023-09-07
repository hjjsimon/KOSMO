<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	private int getTotal(int start, int end){
		int sum = 0;
		for(int i=start; i<=end; i++) sum+=i;
		return sum;
	}

%>
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
    <title>Expression.jsp</title>
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
            <h1>Expression 정리</h1>
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">표현식(Expression 사용하기)</legend>
        	<h1 class="display-4">out내장객체를 사용한 출력</h1><!-- 서블릿으로 바뀔때 out.write로 바뀌고 문자열로 들어감, 그리고 버퍼에 쓰여서 전송됨-->
        	<h4>1부터 100까지 누적합</h4><!-- 이건 out.print로 출력됨 -->
        	<%
        		out.println(getTotal(1,100));
        		out.println("<h4>1부터 1000까지 누적합</h4>");
        		out.println(getTotal(1,1000));
        		out.println("<h4>1부터 10000까지 누적합</h4>");
        		out.println(getTotal(1,10000));
        	%>
        	<h1 class="display-4">표현식을 사용한 출력</h1>
        	<h4>1부터 100까지 누적합</h4>
        	<%=getTotal(1, 100) %>
			<h4>1부터 1000까지 누적합</h4>
        	<%=getTotal(1, 1000) %>        	
        	<h4>1부터 10000까지 누적합</h4>
        	<%=getTotal(1, 10000) %>
        </fieldset>
        
    </div><!--container-->
</body>
</html>