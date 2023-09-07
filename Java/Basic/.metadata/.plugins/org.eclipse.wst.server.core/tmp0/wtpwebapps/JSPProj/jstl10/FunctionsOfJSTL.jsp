<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- core태그 라이브러리용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- 국제화 태그 라이브러리용 -->
<!-- 문자열이나 컬렉션 등을 처리하기위한 함수 라이브러리 -->
<!-- 사용법:EL에서 사용 .태그형태가 아님 \${접두어:함수(...)} -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 함수 라이브러리</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">함수들</legend>
        	<!--
        		JSTL에서 제공하는 함수들은 EL에서 호출 해야한다
                호출방법: \${접두어:함수명([매개변수들])}
             	JSTL의 모든 함수는 항상 값을 반환한다.
        	-->
        	<!--  데이타 준비 -->
        	<fmt:formatDate var="today" value="<%=new Date() %>" pattern="yyyy년 M월 dd일"/>
        	<c:set var="todayString">Today는 ${today }입니다.</c:set>
        	<c:set var="interString">Politics,Economics,Entertainments,Sports</c:set>
        	<c:set var="array" value='<%=new String[]{"한라산","성인봉","태백산","덕유산"} %>'/>
			<c:set var="collection" value="<%=new HashMap() %>"/>        
			<c:set target="${collection }" property="username" value="kosmo"/><!-- property가 키, collection에 저장함 -->
        	<c:set target="${collection }" property="password" value="kosmo9898"/>
        	<h3>int length(Object)</h3>
        	<!--
		     	문자열인 경우는 문자열의 길이 반환
		     	(한글 1자도 길이 1로 처리)
		     	배열인 경우는 배열의 크기(즉 메모리 개수) 반환
		    	컬렉션인 경우는 컬렉션에 저장된 객체의 수 반환
	     	-->
        	<ul class="list-unstyled">
        	${todayString }<!-- Today는 2023년 5월 25일입니다 --><hr/>
        		<li>문자열의 길이: ${fn:length(todayString) }</li><!-- 23 -->
        		<li>배열의 길이: ${fn:length(array) }</li><!-- 4 -->
        		<li>컬렉션의 길이(JSTL의 함수): ${fn:length(collection) }</li><!-- 2 -->
        		<li>컬렉션의 길이(컬렉션의 메소드): ${collection.size() }</li><!-- 2 -->
        	</ul>
        	<h5>컬렉션에 저장된 객체의 여부 판단</h5>
        	<c:if test="${fn:length(collection) != 0 }" var="result"><!-- 컬렉션에 들어간게 있으면 들어옴 -->
        		<c:forEach var="item" items="${collection }">
        			${item.key } : ${item.value }<br/>
        		</c:forEach>
        	</c:if>
        	<c:if test="${!result }">
        		저장된 객체가 없어요
        	</c:if>
        	<h3>String substring(추출할 대상 문자열, 시작인덱스, 끝인덱스)</h3><!-- 끝인덱스 -1까지 추출 -->
        	<!--
		       문자열에서 시작인덱스부터 끝인덱스-1까지 추출,
		       끝인덱스가 -1 인경우 시작인덱스부터 문자열 끝까지 추출
		       인덱스는 0부터 시작
		    -->
        	${todayString }
        	<ul class="list-unstyled">
        		<li>Today 추출 : ${fn:substring(todayString,0,5) }</li>
        		<li>날짜부분부터 끝까지 추출 : ${fn:substring(todayString,7,-1) }</li><!-- -1해도 되고, length해도 되고 -->
        		<li>날짜부분부터 끝까지 추출 : ${fn:substring(todayString,7,fn:length(todayString)) }</li>
        	</ul>
        	<h3>String substringAfter(대상문자열,str): str이후의 문자열을 추출</h3>
        	"Today"이후의 문자열 추출 : ${fn:substringAfter(todayString,"는 ") }<!-- 는 후에 한칸 띄워야 공백없이 출력 -->
        	<h3>String substringBefore(대상문자열,str): str이전의 문자열을 추출</h3>
        	"는 "이전의 문자열 추출 : ${fn:substringBefore(todayString,"는 ") }<!-- Today -->
        	<h3>String replace(대상문자열,src,dest): 대상문자열에서 src를 dest로 대체</h3>
        	${fn:replace(todayString,"Today","Tomorrow") }
        	<h4>문]todayString에서 Today는 Tomorrow로 5월 25일은 5월 26일로 replace하여라</h4>
        	<!-- 방법1: 임시변수 사용 -->
        	<c:set var="temp" value='${fn:replace(todayString,"Today","Tomorrow") }'/>
        	${fn:replace(temp,"25","26") }<br/>
        	<!-- 방법2: 바로 함수 전달 -->
        	${fn:replace(fn:replace(todayString,"Today","Tomorrow"),"25","26")}<br/>
        	<h3>String trim(대상문자열,src,dest): 양쪽의 공백 제거</h3>
        	트림 전 : 가${'    J   S T   L' }나<br/><!-- 트림 전 : 가 J S T L나 / 브라우저는 여러칸 띄워도 한칸 띄워짐, 소스보기에는 띄워져있음 --> 
        	트림 후 : 가${fn:trim('    J   S T   L')}나<br/><!-- 트림 후 : 가J S T L나 / 이번엔 소스보기에서도 다름 -->
        	<h3>boolean startsWith(대상문자열,str)</h3>
        	${fn:startsWith(todayString,"To") }<!-- To로 시작하므로 t -->
        	<h3>boolean endsWith(대상문자열,str)</h3>
        	${fn:endsWith(todayString,"다") }<!-- .으로 끝남 f -->
        	<h3>String[] split(대상문자열,delim)</h3>
        	<!-- ,로 잘라만든 배열에서 하나씩 추출 -->
        	<c:forEach var="item" items="${fn:split(interString,',') }">
        		${item }<br/>
        	</c:forEach>        	
        	<h3>String escapeXml(대상문자열): 태그를 그대로 출력하고자 할 때</h3><!-- out태그와 동일 -->
        	${fn:escapeXml("<h5>함수 라이브러리 사용</h5>") }<br/><!-- <h5>함수 라이브러리 사용</h5> -->
        	<c:out value="<h5>코어 태그 사용</h5>"/><br/><!-- <h5>함수 라이브러리 사용</h5> -->
        	<h3>String join(String[],str): 모든 배열의 요소를 str로 연결해서 하나의 문자열로 반환</h3>
        	${fn:join(array,'▲') }<!-- 한라산▲성인봉▲태백산▲덕유산 -->
        	<h3>int indexOf(대상문자열,str)</h3>
        	${fn:indexOf(todayString,"day") }<br/><!-- 2반환, 2번자리 -->
        	${fn:indexOf(todayString,"Day") }<br/><!-- 맞는 문자열 없으면 -1반환 -->
        	<h3>Boolean contains(대상문자열,str)</h3><!-- 문자열에 str 포함시 t -->
        	${fn:contains(todayString,"day") }<br/><!-- t -->
        	${fn:contains(todayString,"Day") }<br/><!-- f -->
        	<h3>Boolean containsIgnoreCase(대상문자열,str)</h3><!-- 문자열에 str 포함시 t -->
        	${fn:containsIgnoreCase(todayString,"day") }<br/><!-- t -->
        	${fn:containsIgnoreCase(todayString,"Day") }<br/><!-- t -->
        	<h3>String toUpperCase(대상문자열) 및 String toLowerCase(대상문자열)</h3>
        	${fn:toUpperCase(interString) }<br/><!-- 대문자로 바꿈 -->
        	${fn:toLowerCase(interString) }<br/><!-- 소문자로 바꿈 -->
        	
        	
        	
        	
        </fieldset>        
    </div><!--container-->
<j

	sp:include page="/template/Footer.jsp"/>