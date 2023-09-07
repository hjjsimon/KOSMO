<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/mytaglib.tld" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>EL에서 사용할 나만의 태그 라이브러리</h1>            
        </div><!--jumbotron-->
        <ul class="list-unstyled">
        	<li>\${my:isNumber('100사') } : ${my:isNumber('100사') }</li><!-- f, my:에서 컨트롤스페이스하면 뜸 -->
        	<li>\${my:isNumber("1004") } : ${my:isNumber("1004") }</li><!-- t -->
        	<li>\${my:getGender("123456-1234567") } : ${my:getGender("123456-1234567") }</li>
        	<li>\${my:getGender("123456-8234567") } : ${my:getGender("123456-8234567") }</li>
        	<li>\${my:getGender("123456-A234567") } : ${my:getGender("123456-A234567") }</li>
        </ul>
       
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>