<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.JWTOkens"%>
<%@page import="model.bbs.BBSDao"%>
<%@ page import="java.util.Date"%><!-- 지시어라고함 -->
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="이 페이지는 JSP 및 서블릿 테스트 문서입니다"
    %>

<%
	//스크립팅원소 라고함, 여기 자바코드 쓰면 됨s
	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String dateString=String.format("<kbd>%s</kbd>", dateFormat.format(new Date())) ;
	
	//DB 연결확인
	//BBSDao dao = new BBSDao(application);
	//dao.close();
	
	//Map<String,Object> payloads = new HashMap<>();//사용자 임의 데이터 추가
	//long expirationTime = 1000*60*60;//토큰만료시간 설정(1시간)
	
	//String token = JWTokens.createJWToken("KIM", "/resources/tokens", "sercret-key", payloads, expirationTime);
	
	//cf) WEB-INF에 넣으면 보안효과 있음, 원래 이게 정상, URL알아도 접근 불가해짐
	
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
    <title>HelloWorld.jsp</title>
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
    <div class="container">
        <div class="jumbotron bg-info">
            <h1>JSP 및 Servlet 테스트(허재준)<%--=token --%></h1>
            <!-- C:\Users\kosmo>ipconfig /all 해서 내 ip주소 찾고
            		localhost 대신 넣으면 다른 사람에게 서비스 가능 
            		jsp는 Server Side Script Language -> SSSL
            		서버측에서 해석하는 언어, 브라우저는 해석X, html만 해석가능-->
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">JSP 테스트</legend>
        	<h3>오늘은 <%= dateString %> 입니다</h3>
        </fieldset>
        <!-- 값, 연산자를 서버로 보내서 계산할 예정 -->
        <fieldset class="form-group border p-3">
	    	<legend class="w-auto px-3">Servlet테스트</legend>
	    	<a href="HelloServlet.do" class="btn btn-primary">HELLO SERVLET</a>			
			<span class="font-weight-bold text-danger" style="font-size:1.2em">${message}</span>
			<!-- 
				HelloServlet 가보면 message있음, request영역에서 읽어오는 내장객체인 requestScope가 EL의 앞부분 생략된것,
				pageScope부터 읽고, 없으면 requestScope로 넘어가서 찾아옴
			 -->
			<h2>계산기</h2>
			<form action="Calculator.kosmo" >
				<div class="form-group">
					<input type="number" class="form-control form-control-sm w-50" name="firstNum"  />
				</div>
				<div class="form-group">
					<select	name="operator" class="form-control form-control-sm w-50">
						<option value="+">더하기</option>
						<option value="-">빼기</option>
						<option value="*">곱하기</option>
						<option value="/">나누기</option>
					</select>
				</div>
				<div class="form-group">
					<input class="form-control form-control-sm w-50" type="number" name="secondNum"/>
				</div>
				<input class="btn btn-success"	type="submit" value="결과는" />
				<span class="font-weight-bold text-danger" style="font-size:1.4em">${result}</span>
			</form>
	    </fieldset>
        
    </div><!--container-->
</body>
</html>