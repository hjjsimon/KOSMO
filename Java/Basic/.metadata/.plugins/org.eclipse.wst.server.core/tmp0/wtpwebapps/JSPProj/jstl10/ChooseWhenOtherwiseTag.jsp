<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/mytaglib.tld" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">choose~when~otherwise태그</legend>
        	<!-- EL에서 사용할 변수 설정 -->
        	<c:set var="numvar" value="99"/>
        	<c:set var="strvar" value="자바"/>
        	<h3>짝/홀수 판단</h3>
        	<!--
        		choose태그 안에 주석시 에러, 반드시 when~otherwise절만 가능
        		당연히 if처럼 맨위부터 확인, if해당 안되면 아래 if로 이동
        		맨 위 when이 if, 그 아래로 else if, 맨 밑의 otherwise는 else
        	-->
        	<c:choose>
        		<c:when test="${numvar % 2 == 0 }">
        			${numvar }는 짝수
        		</c:when>
        		
        		<c:otherwise>
        			${numvar }는 홀수
        		</c:otherwise>
        	</c:choose>
        	<h3>문자열 비교</h3>
        	
        	<c:choose>
        		<c:when test="${strvar=='JAVA' }">
        		\${strvar }는 "JAVA"다
        		</c:when>
        		<c:when test="${strvar=='java' }">
        		\${strvar }는 "java"다
        		</c:when>
        		<c:when test="${strvar=='자바' }">
        		\${strvar }는 "자바"다
        		</c:when>
        	
        	</c:choose>
        	<form>
				<div class="form-group">
					<label><kbd class="lead">국어</kbd></label> <input type="text"
						class="form-control" name="kor" placeholder="국어점수를 입력하세요">
				</div>
				<div class="form-group">
					<label><kbd class="lead">영어</kbd></label> <input type="text"
						class="form-control" name="eng" placeholder="영어점수를 입력하세요">
				</div>
				<div class="form-group">
					<label><kbd class="lead">수학</kbd></label> <input type="text"
						class="form-control" name="math" placeholder="수학점수를 입력하세요">
				</div>
				<button type="submit" class="btn btn-primary">확인</button>
			</form>
        	<!-- 국영수 점수를  받아서 평균이
		    	90점이상이면 "A학점"출력
		    	80점이상이면 "B학점"출력
		    	70점이상이면 "C학점"출력
		    	60점이상이면 "D학점"출력
		    	60점미만이면 "F학점"출력.
		    	단,EL과 JSTL만을 사용하여라
		   	-->
		   
        	<c:set var="kor" value="${param.kor }" /><!-- get이든 post든 파라미터 받는건 동일 -->
        	<c:set var="eng" value="${param.eng }" />
        	<c:set var="math" value="${param.math }" />
        	<c:if test="${!empty kor && !empty eng && !empty math }"><!-- 사용자가 입력한경우 들어옴 -->
        		<c:if test="${my:isNumber(kor) && my:isNumber(eng) && my:isNumber(math)}" var="isNumber"><!-- 입력한 값이 모두 숫자인지 확인 -->
	        		<c:set var="avg" value="${(kor+eng+math)/3 }"/>
	        		<c:choose>
	        			<c:when test="${avg >= 90 }">A학점</c:when>
	        			<c:when test="${avg >= 80 }">B학점</c:when>
	        			<c:when test="${avg >= 70 }">C학점</c:when>
	        			<c:when test="${avg >= 60 }">D학점</c:when>
	        			<c:otherwise>F학점</c:otherwise>
	        		</c:choose>
        		</c:if>
        		<c:if test="${!isNumber }">숫자만 입력하세요</c:if>
        	</c:if> 
        	 <!--
	           회원인 경우 "?님 반갑습니다"
	           아닌 경우 "로그인후 이용하세요"출력.
	           단,자신만의 태그라이브러리를 만들어
	           EL식으로 호출하여라 단,
	           실제 멤버 테이블을 연동(BBS게시판에서 사용했던 회원테이블 연동)하여라.
	        -->
        	<hr/>
        	<form class="form-inline">
				<label>아이디</label>
				<input type="text" name="id" class="form-control mx-2"/>
				<label>비밀번호</label>
				<input type="password" name="pwd" class="form-control mx-2" />
				<input type="submit" class="btn btn-danger mx-2" value="로그인" />
			</form>
        	
        	<!-- EL에 모든걸 가져오는게 있대,그래서 아래 isMember하고 servletContext랑 받은 이름, 패스워드 -->
        	<!-- KIM, 1234 -> 회원 -->
        	<c:set var="username" value="${param.id }" />
        	<c:set var="password" value="${param.pwd }"/>
        	<c:if test="${!empty username && !empty password }">
        		<c:choose>
        			<c:when test="${my:isMember(pageContext.servletContext, username, password) }">
        				<kbd>${username }</kbd>님 반갑습니다
        			</c:when>
        			<c:otherwise>로그인 후 이용하세요</c:otherwise>
        		</c:choose>
        	</c:if>
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>