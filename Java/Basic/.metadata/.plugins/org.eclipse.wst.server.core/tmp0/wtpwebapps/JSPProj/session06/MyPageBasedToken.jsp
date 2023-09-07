<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//발급한 토큰 가져와서 유효한 토큰인지 판단하기
	//(쿠키네임 같은건 모든 서블릿에서 쓰니까 web.xml에서 파라미터 초기화하는게 좋음, secret-key 같은 것도 빼놓으면 좋음)
	String token = JWTOkens.getToken(request, application.getInitParameter("COOKIE-NAME"));
	boolean isValid = JWTOkens.verifyToken(token, "/resources/tokens", "secret-key");
	//사용자 신원정보를 가져오기위한 payloads부분 얻기
	Map<String,Object> tokenMap = JWTOkens.getTokenPayloads(token, "/resources/tokens", "secret-key");
	
	
%>
<%@ include file="/template/Top.jsp" %>

    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>마이페이지(토큰기반 인증처리) <% if(isValid){ %><small><%=tokenMap.get("sub")%>님 반갑습니다.<%} %></small></h1>            
        </div><!--jumbotron-->
        <% if(isValid){ %>
		<!-- 사용자 아이디 JSON영역에 저장시 sub로 저장 -->            
		<!-- null이면 유효하지 않은 토큰 -->
        <h4>비밀번호:<%=tokenMap.get("password")%></h4><!-- null체크, 현재 페이지 바로 실행시 null님 반갑습니다, 이러면 로그아웃 안뜨게함 -->        
        <a class="btn btn-info" href="LogoutBasedToken.jsp">로그아웃</a>   
        <%} %> 
    </div><!--container-->
<%@ include file="/template/Footer.jsp" %>