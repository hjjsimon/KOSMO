<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>국제화 태그</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">formatDate 태그</legend>
        	<!--
				 value는 필수속성 ,단 날짜타입(java.util.Date타입)이 와야됨
				 type은 기본값:date
			     dateStyle 기본값 :default
		 	-->
        	<c:set var="today" value="<%=new Date() %>"/>
        	<h4>EL로 출력</h4>
        	\${today } : ${today }<!-- Thu May 25 14:41:42 KST 2023 -->
        	<h4>JSTL의 국제화 태그로 출력</h4>
        	<!-- 자바에서 simpleDateFormat으로 간편하게 썼듯, 여기서도 그런 느낌 -->
        	<fmt:formatDate value="${today }"/><!-- 2023. 5. 25. 위에보다 나음 -->
        	<h5>type="date"(디폴트), dateStyle="default"</h5>
        	<fmt:formatDate value="${today }" type="date" dateStyle="default"/><!-- 2023. 5. 25. -->
        	<h5>type="date"(디폴트), dateStyle="short"</h5>
        	<fmt:formatDate value="${today }" type="date" dateStyle="short"/><!-- 23. 5. 25. 말그대로 짧게 출력 -->
        	<h5>type="date"(디폴트), dateStyle="short"</h5>
        	<fmt:formatDate value="${today }" type="date" dateStyle="medium"/><!-- default랑 동일, default가 medium임 -->
        	<h5>type="date"(디폴트), dateStyle="long"</h5>
        	<fmt:formatDate value="${today }" type="date" dateStyle="long"/><!-- 2023년 5월 25일 -->
        	<h5>type="date"(디폴트), dateStyle="full"</h5>
        	<fmt:formatDate value="${today }" type="date" dateStyle="full"/><!-- 2023년 5월 25일 목요일 -->
        	<hr/>
        	<h5>type="time", dateStyle="default"</h5>
        	<fmt:formatDate value="${today }" type="time" timeStyle="default"/><!-- 오후 2:47:29 -->
        	<h5>type="time", dateStyle="short"</h5>
        	<fmt:formatDate value="${today }" type="time" timeStyle="short"/><!-- 오후 2:50 -->
        	<h5>type="time", dateStyle="medium"</h5>
        	<fmt:formatDate value="${today }" type="time" timeStyle="medium"/><!-- 오후 2:50:17 -->
        	<h5>type="time", dateStyle="long"</h5>
        	<fmt:formatDate value="${today }" type="time" timeStyle="long"/><!-- 오후 2시 50분 17초 KST -->
        	<h5>type="time", dateStyle="full"</h5>
        	<fmt:formatDate value="${today }" type="time" timeStyle="full"/><!-- 오후 2시 50분 17초 대한민국 표준시 -->
        	<h5>pattern속성 - type속성 의미없다(type쓰지마 pattern이 짱임)</h5>
        	<fmt:formatDate value="${today }" pattern="yyyy-MM-dd a HH:mm:ss"/><br/>
        	<fmt:formatDate value="${today }" pattern="yyyy년 MM월 dd일 a HH시 입니다"/><br/>
        	<fmt:formatDate value="${today }" pattern="yyyy년 MM월 dd일 EEEE"/><br/>
        	<h5>var속성 지정해서 원하는 위치에 출력</h5>
        	<fmt:formatDate value="${today }" pattern="yyyy년 MM월 dd일 EEEE" var="promise"/><br/>
        	모임 날짜는 ${promise} 입니다<!-- 모임 날짜는 2023년 05월 25일 목요일 입니다 -->
        	
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>