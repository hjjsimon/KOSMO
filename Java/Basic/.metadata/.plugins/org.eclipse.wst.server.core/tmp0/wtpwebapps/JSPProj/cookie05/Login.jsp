<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	List<String> cart = new Vector<>();
	String username="";
	if(cookies != null){
		for(Cookie cookie : cookies){
			String name = cookie.getName();
			String value = cookie.getValue();
			if("USER_ID".equals(name)) username = value;
		}
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
	<title>Login.jsp</title>
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
	<div class="container" style="margin-top:50px">
		<div class="jumbotron bg-info">
			<h1>로그인</h1>            
		</div><!--jumbotron-->
		<fieldset class="form-group border p-3">
			<legend class="w-auto px-3">웹 스토리지를 이용한 아이디 저장</legend>
			​<span style="color: red; font-weight: bold">
			<!-- 여기에 아이디/비번 불일치시 에러 메시지 출력 -->
			<%=request.getAttribute("ERROR")==null? "" : request.getAttribute("ERROR")%>
			</span>
			<%if(session.getAttribute("USER-ID")==null){ %>
			<form class="form-inline" method="post" action="Process.jsp">
				<label>아이디</label>
    			<input  type="text" name="id" class="form-control mx-2" 
    				value="<%=request.getParameter("id")==null?username:request.getParameter("id")%>"/>    			
    			<label>비밀번호</label>
    			<input type="password" name="pwd" class="form-control mx-2"/>
    			<div class="custom-control custom-checkbox">
					<input type="checkbox" class="custom-control-input" name="id-save" value="Y" id="id-save" checked/>
					<label class="custom-control-label" for="id-save" >아이디 저장</label>
				</div>
				<input type="submit" class="btn btn-danger mx-2" value="로그인"/>
    			<%} else{%>
			</form>
			<hr/>
			<!-- 아래 버튼 클릭시 Logout.jsp로 이동. 로그아웃 처리 -->
			​<input type="button" value="로그아웃" class="btn btn-danger"/>
			<%} %>
		</div><!--container-->
		<script>
            var id = document.querySelector('[name=id]');
            var pwd = document.querySelector('[name=pwd]');
            var checkbox = document.querySelector('#id-save');
            var submit = document.querySelector('input[type=submit]');
            var button = document.querySelector('input[type=button]');
            button.onclick = function(){
            	location.href="Logout.jsp";
            }
		</script>
		
	
</body>
</html>