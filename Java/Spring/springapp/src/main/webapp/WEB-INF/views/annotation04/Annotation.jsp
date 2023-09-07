<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>Annotation</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">어노테이션</legend>
        	<h1 class="display-4">@RequestMapping/@PathVariable</h1>
        	<h5>@RequestMapping("/요청URL") : 모든 HTTP메소드 요청처리 가능</h5>
        	<form class="form-inline" action="<c:url value="/Annotation/RequestMappingBoth.do"/>" method="post">
				<label>아이디</label> 
				<input type="text" name="id" class="form-control mx-2" /> 
				<label>비밀번호</label> 
				<input type="password" name="pwd" class="form-control mx-2" /> 
				<input type="submit" class="btn btn-danger mx-2" value="로그인" />
			</form>
			
			<h5>@RequestMapping(value="/요청URL",method=RequestMethod.GET) : HTTP메소드중 하나만 처리 가능</h5>
			<form class="form-inline" action="<c:url value="/Annotation/RequestMappingOne.do"/>" method="get">
				<label>아이디</label> 
				<input type="text" name="id" class="form-control mx-2" /> 
				<label>비밀번호</label> 
				<input type="password" name="pwd" class="form-control mx-2" /> 
				<input type="submit" class="btn btn-danger mx-2" value="로그인" />
			</form>
			<c:if test="${! empty loginInfo}">
        		<kbd>${loginInfo }</kbd>
        	</c:if>
        	
        
        	<h1 class="display-4">@RequestParam</h1>
        	<!--
			  	예]
				//컨트롤러 메소드]
			  	public String 메소드(@RequestParam 자료형 매개변수){
			  	
			  	}
			  	
			    단, 파라미터명과  매개변수명이 일치하지 않은 경우(보통 일치시킴)
			 	@RequestParam("파라미터명") 자료형 매개변수명
				- 해당 파라미터의 자료형으로 받을 수 있다 즉 형변환 불필요(파라미터 어차피 String 아니면 int, 이때 위의 자료형에 int쓰면 편함)
				- 여러개 일때는 자료형을 Map으로
			-->
			<span class="text-danger">${error}</span>
			<h5>파라미터명과 매개변수명 일치시</h5>
			<form class="form-inline" action="<c:url value="/Annotation/RequestParamEqual.do"/>" method="POST">
				<label>이름</label> 
				<input type="text" name="name" class="form-control mx-2" /> 
				<label>나이</label> 
				<input type="text" name="age" class="form-control mx-2" /> 
				<input type="submit" class="btn btn-danger mx-2" value="확인" />
			</form>
			<h5>파라미터명과 매개변수명 불 일치시</h5>
			<form class="form-inline" action="<c:url value="/Annotation/RequestParamDiff.do"/>" method="POST">
				<label>이름</label> 
				<input type="text" name="nickname" class="form-control mx-2" /> 
				<label>나이</label> 
				<input type="text" name="years" class="form-control mx-2" /> 
				<input type="submit" class="btn btn-danger mx-2" value="확인" />
			</form>
			<kbd>이름 : ${name}, 10년후 나이 : ${age }</kbd>
			
			<h5>맵으로 모든 파라미터 받기</h5>
			<form action="<c:url value="/Annotation/RequestParamMap.do"/>" method="post">
				<div class="form-group">
					<label><kbd class="lead">아이디</kbd></label> 
					<input type="text" value="${param.id}" class="form-control" name="id" placeholder="아이디를 입력하세요">
				</div>
				<div class="form-group">
					<label><kbd class="lead">성별</kbd></label>
					<div class="d-flex">
						<div class="custom-control custom-radio mr-2">
							<input type="radio" class="custom-control-input" name="gender" value="man" id="male"
							<c:if test="${ gender=='man' }">checked</c:if> /> 
							<label for="male" class="custom-control-label">남자</label>
						</div>
						<div class="custom-control custom-radio">
							<input type="radio" class="custom-control-input" name="gender" value="woman" id="female"
							<c:if test="${gender=='woman' }">checked</c:if>> 
							<label for="female" class="custom-control-label">여자</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label><kbd class="lead">관심사항</kbd></label>
					<div class="d-flex">
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" name="inter"
								value="POL" id="POL"> <label class="custom-control-label"
								for="POL">정치</label>
						</div>
						<div class="custom-control custom-checkbox mx-2">
							<input type="checkbox" class="custom-control-input" name="inter"
								value="ECO" id="ECO"> <label class="custom-control-label"
								for="ECO">경제</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" name="inter"
								value="ENT" id="ENT"> <label class="custom-control-label"
								for="ENT">연예</label>
						</div>
						<div class="custom-control custom-checkbox ml-2">
							<input type="checkbox" class="custom-control-input" name="inter"
								value="SPO" id="SPO"> <label class="custom-control-label"
								for="SPO">스포츠</label>
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">확인</button>
			</form>
			<kbd>아이디:${id},성별:${gender},관심사항:${inter}</kbd>
	        	
	       	<h1 class="display-4">@ModelAttribute</h1> 	
        	<form action="<c:url value="/Annotation/ModelAttribute.do"/>" method="post">
				<div class="form-group">
					<label><kbd class="lead">이름</kbd></label> 
					<input type="text" class="form-control" name="name" placeholder="이름를 입력하세요">
				</div>
				<div class="form-group">
					<label><kbd class="lead">아이디</kbd></label> 
					<input type="text" class="form-control" name="id" placeholder="아이디를 입력하세요">
				</div>
				<div class="form-group">
					<label><kbd class="lead">비밀번호</kbd></label>  
					<input type="password" class="form-control" name="pwd" placeholder="비밀번호를 입력하세요">
				</div>
				<div class="form-group">
					<label><kbd class="lead">성별</kbd></label>
					<div class="d-flex">
						<div class="custom-control custom-radio mr-2">
							<input type="radio" class="custom-control-input" name="gender" value="man" id="male1"> 
							<label for="male1" class="custom-control-label">남자</label>
						</div>
						<div class="custom-control custom-radio">
							<input type="radio" class="custom-control-input" name="gender" value="woman" id="female1"> 
							<label for="female1" class="custom-control-label">여자</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label><kbd class="lead">관심사항</kbd></label>
					<div class="d-flex">
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" name="inter" value="POL" id="POL1"> 
							<label class="custom-control-label" for="POL1">정치</label>
						</div>
						<div class="custom-control custom-checkbox mx-2">
							<input type="checkbox" class="custom-control-input" name="inter" value="ECO" id="ECO1"> 
							<label class="custom-control-label" for="ECO1">경제</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" name="inter" value="ENT" id="ENT1"> 
							<label class="custom-control-label" for="ENT1">연예</label>
						</div>
						<div class="custom-control custom-checkbox ml-2">
							<input type="checkbox" class="custom-control-input" name="inter" value="SPO" id="SPO1"> 
							<label class="custom-control-label" for="SPO1">스포츠</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label><kbd class="lead">학력사항</kbd></label> 
					<select name="grade" class="custom-select mt-3 custom-select-lg">
						<option value="">학력을 선택하세요</option>
						<option value="ELE">초등학교</option>
						<option value="MID">중학교</option>
						<option value="HIG">고등학교</option>
						<option value="UNI">대학교</option>
					</select>
				</div>
				<div class="form-group">
					<label><kbd class="lead">첨부파일</kbd> </kbd></label>
					<div class="custom-file">
						<input type="file" class="custom-file-input" name="file" id="file1">
						<label class="custom-file-label" for="file1">파일을 첨부하세요</label>
					</div>
				</div>
				<div class="form-group">
					<label><kbd class="lead">자기소개</kbd> </kbd></label>
					<textarea class="form-control" rows="5" name="self"></textarea>
				</div>
				<button type="submit" class="btn btn-primary">확인</button>
			</form>
        	
       		<h1 class="display-4">DI와 관련된 어노테이션</h1> 	
       		<h5>@Autowired(의존성 주입을 위한 어노테이션)</h5>
       		<!-- 컨테이너가 필요한 객체에 주입해줌(=wiring), 제일 많이 쓰는 어노테이션 -->
        	<a href="<c:url value="/Annotation/Autowired.do"/> ">오토와이어링</a>
        	
        	<h5>@Lazy(지연로딩을 위한 어노테이션)</h5><!-- 생성자 출력되는거보면 서버 시작과 동시에됨, 다 프리로딩임 -->
        	<a href="<c:url value="/LazyLoading/Lazy.do"/> ">지연로딩</a>
        	
        	<h5>@Configuration(컨테이너에 등록할 빈을 자바코드로 작성할 경우 사용하는 어노테이션)</h5>
        	<a href="<c:url value="/Configuration/Configuration.do"/> ">자바코드로 빈 등록</a>
        	
        	<div><kbd>${message }</kbd></div>
        	
        	<h1 class="display-4">기타 어노테이션들</h1>
        	<h5>@SessionAttribute(세션으로 인증을 위한 어노테이션)</h5>
        	<!-- HttpSession써도 되지만 어노테이션 권장함 -->
        	<!-- HttpSession 혹은 @SessionAttribute(맵으로 파라미터 받기)으로 인증처리시 -->
        	<!-- isNotLogin가 true면 로그인 안된것 -->
        	<c:if test="${empty sessionScope.id and empty sessionScope.authenticationCommand}" var="isNotLogin">
	        	<h6 class="font-weight-bolder">로그인 전</h6>
				<form class="form-inline" action="<c:url value="/Annotation/SessionAttributeLogin.do"/>" method="GET">
					<label>아이디</label> 
					<input type="text" name="id"class="form-control mx-2" /> 
					<label>비밀번호</label> 
					<input type="password" name="pwd" class="form-control mx-2" /> 
					<input type="submit" class="btn btn-danger mx-2" value="로그인" />
				</form>
				<kbd>에러메시지 : ${loginError}</kbd>
			</c:if>
			<c:if test="${! isNotLogin }">
	        	<h6 class="font-weight-bolder">로그인 후</h6>
				<!--@SessionAttribute 사용시 모델계열에 데이타 저장하면 두 영역에 동시에 저장됨  -->
				<kbd>세션영역 : ${sessionScope.id}${sessionScope.loginCommand.id}${sessionScope.authenticationCommand.id}</kbd>
				<br/>
				<kbd>리퀘스트 영역 : ${requestScope.id}${requestScope.loginCommand.id}${requestScope.authenticationCommand.id}</kbd>
				<br/>
				<kbd>${sessionScope.id }${sessionScope.loginCommand.id}${sessionScope.authenticationCommand.id} 님 즐감하세요</kbd>
				<a href="<c:url value="/Annotation/SessionAttributeLogout.do"/>">로그아웃</a>
        	</c:if>
        	<hr />
			<h6 class="font-weight-bolder">로그인 여부 판단</h6><!-- 로그인 안된 상태에서 누르면 에러남 -->
			<a href="<c:url value="/Annotation/SessionAttributeIsLogin.do"/>">로그인여부 판단</a>
			<br />
			<kbd>서블릿 API(HttpSession 사용시) : ${isLoginMessageAPI}</kbd>
			<br />
			<kbd>@SessionAttribute 사용시 : ${isLoginMessage}</kbd>
			
			<hr/>
			<h5>@ResponseBody(응답바디에 출력을 위한 어노테이션)</h5><!-- String이 그대로 브라우저에 나옴 -->
			<a href="<c:url value="/Annotation/ResponseBody.do"/>">응답바디</a>
			
			<hr/>
			<h5>@RequestBody(요청바디의 JSON형식의 컨텐츠를 읽기 위한 어노테이션, 매우 중요)</h5>
			<h6 class="font-weight-bolder">서버로 JSON타입의 데이타 보내기</h6>
			<a href="javascript:json()">JSON데이타(데이터는 아래 폼태그에 입력하고 테스트하기)</a> 
			<!-- 아이디랑 비번에 값 입력후 a태그 누르면 누른 값 그대로 나옴 -->
			<h6 class="font-weight-bolder">서버로 폼데이타 보내기</h6>
			<form class="form-inline" action="<c:url value="/Annotation/RequestBody.do"/>" method="POST">
				<label>아이디</label> 
				<input type="text" name="id" class="form-control mx-2" id="id"/> 
				<label>비밀번호</label> 
				<input type="password" name="pwd" class="form-control mx-2" id="pwd"/> 
				<input type="submit" class="btn btn-danger mx-2" value="로그인" />
			</form>
			
			<h5>@RequestHeader(요청헤더를 읽기 위한 어노테이션, 매우 중요)</h5>
			<a href="<c:url value="/Annotation/RequestHeader.do"/>">요청헤더</a>
			<kbd>${userAgent }</kbd>
			
			
			
			
			
			
        	
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>
<script>

	//※폼데이터로 데이터 보내면 json형식 아님, key=value 쌍으로 여러개면 &로 엮여 전송됨************************
	//요청헤더에 포함시 GET방식, 요청바디에 포함시 POST방식
	//폼태그 말고 자바스크립트로 서버에 데이터를 보내면 폼태그처럼 key=value형식이나 json형식으로 2가지 보내기 가능
	//이번엔 key=value형식이 아닌 json형식으로 데이터 보내기
	function json(){
		var data = {id:$('#id').val(), pwd:$('#pwd').val()}; 
		//자스에서 키는 감쌀필요 없음, 밸류는 ''나 ""로 감싸야함, 제이쿼리는 당연히 다르고 지금 제이쿼리 
		//json은 객체
		console.log(data); //{id: '', pwd: ''}
		console.log(JSON.stringify(data));//객체를 스트링으로 바꿈
		//1.jQuery Ajax 사용
		//https://api.jquery.com/jquery.ajax/
		$.ajax({
			url: '<c:url value="Annotation/RequestBody.do"/>',//리퀘스트바디 처리할 컨트롤러 필요
			method: 'POST',
			data: JSON.stringify(data), //반드시 문자열로 바꿔서 보내야 받을 수 있음
			contentType: "application/json", //내가 보내는 형태는 JSON형식이야
			dataType: 'json'
			
		}).done(data=>{//서버로부터 받은 데이터가 data 
			console.log('서버에서 받은 데이터:',data);
			console.log('아이디:%s,비번:%s',data.id,data.pwd);
			
		}).fail(e=>{
			console.log('에러발생:',e);
		}); 
		
		//2. fetch 바닐라 자바스크립트 함수 사용 - 모듈을 임포트할 필요 없다. 자바스크립트에서 제공하는 함수
		//이런 것도 있구나 하고 넘기기~~~~
		
		  //https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API
		  /*fetch('<c:url value="/Annotation/RequestBody.do"/>',{method:'POST',headers: {
			    "Content-Type": "application/json",
		  },body:JSON.stringify(data)})
		  .then(function (response) {
		    console.log(response);
		    return response.json();		  
		  })
		  .then(function(data){
			  console.log(data);
			  console.log('아이디:%s,비번:%s',data.id,data.pwd);
		  })
		  .catch(function (error) {
		    console.log(error);
		  });*/
		  //3.axios 모듈 사용 - https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js모듈 임베딩 필요
		//https://axios-http.com/kr/docs/intro
		/*
		 axios.post('<c:url value="/Annotation/RequestBody.do"/>', data)
		  .then(function (response) {
		    //console.log(response);
		    console.log(response.data);
		    console.log('아이디:%s,비번:%s',response.data.id,response.data.pwd);
		  })
		  .catch(function (error) {
		    console.log(error);
		  });*/
		
		
	}
	





</script>








