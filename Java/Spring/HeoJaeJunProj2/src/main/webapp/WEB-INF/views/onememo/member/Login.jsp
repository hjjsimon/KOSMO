<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<jsp:include page="/WEB-INF/views/template/Top.jsp"/>
 	<div class="login-wrap">
 		<div class="login-html">
 		<c:if test="${ empty sessionScope.id }" var="isLogin">
		    <input id="tab-1" type="radio" name="tab" class="sign-in custom-cursor" checked><label for="tab-1" class="tab">로그인</label>
		    <input id="tab-2" type="radio" name="tab" class="sign-up custom-cursor"><label for="tab-2" class="tab">회원가입</label>
	    </c:if>
	    <c:if test="${!isLogin }">
	    	<input id="tab-1" type="radio" name="tab" class="sign-in custom-cursor" checked><label for="tab-1" class="tab">
	    		<a class="no-hover" href="<c:url value="/onememo/auth/List.do"/>">게시판 이동</a></label>
		    <input id="tab-2" type="radio" name="tab" class="sign-up custom-cursor"><label for="tab-2" class="tab">
		    	<a class="no-hover" href="<c:url value="/onememo/auth/Logout.do"/>">로그아웃</a>
		    </label>
		</c:if>	    
    		<div class="login-form">
      		<!-- 로그인 화면 -->
      		<!-- 회원가입수정 성공시 -->
      		<c:if test="${! empty sessionScope.id }">
				<div class="alert alert-success">
				 	<button type="button" class="close" data-dismiss="alert">&times;</button>
				 	<strong>회원가입정보 수정완료!</strong> ${sessionScope.id}님 즐감하세요!
				</div>
			</c:if>
			<!--  로그인 전 -->
			<c:if test="${ empty sessionScope.id }" var="isLogin">
				<form class="form-inline" action="<c:url value="/onememo/auth/LoginProcess.do"/>" method="post">
			      	<div class="sign-in-htm">
			        	<div class="group">
			          		<label for="user" class="label" style="font-size: 14px;">아이디</label>
			          		<input name="id" id="id" type="text" class="input">
			        	</div>
			        	<div class="group">
			          		<label for="pass" class="label" style="font-size: 14px;">비밀번호</label>
			          		<input name="pwd" id="pwd" type="password" class="input" data-type="password">
			        	</div>
			        	<div class="group mt-5">
			        		<input type="submit" class="button" value="로그인하기">
			        	</div>
			        	<!-- 로그인 실패시-->
			       		<c:if test="${! empty NotMember }">
				        	<div class="alert alert-success alert-dismissible fade show">
						   		<strong>로그인 실패! ${requestScope.NotMember} </strong>
							</div>
						</c:if>
						<!-- 회원가입 성공시 -->
						<c:if test="${! empty JoinMember }">
				        	<div class="alert alert-success alert-dismissible fade show">
						   		<strong>회원가입 성공! ${requestScope.JoinMember} </strong>
							</div>
						</c:if>
		        	</div>
	        	</form>
	   		</c:if>   
      	<!-- 회원가입 화면 -->
      	<form class="form-inline member" action="<c:url value="/onememo/auth/MemberProcess.do"/>" method="post">
	      	<div class="sign-up-htm">
	        	<div class="group">
		        	<label id="memberid" for="user" class="label" style="font-size: 14px;">아이디</label>
		        	<input name="id" id="id1" type="text" class="input">
	       		</div>
	        	<div class="group">
	            	<label id="memberpwd" for="pass" class="label" style="font-size: 14px;">비밀번호</label>
	            	<input name="pwd" id="pwd1" type="password" class="input" data-type="password">
		        </div>
		        <div class="group">
				    <label id="memberpwd2" for="pass" class="label" style="font-size: 14px;">비밀번호 확인</label>
				    <input name="pwd2" id="pwd2" type="password" class="input" data-type="password">
		        </div>
		        <div class="group">
		            <label id="membername" for="pass" class="label" style="font-size: 14px;">이름</label>
		            <input name="name" id="name" type="text" class="input">
		        </div>
	        	<div class="group">
	          		<label id="membergender" for="pass" class="label" style="font-size: 14px;">성별</label>
          			<div class="d-flex justify-content-center">
						<div class="custom-control custom-radio mr-2">
							<input type="radio" class="custom-control-input" name="gender" value="man" id="male"> 
							<label for="male" class="custom-control-label">남자</label>
						</div>
						<div class="custom-control custom-radio">
							<input type="radio" class="custom-control-input" name="gender" value="woman" id="female"> 
							<label for="female" class="custom-control-label">여자</label>
						</div>
		 			</div>
        		</div>
		        <div class="group">
		          	<label id="memberinter" for="pass" class="label" style="font-size: 14px;">관심사항</label>
		          	<div class="d-flex justify-content-center">
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
		        <div class="group">
		          	<label id="membergrade" for="pass" class="label" style="font-size: 14px;">학력선택</label>
		          	<select name="grade" class="custom-select custom-select-lg" style="height: 40px; font-size: 14px; width: 100%">
						<option value="">학력을 선택하세요</option>
						<option value="ELE">초등학교</option>
						<option value="MID">중학교</option>
						<option value="HIG">고등학교</option>
						<option value="UNI">대학교</option>
				 	</select>
		        </div>
		        <div class="group">
		          <label id="memberself" for="pass" class="label" style="font-size: 14px;">자기소개</label>
		          <textarea class="form-control" rows="5" name="self" style="width: 100%"></textarea>
		        </div>
		        <div class="group">
		          <a href="<c:url value="/"/>" class="no-hover"><input type="submit" class="button" value="회원가입하기"></a>
		        </div>
	      	</div>
       </form>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>
