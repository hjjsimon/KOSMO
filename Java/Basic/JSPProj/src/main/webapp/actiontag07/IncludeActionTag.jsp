<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	
	※서블릿으로 바뀐걸 보는 곳
	D:\HJJ\Workspace\Java\Basic\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\JSPProj\org\apache\jsp\actiontag07
	
	※include지시어의 file속성과
	  include액션태그의 page속성에
	  절대경로로 페이지 포함시에는 Context 루트 제외한 경로.(둘 다 현재 위치에 페이지 가져옴)
	  단,server.xml의 Context엘리먼트의 path속성을 ""으로
	  설정시에는 request.getContextPath()로 해도 상관없다.
	  
	※차이점: 표현식(변수 넣어 out.writ()로 출력하는 것)
	 include지시어의 file속성에는 표현식 사용불가
	 include액션태그의 page속성에는 표현식 사용가능
	 (단,표현식을 사용할때는 page속성에 딱 표현식만 와야한다. 추가적인 문자열 불가)
	  
	[include지시어와 include 액션태그의 차이점]
	 
	1. include 지시어를 사용한 페이지 include(포함)]
	    -소스 그대로 해당 위치에 포함됨(그리고 하나의 페이지로 서블릿 처리됨, A.jsp에서 B.jsp 불러오면 이 때는 서블릿1개 생김)
	    -include지시어를 통해 포함된 페이지는 현재 페이지와 같은 페이지를 의미(include해오면 그 것도 서블릿에 포함됨)
	    
	2. include 액션태그를 사용한 페이지 include(포함)] 
	    -JSP컨테이너에 의해 실행된 결과가  해당 위치에 포함됨
	    -서로 다른 페이지를 의미
	    -단, request영역 공유
	    (이건 A.jsp에 B.jsp 넣을 때 B.jsp를 먼저 B_jsp.java로 바꾸고 HTML코드로 바꾼게 A.jsp안의 
	    		해당위치로 들어감, java2개 생김, A.jsp요청시 B.jsp에도 요청됨, 같은 request객체!)
	*/
	//페이지명 변수에 저장
	String directivePath = "DirectivePage.jsp";
	String actionTagPath = "ActionTagPage.jsp";
	//페이지 및 리퀘스트 영역에 속성저장
	pageContext.setAttribute("pageVar", "페이지 영역입니다");
	request.setAttribute("requestVar", "리퀘스트 영역입니다");
	
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
    <title>IncludeActionTag.jsp</title>
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
            <h1>&lt;jsp:include&gt;액션태그</h1>            
        </div><!--jumbotron-->
        
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">include지시어와 &lt;jsp:include&gt;액션태그</legend><!-- 해당페이지를 그 위치에 불러오는건 공통 -->
        	
        	<!-- file속성에 표현식 사용불가 -->
        	<%--@include file="<%=directivePath %>" --%><!-- 변수에 페이지 저장돼있음 -->
        	<%@include file="DirectivePage.jsp" %>
        	
        	<!-- page속성에 표현식 가능 -->
        	<jsp:include page="<%=actionTagPath %>"></jsp:include>
        	
        </fieldset>        
        
        <h2>삽입된 페이지안에서 선언한 변수사용하기</h2>
        <!-- include액션태그로 포함된 페이지에서 선언한 변수는 사용불가(실행결과(HTML결과)만 포함되므로) -->
        directiveString : <%=directiveString %><!-- 같은 페이지라 변수선언 없이 사용 가능 -->
        actionString : <%--=actionString --%><!-- 이건 별개로 실행되고 HTML로 들어오는거라 사용 불가(그냥 쓰여지기만 가능) -->
        
    </div><!--container-->
</body>
</html>