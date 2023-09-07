<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">import태그</legend>
        	<!-- jsp:include액션태그와 같다. request영역 공유	-->
    		<!-- url속성에 절대 경로 설정시 프로젝트명은 제외
	         단,server.xml의 Context태그의  path속성값을 지운 경우는 상관없다.
	 		-->
	 		<!-- 리퀘스트 영역에 속성 저장 -->
	 		<c:set var="requestvar" value="리퀘스트 영역" scope="request"/>
		 	<h3>var속성 미 지정(해당 위치에 바로 삽입됨)</h3>
		 	<c:import url="ImportedPage.jsp" >
		 		<c:param name="username" value="KOSMO"/>
		 		<c:param name="password" value="1234"/>
		 	</c:import>
		 	
		 	<h3>var속성 지정(조건에 따라서 원하는 위치에 삽입하고자 할때)</h3>
		 	
		 	<c:import url="ImportedPage.jsp" var="content">
		 		<c:param name="username" value="KOSMO"/>
		 		<c:param name="password" value="1234"/>
		 	</c:import>
		 	
		 	<%--
			<c:import url="ImortedPage.jsp?username=KOSMO&password=1234" var="content"/><!-- 외울필요x쿼리스트링으로 저장,됨, 이전엔 다 페이지로 인식해서 안됐음 -->
			 --%>
	 		<h3>아래애 import된 페이지 내용 출력</h3>
	 		${content }
	 		<h3>외부에 있는 컨텐츠 삽입(저작권 문제 가능)</h3>
	 		<!-- 아래 CSS제대로 안먹음, 위쪽 코드에 /~~~앞에 https://www.nate.com/~~~~ 붙여줘야함, 자바스크립트는 작동안함 -->
	 		<%-- 
	 			<c:import url="https://www.nate.com"/>
	 		--%>
	 		<iframe src="ImportedNate.jsp" style="border:none;width:100%;height:400px"></iframe>
	 		
	 	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>