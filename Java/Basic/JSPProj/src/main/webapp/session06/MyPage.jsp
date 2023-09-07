<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/Top.jsp" %>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>마이페이지 <% if(session.getAttribute("USER-ID")!=null){ %><small><%=session.getAttribute("USER-ID") %>님 반갑습니다.<%} %></small></h1>            
        </div><!--jumbotron-->
        <% if(session.getAttribute("USER-ID")!=null){ %><!-- null체크, 현재 페이지 바로 실행시 null님 반갑습니다, 이러면 로그아웃 안뜨게함 -->
        <a class="btn btn-info" href="Logout.jsp">로그아웃</a>   
        <%} %> 
    </div><!--container-->
<%@ include file="/template/Footer.jsp" %>