<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset='utf-8'>
	<!-- 
	<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css'>
	<script src='https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js'></script>
	<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js'></script>
	<script src='https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js'></script>
	 -->
	<link rel='stylesheet' href='<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css'>
	<script src='<%=request.getContextPath() %>/bootstrap/js/jquery.slim.min.js'></script>
	<script src='<%=request.getContextPath() %>/bootstrap/js/popper.min.js'></script>
	<script src='<%=request.getContextPath() %>/bootstrap/js/bootstrap.bundle.min.js'></script>
	<script src="https://kit.fontawesome.com/e7dcffe0a0.js" crossorigin="anonymous"></script>
	<title>ResponseForRedirectIndex.jsp</title>
	<style>
		.jumbotron{
			padding-top: 1rem;
			padding-bottom: 1rem;
			margin-bottom: .5rem;
			
			border-top-left-radius:0;
			border-top-right-radius:0;
		}
	</style>
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
          <!--하단고정일때는 dropdown을 dropup으로-->
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
            <h1>sendRedirect() 메소드</h1>            
        </div><!--jumbotron-->
       	<fieldset class="form-group border p-3">
	    	<legend class="w-auto px-3">리다이렉트로 이동</legend>
	    	<form class="form-inline"  action="ResponseForRedirectProcess.jsp" method="POST">
   				<div class="form-group">
		   			<label class="ml-2">아이디</label>
		   			<input type="text" name="id" class="form-control mx-2"/>
	   			</div>
              		
              	<div class="form-group">
		   			<label class="ml-2">비밀번호</label>
		   			<input type="password" name="pwd" class="form-control mx-2"/>
	   			</div>
	   			<input type="submit" class="btn btn-danger mx-2" value="로그인"/>
	   			 			
	   		</form>  	
    	</fieldset>
    	<div class="modal fade" id="myModal">            
            <div class="modal-dialog modal-sm">
            	<div class="modal-content">
	                <!-- Modal body -->
	                <div class="modal-body">	                	
	                	<!-- 여기에 메시지를 뿌려주세요 -->
	                </div>        
                </div>
            </div>
         </div>
         
    </div><!--container-->
	<script>
	/*성현이코드
		var id = document.querySelector("[name=id]");
		var pwd = document.querySelector("[name=pwd]");
		var text = document.querySelector(".modal-body");
		$('[type=submit]').click(function (e){
			if(id.value.length==0){
				text.innerHTML = '아이디를 입력하세요';
				$("#myModal").modal("show");
				e.preventDefault();
			}
			else if (pwd.value.length==0) {
				text.innerHTML = '비밀번호를 입력하세요';
				$("#myModal").modal("show");	
				e.preventDefault();
			}
		})
			//모달창 닫을때 해당 입력창에 포커스주기
		 $("#myModal").on('hidden.bs.modal', function(){
			  if(id.value.length==0){
				  id.focus();
			  }
			  else if (pwd.value.length==0){
				  pwd.focus();
			  }
		  });
		  */
	</script>
    <script>
 
    	var form = document.querySelector('div > fieldset > form');
    	var modalBody = document.querySelector('.modal-body');
    	var id= document.querySelector('div > fieldset > form > div:nth-child(1) > input');    	
    	var pwd= document.querySelector('div > fieldset > form > div:nth-child(2) > input');
    	var isPassword=false;
    	form.onsubmit=function(){
    		if(form.id.value===""){		//name이 아이디 .으로접근해도됨
    			modalBody.textContent='아이디를 입력하세요';
    			$('#myModal').modal('show');  
    			isPassword=false;
    			return false; 	//submit 취소
    		}
    		if(form.pwd.value===""){
    			modalBody.textContent='비밀번호를 입력하세요';
    			$('#myModal').modal('show');
    			isPassword=true;
    			return false;
    		}
    	};
    	//모달창 닫을때 해당 입력창에 포커스 주기
    	$("#myModal").on('hidden.bs.modal', function(){
    		if(!isPassword) id.focus();
    		else pwd.focus();
    	});
  
    </script>
</body>
</html>