<%@page import="model.JWTOkens"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- IsMember.jsp -->
<!-- 세션기반 -->
<%--
	//회원인 경우만 이용 가능 토록 처리-로그인 여부 판단]
	if(session.getAttribute("USER-ID")==null){
--%>
	<script>
		//alert("로그인후 이용하세요(세션기반)");
		//location.replace("<%=request.getContextPath()  %>/session06/Login.jsp");
	
	</script>

<%--
		return;
	}--%>
<!-- 토큰 기반 -->
<%
	
	boolean isValid=JWTOkens.verifyToken(
			JWTOkens.getToken(request,application.getInitParameter("COOKIE-NAME")), 
			"/resources/tokens", 
			"secret-key");

	if(!isValid){
%>
	<script>
		alert("로그인후 이용하세요(토큰기반)");
		location.replace("<%=request.getContextPath()  %>/session06/LoginBasedToken.jsp");
	
	</script>

<%
		return;
	}
%>
