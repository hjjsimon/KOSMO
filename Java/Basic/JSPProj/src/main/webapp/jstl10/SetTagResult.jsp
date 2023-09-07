<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.MemberDTO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <h3>xxxvar계열로 출력하기</h3>
        	<ul class="list-unstyled">
        		<li>pageVar : ${pageVar }</li>
        		<li>requestVar : ${requestVar }</li>
        		<li>sessionVar : ${sessionVar }</li>
        		<li>applicationVar : ${applicationVar }</li>
        	</ul>     
        	<h3>MemberDTO(자바빈)계열(argsMember) 출력하기</h3>
        	<ul class="list-unstyled">
        		<li>이름 : ${argsMember.name }</li><!-- requestScope생략가능 -->
        		<li>아이디 : ${requestScope.argsMember.id }</li>
        		<li>비번 : ${requestScope.argsMember.pwd }</li>
        	</ul>
        	<h3>리스트 계열 컬렉션(인덱스가 1인 객체) 출력하기</h3>
        	<ul class="list-unstyled">
        		<%-- List<MemberDTO> list = (List<MemberDTO>)request.getAttribute("list"); --%><!-- 없어도됨 -->
        		<li>이름 : ${list[1].name }</li>
        		<li>아이디 : ${list[1].id }</li>
        		<li>비번 : ${list[1].pwd } </li>
        	</ul>
        	<h3>맵 계열 컬렉션(키값이 'args'인 객체) 출력하기</h3>
        	<ul class="list-unstyled">
        		<%-- Map<String,MemberDTO> map = (Map<String,MemberDTO>)request.getAttribute("map"); --%>
        		<li>이름 :${map.args.name } </li>
        		<li>아이디 :${map['args'].id } </li>
        		<li>비번 :${map.args.pwd } </li>
        	</ul>
        	<h3>파라미터 출력</h3>
        	<%= request.getParameter("subject") %>
        	${param.subject }
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>