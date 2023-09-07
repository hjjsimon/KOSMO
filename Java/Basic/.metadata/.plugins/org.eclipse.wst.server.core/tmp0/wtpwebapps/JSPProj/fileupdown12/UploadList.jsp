<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>카테고리</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">파일목록</legend>
        	<ul class="list-unstyled">
        		<%--
        		String directoryPath="D:\\HJJ\\Workspace\\Java\\Basic\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\JSPProj\\upload";
        		File directory = new File(directoryPath);
        		File[] files = directory.listFiles();
        		if (files != null) {
        		 for (File file : files) {
				--%>
				<%
					String saveDirectory = request.getServletContext().getRealPath("/upload");
					File file = new File(saveDirectory);
					File[] files = file.listFiles();
					for(File f : files){
				%>
        		<li><a href="Download.jsp?filename=<%=f.getName() %>">파일명:<%=f.getName() %> , 파일크기:<%=(int)Math.ceil(f.length()/1024.0) %> KB</a></li>
        		<!-- 쿼리스트링으로 파일명 걸어 전달, 링크 걸어서 다운로드도 가능하도록함 -->
        		<%} %>
        	</ul>
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>