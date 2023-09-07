<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/template/Top.jsp" />
<div class="container" style="margin-top: 50px">
	<div class="jumbotron bg-info">
		<h1>Servlet</h1>
	</div>
	<!--jumbotron-->
	<fieldset class="form-group border p-3">
		<legend class="w-auto p-3">서블릿으로 요청 보내기</legend>
		<!--URL은 디렉토리 구조와 상관없다 -->
		<ul class="list-unstyled">
			
			<li><kbd>1.GET방식</kbd> <a href="<c:url value="/Basic/get.kosmo"/>">GET방식</a></li>
			<!-- URL생성, 절대경로 걸어줌, JSPProj알아서 붙음 -->
				<!-- 서블릿 등록하러 web.xml 이동 -->
				<!-- 
					서블릿의 생성자 GetController():GetController
					서블릿의 init():GetController
					서블릿의 doGet():GetController
					서블릿의 service():GetController
				
					출력됨, 원래 service()가 먼저 실행된다는데 doGet먼저 뜨네
					doGet이면 service로 와서 super때문에 doGet으로 이동 후 serivce()출력
			-->
			<li>
				<kbd>2.POST방식</kbd>
				
				<form class="form-inline" action="<c:url value="/Basic/post.kosmo"/>" method="POST">
					<label>아이디</label> 
					<input type="text" name="id" class="form-control mx-2" /> 
					<label>비밀번호</label> 
					<input type="password" name="pwd" class="form-control mx-2" /> 
					<input type="submit" class="btn btn-danger mx-2" value="로그인" />
				</form></li>
			<li>
				<kbd>3.GET/POST에 상관없이 요청받기(하나의 컨트롤러(서블릿)로 GET/POST방식 둘다 처리)</kbd><br /> 
				<!-- 이제 web.xml에 등록안함 귀찮 -->
				<a href="<c:url value="/Basic/both.kosmo"/>">GET방식</a>
				<form class="form-inline" action="<c:url value="/Basic/both.kosmo"/>" method="POST">
					<input type="submit" class="btn btn-danger mx-2" value="POST방식" />
				</form>
			</li>
			<li>
				<kbd>4.하나의 컨트롤러로 여러 요청 처리하기(파라미터로 구분해서)</kbd><br />
			 	<a href="<c:url value="/Basic/multi.kosmo?crud=create"/>">입력요청</a> <!-- ?부터는 바뀔 수 있으므로 패턴 아님 --> 
			 	<a href="<c:url value="/Basic/multi.kosmo?crud=read"/>">조회요청</a> 
			 	<a href="<c:url value="/Basic/multi.kosmo?crud=update"/>">수정요청</a> 
			 	<a href="<c:url value="/Basic/multi.kosmo?crud=delete"/>">삭제요청</a>
			</li>
			<li>
				<kbd>링크메뉴 클릭(GET요청)->FIRSTController->입력폼페이지로
					포워드->입력폼에서 POST방식 요청(SECONDController)->SECONDController에서는
					FIRSTController로 포워드(405에러)</kbd> 
				<a href="<c:url value="/Basic/first.kosmo"/>">링크 메뉴</a>
			</li>
		</ul>
	</fieldset>
	<fieldset class="form-group border p-3">
		<legend class="w-auto p-3">리퀘스트 영역에 저장된 데이타 출력</legend>
		<ul class="list-unstyled">
			<li>get.kosmo요청시 : ${GET}</li>
			<li>post.kosmo요청시 : ${POST}</li>
			<li>both.kosmo요청시 : ${BOTH}</li>
			<li>multi.kosmo요청시 : ${MULTI}</li>
		</ul>
	</fieldset>
</div>
<!--container-->
<jsp:include page="/template/Footer.jsp" />
