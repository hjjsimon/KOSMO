<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!-- 
1. HTML태그에 URL패턴을 생성(A태그의 href속성 혹은 FORM의 action속성)
2. 컨트롤러 생성
 -->
    
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Hello Spring!!!<small>${requestScope.today }</small></h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">스프링 프레임워크</legend>
        	<h2>1.static resource(정적자원) 표시방법<small>(이미지,동영상,.css,.js파일 등)</small></h2>
        	
        	<h4><kbd>디폴트 설정 사용시(설정 불필요)</kbd></h4>
        	<!-- 
        	#spring.mvc.static-path-pattern=/static/** 로 주석처리시 아래 value="/static/images~~"에서 /static 빼기 
        	-->
        	<img class="img-thumbnail" src="<c:url value="/images/thumbnail.jpg"/>" alt="풍경 이미지"/>
        	<!-- 프로퍼티스에서 컨텍스트패스 지정안했으니 /springapp안붙음 -->
        	<img class="img-thumbnail" src="/images/thumbnail.jpg" alt="풍경 이미지"/>
        	<!-- 이건 주석빼도 /springapp 안붙음, 결론: c:url 쓰는게 좋다, 컨텍스트루트 무관 알아서 된다 -->
        	
        	<h4><kbd>디폴트 설정 미사용시(설정 필요)</kbd></h4>
        	<!-- 
        	spring.mvc.static-path-pattern=/resources/** 설정시 src/main/resources 하위부터 찾음
        	반드시 /resources 써야함, 위 2개 안나옴, 아래만 나옴 
        	-->
        	<img class="img-thumbnail" src="<c:url value="/resources/images/thumbnail.jpg"/>" alt="풍경 이미지"/>
        	
        	<h4><kbd>WebMvcConfigurer로 리소스 핸들러 추가(설정 불필요,WebConfiguration 클래스 필요)</kbd></h4>
        	<img class="img-thumbnail" src="<c:url value="/virtual/images/thumbnail.jpg"/>" alt="풍경 이미지"/>
        	
        	<h2>2.Controller <small><a href="<c:url value="/controller.do"/>">컨트롤러</a></small></h2>
        	<!-- /controller.do 이 패턴의 url을 컨트롤할놈필요 -->
        	
        	
        	<h2>3.Controller Method <small><a href="<c:url value="/returntype.do"/>">컨트롤러 메소드의 반환타입</a></small></h2>
        	
        	
        	<h2>4.Dependency Injection <small><a href="<c:url value="/injection.do"/>">의존성 주입</a></small></h2>
        	
        	
        	<h2>5.Annotation <small><a href="<c:url value="/annotation.do"/>">어노테이션</a></small></h2>
        	
        	
        	<h2>6.Properties <small><a href="<c:url value="/properties.do"/>">속성 파일(.properties파일)</a></small></h2>
        	
        	
        	<h2>7.Database <small><a href="<c:url value="/database.do"/>">데이터베이스</a></small></h2>
        	
        	
        	<h2>8.Validation <small><a href="<c:url value="/validation.do"/>">유효성 검증(서버측에서)</a></small></h2>
        	
        	
        	<h2>9.MyBatis & Dynamic SQL <small><a href="<c:url value="/mybatis.do"/>">마이바티스 및 동적SQL</a></small></h2>
        	
        	
        	<h2>10.jQuery Ajax<small><a href="<c:url value="/ajax.do"/>">제이쿼리 에이작스</a></small></h2>
        	
        	
        	<h2>11.Excpetion<small><a href="<c:url value="/exception.do"/>">예외처리</a></small></h2>
        	
        	
        	<h2>12.File Upload/Download<small><a href="<c:url value="/fileupdown"/>">파일 업로드/다운로드</a></small></h2>
        	
        	
    	    <h2>13.AOP(Aspect Oriented Programming)<small><a href="<c:url value="/aop"/>">관심지향 프로그래밍</a></small></h2>
        	
        	
        	<h2>14.WebSocket<small><a href="<c:url value="/websocket"/>">웹소켓</a></small></h2>
        	
        	
        	<h2>15.Spring Tiles<small><a href="<c:url value="/tiles"/>">스프링 타일즈</a></small></h2>
        	
        	
        	<h2>16.Teachable Machine<small><a href="<c:url value="/teachable"/>">이미지 분류</a></small></h2>
        	
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>