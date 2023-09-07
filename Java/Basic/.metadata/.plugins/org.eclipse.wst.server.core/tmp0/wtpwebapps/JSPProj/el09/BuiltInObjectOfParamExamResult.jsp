<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String inters="";//
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>EL의 param 및 paramValues내장객체</h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">자바코드(스크립팅요소)로 받기</legend>
        	<ul>
        		<li>이름 : <%=request.getParameter("name") %></li>
        		<li>성별 : <%=request.getParameter("gender") %></li>
        		<%
        			String[] items = request.getParameterValues("inter");//여러개라 getParameterValues, 배열이라 주소나옴
        			//방법1- Arrays.toString(배열)=>"[요소1,요소2,...]"형태의 문자열 반환
        			//String inters = Arrays.toString(items);//대괄호로 감싸서 배열 나옴
        			//방법2- 스트림 사용(람다함수)
        			/*
        			StringBuffer buf = new StringBuffer();
        			Arrays.stream(items).forEach(item -> {//배열, List계열 컬렉션만 스트림으로 만들 수 있음
        				buf.append(String.join("", item+" "));//item을 하나씩 꺼내고(forEach) 람다식 써서 연결         	
        			});
        			//<%--=buf --%->로 출력
        					
        			Arrays.stream(items).forEach(item -> {
        				inters+=item+" ";        			   
        			});
        			*/
        			//방법3- 확장 for문 사용
        			String inters = "";
        			for(String item : items){
        					inters += item + " ";
        			}
        			
        		%>
        		<li>관심사항 : <%=inters %></li>
        		<li>학력사항 : <%=request.getParameter("grade") %></li>
        	</ul>
        </fieldset>      
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">EL로 받기</legend>
        	<ul>
        		<li>이름 : ${param.name }</li><!-- 파라미터 받을 때 param 내장객체, 여러값이면 paramValues -->
        		<li>성별 : ${param['gender'] }</li>
        		<!-- EL안에서 Arrays.toString() 호출가능 -->
        		<li>관심사항 : ${Arrays.toString(paramValues.inter) }</li><!-- 방법1] 주소출력, String[] items = request.getParameterValues("inter");와 동일 -->
        		<li>관심사항 : 
        			<c:forEach var="item" items="${paramValues.inter}"><!-- var는 item인셈, items는 배열 -->
        				${item}&nbsp;<!-- EL에 for문 없어 JSTL 병용해야함 -->
        			</c:forEach>
        		</li>
        		<li>학력사항 : ${param.grade }</li>
        	</ul>
        </fieldset>     
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>