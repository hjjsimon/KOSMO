<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ForwardActionTagExamResult.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>&lt;jsp:forward&gt;액션 태그 예제</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">리퀘스트 영역에 저장된 속성 읽기</legend>
        	<ul class="list-unstyled">
        		<% Map<String,String[]> param=(Map<String,String[]>)request.getAttribute("params"); %>
        		<li>아이디 : <%=param.get("username")[0] %> </li>
        		<li>비밀번호 : <%=param.get("password")[0] %> </li>
        		<li>운동종목 : <%=Arrays.toString(param.get("sports"))%> </li>
        		<li>연령대 : <%=param.get("ages")[0] %> </li>
        	</ul>
        </fieldset>  
                <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">파라미터로 전달된 값 읽기</legend>
        	<ul class="list-unstyled">
        		<% Map<String,String[]> map=request.getParameterMap(); %>
        		<li>아이디 : <%=map.get("username")[0] %> </li><!-- 키값 알고있으니 키셋할 필요 없음, for하면 안됨 -->
        		<li>비밀번호 : <%=map.get("password")[0] %> </li>
        		<li>운동종목 : <%=Arrays.toString(map.get("sports"))%> </li>
        		<li>연령대 : <%=map.get("ages")[0] %> </li>
        	</ul>
        </fieldset>            
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>