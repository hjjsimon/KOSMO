<%@page import="model.MemberDTO"%>
<%@ page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	파라미터로 넘어온 값(POST방식일때-톰캣10부터 안깨짐)을 읽을때는 한글처리를 위해
	반드시 request.setCharacterEncoding("문자인코딩셋")로 처리 -> 이제 안해줘도 됨
	
	단, 영역에 저장된 값을 읽어 올때는 한글처리 불필요. -> 한글 안깨짐
	
	모든 영역(페이지,리퀘스트,세션,어플리케이션)에는
	모든 타입(Object)의 객체 저장 가능
	파라미터로 값을 전달할때는 문자열만 가능(form태그 POST 또는 쿼리스트링)
	
	※page영역에 저장한 속성은 해당 페이지를 벗어나면 소멸된다.
	  page영역에는 pageContext객체(PageContext타입)존재
	  
	->주로 request에 정보를 저장한다 
	*/
	//페이지 영역에 속성 저장]
	pageContext.setAttribute("pageInteger", new Integer(10));//10해도 됨 오토박싱 되니까
	pageContext.setAttribute("pageString", "페이지 영역에 저장할 문자열");
	pageContext.setAttribute("pageDate", new Date(new java.util.Date().getTime()));//sql꺼 사용, 기본생성자 없음
	pageContext.setAttribute("pageMemberFirst", new MemberDTO("KIM","1234","김길동","20"));//클래스 만들고옴
	pageContext.setAttribute("pageMemberSecond", new MemberDTO("PARK","1234","박길동","30"));
	
	

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
    <title>PageScopeIndex.jsp</title>
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
            <h1>page영역</h1>            
        </div><!--jumbotron-->
        <h2>페이지 영역에 저장된 속성 읽기</h2><!-- 당연히 현재 페이지의 속성은 잘 읽어옴 -->
        <%
        	Object object = pageContext.getAttribute("pageMemberFirst");//넣는값도 Object, 반환도 동일
        	//object. 하면 원래 MemberDTO 타입인데 안뜸, 형변환 해줘야함
        	String firstMemberString="";
        	if(object instanceof MemberDTO){//instance로 판단해서 맞으면 넣는게 정석
        		MemberDTO dto = (MemberDTO)object;
        		firstMemberString = String.format("이름:%s,아이디:%s,비번:%s,나이:%s", dto.getName(),dto.getId(),dto.getPwd(),dto.getAge());
        		//toString오버라이딩 해놔서 그냥 찍어도 되긴함
        	}
        	MemberDTO second = (MemberDTO)pageContext.getAttribute("pageMemberSecond");//위가 정석, 이건 가라
        %>
		<ul class="list-unstyled">
			<li>Integer타입 : <%=pageContext.getAttribute("pageInteger") %></li>
			<li>String타입 : <%=pageContext.getAttribute("pageString") %></li>
			<li>Date타입 : <%=pageContext.getAttribute("pageDate") %></li>
			<li>MemberDTO타입 1 : <%=firstMemberString %></li>
			<li>MemberDTO타입 2 : <%=second %></li>
		</ul>
		<h2>페이지 영역에 저장된 속성 삭제후 읽기</h2>
		<%
			//속성명이 틀린 경우 삭제안됨, 에러가 나지는 않음
			pageContext.removeAttribute("NoKEY");
			//존재하는 키값(속성명)으로 삭제 -> 지운 2개는 null나옴
			pageContext.removeAttribute("pageInteger");
			pageContext.removeAttribute("pageString");
		%>
		<ul class="list-unstyled">
			<li>Integer타입 : <%=pageContext.getAttribute("pageInteger") %></li>
			<li>String타입 : <%=pageContext.getAttribute("pageString") %></li>
			<li>Date타입 : <%=pageContext.getAttribute("pageDate") %></li>
			<li>MemberDTO타입 1 : <%=pageContext.getAttribute("pageMemberFirst") %></li>
			<li>MemberDTO타입 2 : <%=pageContext.getAttribute("pageMemberSecond") %></li>
		</ul>
		<h2>페이지 이동</h2>
		<a href="PageScopeResult.jsp">사용자 클릭에 의한 이동</a><!-- 클릭은 새로운 페이지 요청 의미 -->
		
		<%@include file="PageScopeIncludedPage.jsp" %><!-- ""안의 페이지가 현재 페이지에 그대로 들어오니 같은 페이지 영역이 됨 -->
		<!-- ""안의 페이지에 있는 코드에 현재 페이지의 값 그대로 넣어 출력됨 -->
		



        
        
        
    </div><!--container-->
</body>
</html>