<script>

	//회원가입 유효성 체크
	$(document).ready(function() {
		
	  //폼 제출 시 유효성 체크
	  $('form.member').submit(function(event) {

	    //아이디 유효성 체크
	    var id = $('#id1').val();
	    if (id.length < 3) {
	      event.preventDefault();
	      $('#memberid').after('<span class="error-msg" style="color: red;"> (아이디는 3글자 이상이어야 합니다.)</span>');
	    }

	    //비밀번호 유효성 체크
	    var pwd = $('#pwd1').val();
	    if (pwd.length < 4) {
	      event.preventDefault();
	      $('#memberpwd').after('<span class="error-msg" style="color: red;"> (비밀번호는 4글자 이상이어야 합니다.)</span>');
	    }
	
	    //비밀번호 일치 여부 체크
	    var pwd2 = $('#pwd2').val();
	    if (pwd !== pwd2) {
	      event.preventDefault();
	      $('#memberpwd2').after('<span class="error-msg" style="color: red;"> (비밀번호가 일치하지 않습니다.)</span>');
	    }
	
	    //이름 필수 입력 체크
	    var name = $('#name').val();
	    if (name === '') {
	      event.preventDefault();
	      $('#membername').after('<span class="error-msg" style="color: red;"> (이름을 입력하세요.)</span>');
	    }
	
	    //성별 필수 선택 체크
	    var gender = $('input[name="gender"]:checked').val();
	    if (typeof gender === 'undefined') {
	      event.preventDefault();
	      $('#membergender').after('<span class="error-msg" style="color: red;"> (성별을 선택하세요.)</span>');
	    }
	
	    //관심사항 2개 이상 필수 선택 체크
	    var interests = $('input[name="inter"]:checked');
	    if (interests.length < 2) {
	      event.preventDefault();
	      $('#memberinter').after('<span class="error-msg" style="color: red;"> (관심사항은 최소 2개 이상 선택해야 합니다.)</span>');
	    }
	
	    //학력 필수 선택 체크
	    var grade = $('select[name="grade"]').val();
	    if (grade === '') {
	      event.preventDefault();
	      $('#membergrade').after('<span class="error-msg" style="color: red;"> (학력을 선택하세요.)</span>');
	    }
	
	    //자기소개 필수 입력 체크
	    var selfIntro = $('textarea[name="self"]').val();
	    if (selfIntro === '') {
	      event.preventDefault();
	      $('#memberself').after('<span class="error-msg" style="color: red;"> (자기소개를 입력하세요.)</span>');
	    }
	    
	  });
	  
	  
	});





</script>