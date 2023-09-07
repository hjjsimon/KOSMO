<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">forTokens태그</legend>
        	<%
        		String colors = "red,green,blue,#AB7DFF";
        		out.println("<h3>String클래스</h3>");
        		for(String color : colors.split(",")){//,로 잘라서 배열반환
        			out.println("<span style='color:"+color+"'>"+color+"</span>");
        		}	
        		out.println("<h3>StringTokenizer클래스</h3>");
        		StringTokenizer tokenizer = new StringTokenizer(colors,",");
        		out.println("토큰 수:"+tokenizer.countTokens()+"<br/>");
        		//hasMoreTokens(): 꺼내올 토큰이 있으면 true, 없으면 false반환
        		while(tokenizer.hasMoreTokens()){
        			//토큰을 꺼내올때는 nextToken()
        			String color = tokenizer.nextToken();
        			out.println("<span style='color:"+color+"'>"+color+"</span>");
        		}
        	%>
        	<h3>forTokens태그로 토큰화</h3>
        	<c:set var="colors" value="<%=colors %>" />
        	<!-- colors에서 ,로 쪼갠걸 하나하나 color에 담음 -->
        	<c:forTokens items="${colors}" delims="," var="color">
        		<span style="color:${color}">${color }</span>
        	</c:forTokens>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>