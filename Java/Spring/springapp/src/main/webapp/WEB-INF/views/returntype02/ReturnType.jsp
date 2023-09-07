<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>Controller Method</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">컨트롤러 메소드</legend>
        	<!-- 
	        	정리: @Controller는 원래 페이지만 응답, 데이터 보내는 방법 2개 존재 
		       		1) @Controller void 반환
		       		2) @Controller String 반환 + @ResponseBody 사용 
        	 -->
        	<h3>반환 타입들</h3>
	    	<ul class="list-unstyled">
	    		<li><a href="<c:url value="/ReturnType/ModelAndView.do?returnType=ModelAndView!!!"/>">ModelAndView반환</a></li>
	    		<!-- 데이터나 로직을 갖는 객체가 Model, 뿌려주는게 View, ModelAndView는 데이터와 뷰정보 동시 저장가능 -->
	    		
	    		<li><a href="<c:url value="/ReturnType/String.do?returnType=String!!!"/>">String반환(뷰정보 반환)</a></li>
	    		<!-- 이건 데이터 못담고 뷰정보만 반환 -->
	    		
	    		<li><button id="ajax" class="btn btn-info">void 타입반환(@Controller어노테이션 사용시에 클라이언트로 페이지가 아닌 데이타(JSON형식으로 문자열 생성)만 보낼때)</button></li>
	    		<!-- @Controller는 원래 페이지만 응답으로 보내줌, 근데 @RestController(데이터만 보냄) 쓰지 않고도 void하면 데이터만 보내기 가능 -->
	    		
	    		<li><a href="<c:url value="/ReturnType/Object.do?returnType=Object!!!"/>">객체 타입(자바빈 혹은 컬렉션)반환(@ResponseBody어노테이션-페이지가 아닌 데이타(JSON-컨버터가 자동로 생성해줌)만 보낼때)-브라우저로 바로 출력시</a></li>
	    		<!-- 자바빈(좁은의미), 컬렉션 둘 다 객체, 객체타입을 반환, @ResponseBody 이것도 데이터만 보냄, 이 때는 String씀 -->
				<!-- 응답바디에 데이터를 실어보내는 것 -->
	    		<!-- 스프링부트는 Jackson이 자동으로 설치 됨, 신경안쓰고 막 반환해도 클라이언트가 JSON {} 으로 받게됨 -->
	    		<!-- {} JSON은 자바스크립트에서 쓰는 타입이니 자바언어가 모름 -->
	    		<!-- 사용자가 서버에 JSON 형태로 {키:밸류}로 요청, 이 때 자바는 모름, Map으로 바꿔서 읽어줘야함 -->
	    		<!-- JSON을 Map으로 바꿔주는게 컨버터임, Jackson라이브러리가 해줌 -->
	    		<!-- 자바로 Map 반환시에도 자바스크립트는 Map 모름, JSON으로 바꿔줘야함, 이 때도 컨버터 필요, Jackson라이브러리가 해줌 -->
	    		
	    		<li><a href="javascript:printConsole()">객체 타입(자바빈 혹은 컬렉션)반환(@ResponseBody어노테이션-페이지가 아닌 데이타(JSON-컨버터가 자동로 생성해줌)만 보낼때)-콘솔로 바로 출력시</a></li>
	    		<!-- 나중에는 자바스크립트로 DOM의 특정 부분에 출력시켜야함 -->
	    	</ul>
	    	<h3>결과 값 출력</h3>
	    	<ul class="list-unstyled">
	    		<li>\${requestScope.message }  : ${requestScope.message }</li>
	    		<li>\${requestScope.returnType }  : ${requestScope.returnType }</li>
	    		<li>\${param.returnType }  : ${param.returnType }</li>
	    	</ul>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>
<script>
 
	//1]제이쿼리에 ajax API를 써서 버튼 post처리
	/*
	$('#ajax').click(function(){
		 console.log('버튼 클릭!!');
		 $.ajax({
			 url:"<c:url value="/ReturnType/Void.do?returnType=Void!!!"/>",
			dataType:"json"//데이터를 서버에서 JSON으로 받겠다는 뜻
			method:'get'//메소드 디폴트가 겟(안써도됨)
			
		 }).done((data)=>{
			 console.log("서버로부터 받은 데이타:",data);
			 console.log("아이디:%s,비밀번호:%s,파라미터:%s",data.username,data['password'],data.parameter);//JSON으로 아이디,비번 보냄
		 }).fail((error)=>{
			 console.log(error);//에러출력
		 });
	 });
 	 */
	
	 
	 //2]바닐라 자바스크립트로 버튼 post,get 처리
	 var button = document.querySelector('#ajax');
	 button.onclick = function(e){
		 // XMLHttpRequest 객체 생성
		 var xhr = new XMLHttpRequest();
		 // 요청 메서드 및 URL 설정
		 //2-1]POST처리
		 xhr.open("POST", "<c:url value="/ReturnType/Void.do"/>", true);//비동기 true
		 //2-2]GET처리
		 xhr.open("GET", "<c:url value="/ReturnType/Void.do?returnType=Void!"/>", true);
		 // 요청 헤더 설정 - POST방식일때는 반드시 요청헤더에 Content-Type설정(GET은 넣든말든무관)
		 xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		 // 요청 파라미터 생성
		 var params = "returnType = Void!";
		 // 요청 완료 후 처리할 콜백 함수 설정
		 xhr.onreadystatechange = function() {
		 	if (xhr.readyState === XMLHttpRequest.DONE) {//요청이 완료된상태가 DONE
		 		if (xhr.status === 200) {//정상응답시 200
			      	// 요청이 성공적으로 완료되었을 때 처리할 로직
			     	console.log('서버로부터 받은 데이터:',xhr.responseText);//Object
			     	console.log('서버로부터 받은 데이터의 타입:', typeof xhr.responseText);//String
			     	//JSON.parse: 문자열을 JSON으로 
				    //JSON.stringify: JSON을 문자열로
			     	var data = JSON.parse(xhr.responseText);
			     	console.log("아이디:%s,비밀번호:%s,파라미터:%s",data.username,data['password'],data.parameter);
			     }
			     else {
				    // 요청이 실패했을 때 처리할 로직
				    console.error(xhr.status);
			     }
	   		}
		 };
		 // 요청 전송
		 //xhr.send(params);//params 넣으면 파라미터를 POST방식으로 전송, var params = "returnType = Void!"; 
		 xhr.send();//GET방식 요청(GET은 쿼리스트링으로 해줌)
	 };

	 function printConsole(){
		
		 $.ajax({
			 url:"<c:url value="/ReturnType/Object.do?returnType=Object!!!"/>",
			dataType:"json",//데이터를 서버에서 JSON으로 받겠다는 뜻
			method:'get'//메소드 디폴트가 겟(안써도됨)
			
		 }).done((data)=>{
			 console.log("서버로부터 받은 데이타:",data);
			 console.log("아이디:%s,비밀번호:%s,파라미터:%s",data.username,data['password'],data.parameter);//JSON으로 아이디,비번 보냄
		 }).fail((error)=>{
			 console.log(error);//에러출력
		 });
	 }
	 
	 
	 
	 


</script>