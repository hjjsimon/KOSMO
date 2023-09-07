<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">catch태그</legend>
        	<!--
				catch태그:
				-EL에서 오류날때 에러 처리시 주로 사용
				-에러내용을 원하는 위치에서 출력하고자 할때 사용	
				-JSTL문법오류는 catch가 안된다.
		    -->
        	<c:set var="fnum" value="100"/>
        	<c:set var="snum" value="0"/>
        	<h3>에러가 안나는 경우 : 에러내용 var속성에 저장안됨</h3><!-- 0으로 나누면 infinite, -->
        	<c:catch var="error">
        		\${funm/snum } : ${funm/snum }</br>
        	</c:catch>
        		\${error } : \${error }</br>
        	<h3>에러가 나는 경우 : 에러내용 저장됨</h3>
        	<c:catch var="error">
        		\${"백"+100 } : </br>${"백"+100 }</br><!-- 앞에 에러라 뒤의 br안먹음 앞에 해야함 -->
        	</c:catch>
        		\${error } : ${error }</br>	
        	<h3>JSTL문법오류는 캐치가 안됨</h3>
        	<%--
        	<c:catch>
	        	<c:choose>
	        	<!-- 주석넣으면 500에러 발생 -->
	        		<c:when test="true">참이다</c:when>
	        	</c:choose>
        	</c:catch>
        	--%>

        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>