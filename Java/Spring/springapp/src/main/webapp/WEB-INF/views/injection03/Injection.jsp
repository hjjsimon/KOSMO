<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>Dependency Injection(DI)</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">의존성 주입</legend>
        	<h3>인젝션 종류</h3>
        	<ul class="list-unstyled">
	    		<li><a href="<c:url value="/Injection/Constructor.do"/>">생성자 인젝션</a></li>
	    		<li><a href="<c:url value="/Injection/Field.do"/>">필드 인젝션</a></li>
	    		<li><a href="<c:url value="/Injection/Setter.do"/>">세터 인젝션</a></li>
	    	</ul>
	    	<h3>DI로 객체 사용하기</h3>
			<form method="post" action="<c:url value="/Injection/Injection.do"/>">
				<div class="form-group">
					<label><kbd class="lead">올린이</kbd></label> 
					<input type="text" class="form-control" placeholder="이름을 입력하세요" name="name">
				</div>
				<div class="form-group">
					<label><kbd class="lead">주소</kbd></label> 
					<input type="text" class="form-control" placeholder="주소를 입력하세요" name="addr">
				</div>
				<div class="form-group">
					<label><kbd class="lead">나이</kbd></label> 
					<input type="text" class="form-control" placeholder="나이를 입력하세요" name="age">
				</div>
				<button type="submit" class="btn btn-primary">확인</button>
			</form>
			<hr/>
			<!-- request에서 읽음, 생성자 인젝션 누르면 아래 출력됨 -->
			<c:if test="${not empty personInfo }">
				<kbd>${personInfo }</kbd>
			</c:if>
		
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>