<%@page import="model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- BuiltinObjectOfParamResult.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>EL의 param내장객체</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">자바코드로 영역에 저장된 속성 및 파라미터로 전달된 값 읽기</legend>
        	<h1 class="display-4">영역에 저장된 속성</h1>
        	<%
        		MemberDTO member = (MemberDTO)request.getAttribute("dtoObject");//형변환
        	%>
        	<ul class="list-unstyled">
        		<li>MemberDTO객체 : <%=member %></li><!-- 이름:김길동,아이디:KIM,비번:1234,나이:20 -->
        		<li>String객체 : <%=request.getAttribute("stringObject") %></li><!-- String객체 : 문자열 객체-->
        		<li>Integer객체 : <%=request.getAttribute("integerObject") %></li><!-- Integer객체 : 1000 -->
        	</ul>
        	<h1 class="display-4">파라미터로 전달된 값</h1>
        	<%
        		int first = Integer.parseInt(request.getParameter("first"));
        		int second = Integer.parseInt(request.getParameter("second"));
        	%>
        	두 파라미터의 합: <%=first+second %>
        </fieldset>        
        <!--
			영역에 저장된 값은 xxxScope내장 객체로]
			-xxxScope.속성명 혹은 xxxScope["속성명"]
			파라미터로 전달 된 값은
			param내장객체 혹은 paramValues내장객체(체크박스종류)로
			
			]
			-param.파라미터명 혹은 param["파라미터명"]
			 paramValues.파리미터명 혹은 paramValues["파라미터명"]
			
			 param은 request.getParameter()와 같고
			 paramValues는 request.getParameterValues()와 같다
			
			※EL에서는 문자열등을 표시할때 ""이나 '' 둘다 사용가능
		 -->
        
        
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">EL코드로 영역에 저장된 속성 및 파라미터로 전달된 값 읽기</legend>
        	<h1 class="display-4">영역에 저장된 속성</h1>
        	<ul class="list-unstyled">
        		<li>MemberDTO객체 : ${dtoObject }</li><!-- requestScope 생략된 것, 형변환도 안해서 편리함, 코드도 깔끔 -->
        		<li>String객체 : ${requestScope.stringObject }</li><!-- 한번 써봄, 보통 안씀 -->
        		<li>Integer객체 : ${integerObject }</li>
        	</ul>
        	<h1 class="display-4">파라미터로 전달된 값</h1>
        	두 파라미터의 합: ${param.first+param['second'] }<!-- parseInt안해도 돼서 편함 -->
        	
        	
        </fieldset> 
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>