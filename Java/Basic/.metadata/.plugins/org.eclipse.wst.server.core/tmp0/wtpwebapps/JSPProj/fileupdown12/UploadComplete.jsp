<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>파일 업로드/다운로드</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">파일 업로드 결과</legend>
        	<h3>type="file" 요소를 제외한 폼 요소들</h3>
			<ul class="list-unstyled">
				<li>올린이 :<%=request.getParameter("name") %></li>
				<li>제목 :<%=request.getParameter("title") %></li>
				<li>관심사항 :<%=Arrays.toString(request.getParameterValues("inter")) %></li>
			</ul>
			<h3>업로드한 파일 정보</h3>
			<%
				List<Map<String,String>> fileInfos = (List<Map<String,String>>)request.getAttribute("fileInfos");
				int index=1;
				for(Map<String,String> file : fileInfos){
			%>
				<kbd class="lead"><%=index++ %>번째 파일</kbd>
				<ul class="list-unstyled">
					<li>원본 파일명 :<%=file.get("SubmittedFileName") %></li>
					<li>실제 파일시스템에 저장된 파일명: <%=file.get("systemFileName") %> </li>
					<li>컨텐츠 타입 : <%=file.get("ContentType") %></li>
					<li>파일 크기 : <%=(int)Math.ceil(Integer.parseInt(file.get("FileSize"))/1024.0) %>KB</li>
				</ul>
			<%} %>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>