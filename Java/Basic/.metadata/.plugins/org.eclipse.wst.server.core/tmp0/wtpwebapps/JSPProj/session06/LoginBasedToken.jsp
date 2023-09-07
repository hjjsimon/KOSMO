<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/Top.jsp" %>
	<div class="container" style="margin-top:50px">
		<div class="jumbotron bg-info">
		    <h1>로그인(토큰기반 인증처리)</h1>            
		</div><!--jumbotron-->
		  
		<span class="font-weight-bold text-danger"><%=request.getAttribute("NOT-LOGIN")==null?"":request.getAttribute("NOT-LOGIN") %></span>
		<form class="form-inline" action="<%=request.getContextPath() %>/session06/LoginProcessBasedToken.jsp" method="POST">
			<label>아이디</label> 
			<input type="text" name="id" class="form-control mx-2" value="<%=request.getParameter("id")==null?"":request.getParameter("id") %>" /> 
			<label>비밀번호</label> 
			<input type="password" name="pwd" class="form-control mx-2" value="<%=request.getParameter("pwd")==null?"":request.getParameter("pwd") %>" /> 
			<input type="submit" class="btn btn-danger mx-2" value="로그인" />
		</form>		
	</div><!--container-->
 <%@ include file="/template/Footer.jsp" %>
   