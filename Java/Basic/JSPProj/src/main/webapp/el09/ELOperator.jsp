<%@page import="java.util.Vector"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 처음 쓰는 태그, 지금 써야함, WEB-INF에 라이브러리 넣어서 uri 내부 뜸 -->
<!-- ELOperator.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>EL의 연산자</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">EL연산자 연습하기</legend><!-- EL은 라이브러리 없어도 기본제공함,이것도 자바코드로 변환됨 -->
        	<ul class="list-unstyled">
        		<li>\${null+10 } : ${null+10 }</li><!-- EL에서 null이 연산 참여시 0처리 --><!-- 텍스트 처리하려면 \붙여줌  -->
        		<li>\${null*10 } : ${null*10 }</li><!-- 칸띄우는건 무관 -->	
        		<li>param내장객체 : ${param }</li><!-- 파라미터 안넘어와서 {} 출력, 파라미터 넘기면 {username=20} -->
        		<li>\${param.username+10 } : ${param.username+10 }</li><!-- 파라미터명 username, 없으므로 안넘어옴, 10출력 -->
        		<!-- http://localhost:8080/JSPProj/el09/ELOperator.jsp?username=20 으로 파라미터 넘기면 30출력 -->
        		<li>\${param['username']+10 } : ${param["username"]+10 }</li><!-- 이것도 됨, 싱글이나 더블로 감쌈 -->
        		<% String varInScriptlet = "스크립틀릿에서 선언한 변수";%>
        		<!-- 
        			EL표현식 \${}에서는 JSP의 스크립팅 요소에서 선언한 변수를 직접사용 못한다. 값이 출력안됨.
        			즉 null로 처리됨, 따라서 EL식에서 사용할 변수는 JSTL을 이용해서 선언해야한다
        		 -->
				<li>\${varInScriptlet+ 10} : ${varInScriptlet +10}</li>
				<li>\${varInScriptlet == null} : ${varInScriptlet == null}</li><!-- true -->
        	</ul>
        	<h4>JSTL로 EL에서 사용할 변수 선언</h4>
        	<c:set var="varInScriptlet" value="<%=varInScriptlet %>"/>
        	<!-- 얘도 자바코드로 바뀜(pageContext.setAttribute 동일), 속성명(var)으로 페이지영역에 저 위에 문자열이 저장됨-->
        	\${pageScope.varInScriptlet } :	${pageScope.varInScriptlet }</br><!-- 스크립틀릿에서 선언한 변수 -->
        	\${varInScriptlet } : ${varInScriptlet }<!-- 스크립틀릿에서 선언한 변수 -->
        	<h4>EL변수에 값 할당</h4>
        	<!-- 톰캣 8.0부터 할당가능(비권장): el-api.jar 변경됨 -->
        	<c:set var="fnum" value="9"/><!-- pageContext.setAttribute 동일(page기본), scope="request" 시에 request영역에 저장 -->
        	<c:set var="snum" value="5"/>
        	\${fnum = 100 } : ${fnum = 100 }<!-- 100할당하고 바로 출력 -->
        	<h4>EL의 산술 연산자</h4>
        	<ul class="list-unstyled">
        		<li>\${fnum + snum } : ${fnum + snum }</li><!-- 105, 문자열 자동으로 숫자로 바뀜 -->
        		<li>\${fnum - snum } : ${fnum - snum }</li><!-- 95 -->
	        	<li>\${fnum * snum } : ${fnum * snum }</li>
	        	<li>\${fnum / snum } : ${fnum / snum }</li>
	        	<li>\${fnum div snum } : ${fnum div snum }</li><!-- 위와 동일 -->
        		<li>\${fnum % snum } : ${fnum % snum }</li>
        		<li>\${fnum mod snum } : ${fnum mod snum }</li><!-- 위와 동일 -->
        		<li>\${"100"+100 } : ${"100" + 100 }</li><!-- 200 -->
        		<li>\${"HELLO"+      ' EL! ' } : \${"HELLO"+      ' EL! ' }</li><!-- EL에서 +는 숫자연산에만 사용가능, 문자열 연결시 에러(\해줌) -->
        	</ul>
        	<h4>EL의 비교 연산자</h4>
        	<c:set var="fnum" value="100"/><!-- pageContext.setAttribute 동일, 덮어씀 -->
        	<c:set var="snum" value="9"/>
        	<!-- 
        		EL에서 비교연산자를 이용한 비교연산시 JSTL에서 선언한 변수에 저장된 값을 모두 문자열로 인식하여 
        		String클래스의 compareTo()메소드 방식으로 비교한다
        		첫번째 문자부터 하나씩 비교함, 단 실제 숫자 비교시에는 정상적인 비교가능
        	 -->
        	<ul class="list-unstyled">
        		<li>\${fnum > snum } : ${fnum > snum }</li><!-- 한글자씩 비교하므로 false, (1>9 하는것) -->
        		<li>\${"100" > '9' } : ${"100" > '9' }</li><!-- 문자열 비교이므로 한글자씩, false -->
        		<li>\${"100" > 9 } : ${"100" > 9 }</li><!-- 100은 숫자로 바뀌므로 true -->
        		<li>\${'JSP' > 'JSP' } : ${'JSP' > 'JSP' }</li><!-- J끼리 같고.. 다음 문자도 계속 같으니 false -->
        		<li>\${'JSP' gt 'JSP' } : ${'JSP' gt 'JSP' }</li>
        		<!-- 
        			자바에서는 문자열 동등 비교시 equals()메소드로 비교하지만
        			EL에서는 ==로 비교
        		 -->
        		<li>\${'JSP' == 'JSP' } : ${'JSP' == 'JSP' }</li><!-- t -->
        		<li>\${'JSP' eq 'JSP' } : ${'JSP' eq 'JSP' }</li><!-- t -->
        		<li>\${'JSP' != 'JSP' } : ${'JSP' != 'JSP' }</li><!-- f -->
        		<li>\${'JSP' ne 'JSP' } : ${'JSP' ne 'JSP' }</li><!-- f, 이클립스에러 무시 -->
        	</ul>
        	<h4>EL의 논리 연산자</h4>
        	<ul class="list-unstyled">
        		<li>\${5 > 2 && 10 != 10 } : ${5 > 2 && 10 != 10 }</li><!-- f -->
        	 	<li>\${5 gt 2 and 10 != 10 } : ${5 gt 2 and 10 ne 10 }</li><!-- f -->
        		<li>\${5 >= 2 || 10 < 10 } : ${5 >= 2 || 10 < 10 }</li><!-- f -->
        	 	<li>\${5 ge 2 or 10 lt 10 } : ${5 ge 2 or 10 lt 10 }</li><!-- f -->
        	</ul>
        	<h4>EL의 삼항연산자</h4>
        	\${10 gt 9 ? '10은 9보다 크다' : '10은 9보다 크지않다' } : ${10 gt 9 ? '10은 9보다 크다' : '10은 9보다 크지않다' }<!-- t이므로 두번째 -->
        	<h4>EL의 empty 연산자</h4>
        	<!--
				null이거나
				""(빈문자열)이거나
				배열인 경우는 길이가 0이거나
				컬렉션인 경우는 size가 0인 경우 true반환
			
			   empty연산자 사용법 : \${empty el변수}
			   true나 false값이 계산된다
	   	  	-->
        	<%
        		//스크립팅요소에서 만든 변수는 EL에서 사용 불가
        		String nullString = null;
        		String emptyString = "";
        		Integer[] zeroLength = new Integer[0];
        		Collection zeroSize = new Vector();
        	%>
    		<!-- JSTL로 EL에서 사용할 변수 선언 -->    	
    		<c:set var="nullString" value="<%=nullString %>"/>
	    	<c:set var="emptyString" value="<%=emptyString %>"/>
	    	<c:set var="zeroLength" value="<%=zeroLength %>"/>
        	<c:set var="zeroSize" value="<%=zeroSize %>"/>
        	
        	<ul class="list-unstyled">
        		<li>\${empty nullString } : ${empty nullString }</li><!-- t -->
     		    <li>\${empty emptyString } : ${empty emptyString }</li><!-- t -->
     		    <li>\${empty zeroLength } : ${! empty zeroLength }</li><!-- f -->
      	        <li>\${empty zeroSize } : ${not empty zeroSize }</li><!-- f -->
        	</ul>
        	
        	
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>