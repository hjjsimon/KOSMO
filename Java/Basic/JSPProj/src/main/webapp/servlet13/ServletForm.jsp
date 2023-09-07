<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Servlet</h1>            
        </div><!--jumbotron-->
        ${WHERE}
        <form method="post" action="<c:url value="/Basic/second.kosmo"/>">
	    	<input type="submit" class="btn btn-danger mx-2" value="POST요청"/>
	    </form>  
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>
    