<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>                 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">if태그</legend>
        	<c:set var="numvar" value="100"/>
        	<c:set var="strvar" value="JSTL"/>
        	<h3>if태그 기본</h3>
        	<!-- 
        		if태그는 시작태그와 종료태그를 사용해서 판단(양끝 태그 필수)
          		else문이 없다(그래서 var에 값을 지정 후 밑에 ! var명 으로 else처리가 가능하다)
          		
        	 -->
        	<c:if test="${numvar mod 2 eq 0 }" var="result"><!-- 100을 2로 나눈 나머지 0임,true/ 조건문의 결과값(t/f)가 var에 저장 -->
        		${numvar }는 짝수<br/>
        	</c:if>	
        	\${result} : ${result}<br/>
        	<c:if test="${not result }"><!-- 결과가 f라면 t가되어 아래값 출력 -->
        		${numvar }는 홀수<br/>
        	</c:if>
        	<!-- 위의 if(JSTL)을 EL의 삼항연산자로 변경 -->
        	<h3>EL의 삼항연산자로 짝홀수 판단</h3>
        	${numvar }는 ${numvar mod 2 eq 0?"짝수" : "홀수" }
        	<h3>문자열 비교</h3>
        	<c:if test="${strvar=='jstl'}" var="result"><!-- 대소문자 가림 주의, f -->
        		${strvar }는 "jstl"이다<br/>
        	</c:if>
        	<c:if test="${!result }">
        		${strvar }는 "jstl"이 아니다<br/>
        	</c:if>
        	<h3>EL의 true/false불린값 사용 </h3><!-- EL에도 불린값 존재 -->
        	<c:if test="${true }">
        		항상 출력되는 HTML태그<br/>
        	</c:if>
        	<c:if test="${false }">
        		항상 출력안되는 HTML태그<br/>
        	</c:if>
        	<h3>test속성에 일반값으로 조건 설정</h3>
        	<!--
			 	※test속성에 EL식이 아닌 일반 값을
			 	넣으면 무조건 거짓(false/자스는 true)
			 	단,일반 값이라도 TRUE(true)인(대소문자 상관없이) 경우는 true로 판단
			 	또한 EL식이더라도 test="" 내부에는 \${}양쪽에 빈 공백이 들어가면 무조건 거짓(주의, EL내부 공백은 상관없음)	 	
		  	-->
        	<c:if test="100" var="reuslt"><!-- 아래 출력되면 사실 var 필요 없음, 그냥 true임 -->
        		100은 참이다
        	</c:if>
        	\${result} : ${result}<br/><!-- f -->
        	<c:if test="tRuE" var="result"><!-- 이것도 절대 빈공백없게하기 -->
        		"tRuE"는 참이다<br/>
        	</c:if>
        	<c:if test="100 > 10"><!-- 맞는말인데? 출력안됨.. EL로 해줘야함 -->
        		'100 > 10'는 참이다1<br/>
        	</c:if>
        	<c:if test="${100 > 10 }"><!-- EL내부 공백은 괜찮음 -->
        		'100 > 10'는 참이다2<br/>
        	</c:if>
        	<c:if test=" ${100 > 10 } " var="space"><!-- ""내부 양쪽공백 들어가서 거짓됨,출력x -->
        		'100 > 10'는 참이다3<br/>
        	</c:if>
        	\${space } : ${space }<br/><!-- 위 거짓이라 이거 f 나옴 -->
        	<h3>if태그 연습</h3>
        	<!--
			   문]파라미터로 아이디(user)와 비밀번호(pass)를
			   받아서(주소에 ?user=KIM&pass=1234 쿼리스트링추가)
			   아이디가 "KIM"이고 비밀번호가 "1234"인 경우
			   회원이라고 가정하자.
			   회원인 경우 "\${EL로 출력} 님 즐감하세요" 출력
			   비회원인 경우 "아뒤와 비번이 틀려요" 출력(처음부터 뜨면 안됨)
			   단,EL과 JSTL만 사용해서 구현해라
			-->
			
			<!-- 
			<c:if test="${empty param.id or empty param.pwd }"></c:if>
        	<c:if test="${param.id eq 'KIM' and param.pwd eq '1234'}">
        		${param.id }님 즐감하세요
        	</c:if>
        	<c:if test="${ (!empty param.id and !empty param.pwd) and (param.id ne 'KIM' or param.pwd ne '1234')}">
        		아뒤와 비번이 틀려요
        	</c:if>
        	-->
        	
        	<c:set var="username" value="${param.user }" />
        	<c:set var="password" value="${param.pass }" />
        	<c:if test="${! empty username && not empty password}">
        		<!-- 회원인 경우 -->
        		<c:if test="${username == 'KIM' and password eq '1234'  }" var="isMember">
        			${username }님 즐감하세요
        		</c:if>
        		<!-- 비회원인 경우 -->
        		<c:if test="${!isMember }">
        			아뒤와 비번이 틀려요
        		</c:if>
        	</c:if>
        	
        	
        	
        	
        	
        	
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>