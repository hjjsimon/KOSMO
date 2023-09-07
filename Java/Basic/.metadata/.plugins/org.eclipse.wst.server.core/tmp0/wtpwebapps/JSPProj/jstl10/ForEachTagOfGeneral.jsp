<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- ForEachTagOfGeneral.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">forEach태그(일반 for문 형태)</legend>
        	<!--
				필수 속성: begin 및 end 그리고  var속성(일반 for문 중 3개 필수) 
			            step(0이상 정수만 가능)은 생략가능(생략시 1씩증가)	
	 		-->
        	<c:forEach begin="1" end="6" var="i">
        		<h${i }>제목${i }</h${i }>
        	</c:forEach>
        	<h1 class="display-4">varStatus속성</h1>
        	<!-- 
        		varStatus속성에 지정한 변수에는 반복과 관련된 정보를
        		추상화한 클래스인 LoopTagStatus객체가 저장됨
        	-->
        	<c:forEach begin="3" end="5" var="i" varStatus="loop"><!-- 이제 변수 loop에 LoopTagStatus객체가 할당됨 -->
        		<h5>${loop.count }번째 반복</h5><!-- 반복횟수가 저장되는 속성 -->
        		<ul class="list-unstyled">
        			<li>\${loop.index } : ${loop.index }</li><!-- begin의 숫자부터 시작 -->
        			<li>\${loop.first } : ${loop.first }</li>  <!-- 첫번째 반복이면 true,아니면 false 반호나하는 first속성 -->
        			<li>\${loop.last } : ${loop.last }</li>
        			<li>\${loop.current } : ${loop.current }</li><!-- 현재 반복중인 숫자 반환 -->
        			<li>\${loop.count } : ${loop.count }</li><!-- 현재 반복중인 숫자 반환 -->
        			
        			
        		</ul>
        	</c:forEach>
        	<h1 class="display-4">varStatus속성을 이용한 스타일 변경</h1>
        	<!--
        		문]반복이 짝수번째일때는 글자색을 RED로 홀수번째일때는 글자색을 GREEN으로
              	단, 첫번째 반복이라면 글자색을 BLUE로 마지막 반복이면 CYAN  
            -->
            <!-- 
        	<c:forEach begin="1" end="6" var="i" varStatus="loop">
        		<c:if test="${loop.current % 2 == 0 && loop.current != 6}">
        			<h${i } style="color:red;">제목${i }</h${i }>
        		</c:if>
        		<c:if test="${loop.current % 2 != 0 && loop.current != 1 }">
        			<h${i } style="color:green;">제목${i }</h${i }>
        		</c:if>
        		<c:if test="${loop.first}">
        			<h${i } style="color:blue;">제목${i }</h${i }>
        		</c:if>
        		<c:if test="${loop.last}">
        			<h${i } style="color:cyan;">제목${i }</h${i }>
        		</c:if>
        	</c:forEach>
        	 -->
        	<!-- first, last부터 해야 짝,홀수 해결가능 -->
        	<c:forEach begin="1" end="6" var="i" varStatus="loop">
        		<c:choose>
        			<c:when test="${loop.first}">
        				<c:set var="color" value="blue"/>
        			</c:when>
        			<c:when test="${loop.last}">
        				<c:set var="color" value="cyan"/>
        			</c:when>
        			<c:when test="${loop.count % 2 ==0}">
        				<c:set var="color" value="red"/>
        			</c:when>
        			<c:otherwise>
        				<c:set var="color" value="green"/>
        			</c:otherwise>
        		</c:choose>
        		<h${i } style="color:${color}">제목${i }</h${i }>
        	</c:forEach>
        	<h1 class="display-4">1부터 100까지 홀수의 합</h1>
        	<c:forEach begin="1" end="100" var="i">
        		<c:if test="${i mod 2 ne 0 }">
        			<c:set var="sum" value="${sum+i }"/>
        		</c:if>
        	</c:forEach>
        	${sum }
        	<h1 class="display-4">for문안의 for문(중첩for문)</h1>
        	<!-- 
        		10000
        		01000
        		00100
        		00010
        		00001 출력
        	 -->
        	<c:forEach begin="1" end="5" var="k">
        		<c:forEach begin="1" end="5" var="i">
        			<c:if test="${k==i }" var="result">1&nbsp;&nbsp;</c:if>
        			<c:if test="${!result }" var="result">0&nbsp;&nbsp;</c:if>
        		</c:forEach>
        		</br>
        	</c:forEach>
        	<!--
		  		문]구구단 출력
		  		  짝수행에 마우스 오버시 빨강
		  	   	  홀수행에 마우스 오버시 노랑
		  	   	  마우스 아웃시는 흰색 그리고 table태그를 사용해서 출력하여라
	   		-->
	   		<h1 class="display-4">구구단 출력</h1>
	   		<!-- 안쪽 for문은 컬럼, 바깥 for문은 행 -->
        	<table class="table table-bordered">
		        <tbody>
					<c:forEach begin="1" end="9" var="i" varStatus="loop">
						<c:choose>
							<c:when test="${loop.count %2 ==0 }">
								<c:set var="color" value="red"/>
							</c:when>
							<c:otherwise>
								<c:set var="color" value="yellow"/>
							</c:otherwise>
						</c:choose>
						<tr onmouseover="this.style.backgroundColor='${color}'" onmouseout="this.style.backgroundColor='white'">
							<c:forEach begin="2" end="9" var="k">
								<td>${k } * ${i } = ${k*i }</td>
							</c:forEach>
						</tr>
					</c:forEach>
		        </tbody>
		    </table>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>