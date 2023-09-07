<%@page import="java.util.List"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//요청헤더에서 쿠키값 읽기
	Cookie[] cookies = request.getCookies();
	//상품 코드값을 담을 컬렉션
	List<String> cart = new Vector<>();
	
	//아이디 저장용
	String username="";
	
	if(cookies != null){//쿠키는 꼭 null체크
		for(Cookie cookie : cookies){
			//쿠키명 얻기(상품명 혹은 USER_ID)
			String name = cookie.getName();
			//쿠키값 얻기(상품코드 혹은 사용자아이디값)
			String value = cookie.getValue();
			if(value.toUpperCase().startsWith("PC_FR")) cart.add(value);//쿠키 중 product로 시작하는거 골라서 선택된것만 cart에 담음
			//아이디 저장용
			if("USER_ID".equals(name)) username = value;
		}
	}

%>    
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
    <title>CookieExamIndex.jsp</title>
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
</head>
<body>
	<!-- 네비게이션 바  -->
	<!--상단 고정-->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">   
      <!--Brand / Logo 표시-->      
      <a class="navbar-brand" href="#"><i class="fa-solid fa-house-chimney"></i></a>
      <!-- Navbar text-->
      <span class="navbar-text">
        모두를 위한 플랫폼
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
            <h1>쿠키예제</h1>            
        </div><!--jumbotron-->
        
        <fieldset class="form-group border p-3">
    		<legend class="w-auto p-3">장바구니 예제</legend>
    		<form action="CookieExamCartProcess.jsp">
    		<div class="form-group">
				
					<div class="d-flex">
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" name="cart" value="사과:PC_FR_001" id="product1" 
							<%if(cart.contains("PC_FR_001")){ %> checked disabled <%} %>/>
							<label class="custom-control-label" for="product1">사과</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" name="cart" value="바나나:PC_FR_002" id="product2" 
							<%if(cart.contains("PC_FR_002")){ %> checked disabled <%} %>/>
							<label class="custom-control-label" for="product2">바나나</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" name="cart" value="포도:PC_FR_003" id="product3" 
							<%if(cart.contains("PC_FR_003")){ %> checked disabled <%} %>/>
							<label class="custom-control-label" for="product3">포도</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" name="cart" value="딸기:PC_FR_004" id="product4" 
							<%if(cart.contains("PC_FR_004")){ %> checked disabled <%} %>/>
							<label class="custom-control-label" for="product4">딸기</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" name="cart" value="수박:PC_FR_015" id="product15" 
							<%if(cart.contains("PC_FR_015")){ %> checked disabled <%} %>/>
							<label class="custom-control-label" for="product15">수박</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" name="cart" value="참외:PC_FR_190" id="product190" 
							<%if(cart.contains("PC_FR_190")){ %> checked disabled <%} %>/>
							<label class="custom-control-label" for="product190">참외</label>
						</div>
						<input type="submit" value="장바구니 담기" class="btn btn-info"/>
					</div>
				</div>
			</form>
			<form action="CookieExamCartEmpty.jsp">
				<input type="submit" value="장바구니 비우기" class="btn btn-info"/>
			</form>
			<hr/>
			<a href="CookieExamCartShow.jsp" class="btn btn-info">장바구니 보기</a>
    	</fieldset>
    	<hr/>
    	<span class="font-weight-bold text-danger mb-2">
    	<%=request.getAttribute("ERROR")==null? "" : request.getAttribute("ERROR")%></span>
    	<fieldset class="form-group border p-3">
    		<legend class="w-auto p-3">아이디 저장 예제</legend>
    		
    		<%if(session.getAttribute("USER-ID")==null){//로그인처리 안된것 %>
    		
    		<form class="form-inline" action="CookieExamLoginProcess.jsp" method="POST">
    		
    			<label>아이디</label>
    			<input  type="text" name="id" class="form-control mx-2" 
    			value="<%=request.getParameter("id")==null?username:request.getParameter("id")%>"/><!-- 값유지 -->
    			
    			<label>비밀번호</label>
    			<input type="password" name="pwd" class="form-control mx-2"
    			value="<%=request.getParameter("pwd")==null?"":request.getParameter("pwd")%>"/><!-- 값유지 -->
    			
    			<div class="custom-control custom-checkbox">
					<input type="checkbox" class="custom-control-input" name="id-save" value="Y" id="id-save" 
					<%if(username.length()!=0){ %>checked<%} %>/><!-- 0아니면 쿠키에 유저네임있음, 이 때는 체크박스 체크유지 -->
					<label class="custom-control-label" for="id-save">아이디 저장</label>
				</div>
    			<input type="submit" class="btn btn-danger mx-2" value="로그인"/>
    		</form>
    		
    		<%}else{//세션영역에 USER-ID키로 로그인이 된 것, 이 때만 로그아웃버튼보여줌 %>
    		
    		<a href="CookieExamLogout.jsp" class="btn btn-info">로그아웃</a> 	
    		
    		<%} %>	
    	</fieldset>        
        
    </div><!--container-->
</body>
</html>