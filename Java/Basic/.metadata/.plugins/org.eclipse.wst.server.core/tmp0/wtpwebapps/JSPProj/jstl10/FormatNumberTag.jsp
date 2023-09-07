<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- core태그 라이브러리용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- 국제화태그 라이브러리용 -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>국제화 태그</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">formatNummber태그</legend><!-- 숫자데이터를 형식에 맞춰 처리함 -->
        	<!-- 
        		 필수 속성: value
				 type속성의 기본값:number
			     groupingUsed속성의 기본값:true(1000단위로 콤마를 찍는다)
		 	-->
		 	<c:set var="money" value="10000"/>
		 	<h5>EL로 출력</h5>
		 	${money }
		 	<h5>JSTL 국제화태그로 출력: 필수 속성만 사용</h5>
		 	<fmt:formatNumber value="${money }" />
		 	<h5>JSTL 국제화태그로 출력: type="currency" 현지 통화단위로 출력</h5>
		 	<fmt:formatNumber value="${money }" type="currency"/>
		 	<h5>JSTL 국제화태그로 출력: type="currency" 통화단위 $로 출력</h5>
		 	<fmt:formatNumber value="${money }" type="currency" currencySymbol="$"/>
		 	<h5>JSTL 국제화태그로 출력: type="percent" 숫자 * 100으로출력</h5>
		 	<fmt:formatNumber value="${money }" type="percent"/><br/>
		 	<fmt:formatNumber value="${5/100 }" type="percent"/>
		 	<h5>원하는 위치에 출력</h5>
		 	<fmt:formatNumber value="${money }" type="currency" var="price"/>
		 	가격이 ${price } 내렸어요
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>