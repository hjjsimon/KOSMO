<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--  
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    -->
     <link rel="stylesheet" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css">
    <script src="<%=request.getContextPath() %>/bootstrap/js/jquery.slim.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/0b4621b427.js" crossorigin="anonymous"></script>
    <title>RequestForExampleIndex.jsp</title>
    <style>
        /*점보트론 세로폭 및 마진바툼 줄이기*/
        .jumbotron{
            padding-top:1rem;
            padding-bottom:1rem;            
            margin-bottom: .5rem;
            
            border-top-left-radius:0;
            border-top-right-radius:0;
        }
    </style>
    <script>
    
    window.addEventListener('DOMContentLoaded',function(){
    	
    	var button = document.querySelector('body > div > form > button');
    	var id = document.querySelector('[name=id]');
    	var pwd1 = document.querySelector('[name=pwd]');
    	var pwd2 = document.querySelector('[name=pwd2]');
    	var gender = document.querySelectorAll('[name=gender]');
    	var inter = document.querySelectorAll('[name=inter]');
    	var grade = document.querySelector('[name=grade]');
    	var file = document.querySelector('[name=file]');
    	var self = document.querySelector('[name=self]');

    	button.onclick=function(e){
	    	
	    	if(id.value.trim() === ''){
	            alert('아이디를 입력하세요??');
	            id.focus();
	            return false;
	        }
	    	var pattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[`~!@#$%^&*()-_+=\[\]{}|;':",.<>/?]).{8,}$/;
	    	if(!pattern.test(id.value)){
	    		alert('아이디는 영문자와 숫자 그리고 특수문자를 포함하여 8자 이상이어야 합니다');
	    		id.focus();
	    		return false;
	    	}
	    	if(pwd1.value.trim() === ''){
	    		alert('비밀번호를 입력하세요??');
	            pwd1.focus();
	            return false;
	    	}
	    	if(pwd2.value.trim() === ''){
	    		alert('비밀번호 확인을 입력하세요??');
	            pwd2.focus();
	            return false;
	    	}
	    	if(pwd1.value.trim() !== pwd2.value.trim()){
	    		alert('비밀번호가 일치하지 않습니다');
	    		pwd2.focus();
	    		return false;
	    	}
	    	var isGender=false;
            for(var i=0; i<gender.length; i++){
                if(gender[i].checked){
                    isGender=true;
                    break;
                }
            }
            if(!isGender){
                alert('성별을 선택하세요??');
                return false;
            }
            var count=0;
            for(var i=0; i<inter.length; i++){
	            if(inter[i].checked){
	            	count++;
	            }
            }
            if(count < 3){
            	alert('관심사항은 최소 3개 이상 선택해주세요??')
            	return false;
            }
            if(grade.selectedIndex === 0){
                alert('학력사항을 선택하세요??');
                grade.focus();
                return false;
            }
            if(file.value.trim() === ''){
                alert('파일을 선택하세요??');
                file.focus();
                return false;
            }
            if(self.value.trim() === ''){
                alert('자기소개를 입력하세요??');
                self.focus();
                return false;
            }
    	}
    })
    
  </script>
</head>
<body>
	<!-- 네비게이션 바  -->
	<!--상단 고정-->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">   
      <!--Brand / Logo 표시-->      
      <a class="navbar-brand" href="#"><i class="fa-solid fa-house-chimney"></i></a>
      <!-- Navbar text-->
      <span class="navbar-text">
        솔로를 위한 플랫폼
      </span>
      
      <!-- Toggler/collapsibe Button -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
       
        <!-- Links -->
        <ul class="navbar-nav mr-3"><!--mr-3은 Navbar text와의 여백용-->
          <li class="nav-item">
            <a class="nav-link active" href="#">Link 1</a>
          </li>
          <li class="nav-item">
            <a class="nav-link " href="#">Link 2</a>
          </li>
          <!--Navbar With Dropdown-->
          <!--하단 고정일때는 dropdown을 dropup으로-->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Link 3</a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="#">SubLink 1</a>
              <a class="dropdown-item" href="#">SubLink 2</a>
              <a class="dropdown-item" href="#">SubLink 3</a>
            </div>
          </li>
          <li class="nav-item">
            <form class="form-inline" action="#">
              <input class="form-control mr-sm-2" type="text" placeholder="검색어 입력">
              <button class="btn btn-success" type="submit">검색</button>
            </form>
          </li>
        </ul>
      </div>
    </nav>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>파라미터 받기 연습</h1>            
        </div><!--jumbotron-->
        <form action="RequestForExampleComplete.jsp" method="post">
			<div class="form-group">
				<label><kbd class="lead">아이디</kbd></label> 
				<input type="text" class="form-control" name="id" placeholder="아이디를 입력하세요">
			</div>
			<div class="form-group">
				<label><kbd class="lead">비밀번호</kbd></label> 
				<input type="password" class="form-control" name="pwd" placeholder="비밀번호를 입력하세요">
			</div>
			<div class="form-group">
				<label><kbd class="lead">비밀번호 확인</kbd></label> 
				<input type="password" class="form-control" name="pwd2"	placeholder="비밀번호 다시한번 입력하세요">
			</div>
			<div class="form-group">
				<label><kbd class="lead">성별</kbd></label>
				<div class="d-flex">
					<div class="custom-control custom-radio mr-2">
						<input type="radio" class="custom-control-input" name="gender" value="man" id="male"> 
						<label for="male" class="custom-control-label">남자</label>
					</div>
					<div class="custom-control custom-radio">
						<input type="radio" class="custom-control-input" name="gender" value="woman" id="female"> 
						<label for="female"	class="custom-control-label">여자</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label><kbd class="lead">관심사항</kbd></label>
				<div class="d-flex">
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input" name="inter" value="POL" id="POL">
						<label class="custom-control-label" for="POL">정치</label>
					</div>
					<div class="custom-control custom-checkbox mx-2">
						<input type="checkbox" class="custom-control-input" name="inter" value="ECO" id="ECO">
						<label class="custom-control-label" for="ECO">경제</label>
					</div>
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input" name="inter" value="ENT" id="ENT">
						<label class="custom-control-label" for="ENT">연예</label>
					</div>
					<div class="custom-control custom-checkbox ml-2">
						<input type="checkbox" class="custom-control-input" name="inter" value="SPO" id="SPO">
						<label class="custom-control-label" for="SPO">스포츠</label>
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
				<label><kbd class="lead">첨부파일</kbd></kbd></label>
				<div class="custom-file">
					<input type="file" class="custom-file-input" name="file" id="file"> 
					<label	class="custom-file-label" for="file">파일을 첨부하세요</label>
				</div>
			</div>
			<div class="form-group">
				<label><kbd class="lead">자기소개</kbd></kbd></label>
				<textarea class="form-control" rows="5" name="self"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">확인</button>
		</form>            
    </div><!--container-->
    <script>  
   		
	  // Add the following code if you want the name of the file appear on select
	  $(".custom-file-input").on("change", function() {
	    var fileName = $(this).val().split("\\").pop();
	    $(this).siblings(".custom-file-label").html(fileName);
	  });
	  
  </script>
</body>
</html>