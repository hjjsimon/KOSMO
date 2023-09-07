<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
	private String codeToValue(String code){
		
		switch(code.toUpperCase()){
			case "POL": return "정치";
			case "ECO": return "경제";
			case "ENT": return "연예";
			default : return "스포츠";
		}
	}
%>    
    
<%
	/*
		※getParameter계열 메소드가 null을 반환하거나 빈 문자열을 반환 하는 경우](파라미터=name의 값)
		
		1]parameter를 전달하지 않거나 파라미터명이 틀린 경우 null반환
		2]Text박스 계열인 경우 값을 입력 안하고 전송한 경우 - 빈 문자열("") 반환
		3]선택 계열인 radio박스나 checkbox등인 경우 미 선택시에는 null반환(왜냐하면 파라미터가 전달이 안되니까)
		
		※name이 같은(ex.checkbox는 name동일) form의 하위요소의 값을 받을때는 getParameterValues()
		 단, type="radio"처럼 하나의 이름으로 하나의 값을 전달할때는 getParameter()로 받아도 된다.
	*/
	//액션x, 현재 페이지를 다시 받는거라, 현재 페이지에서 파라미터값을 받자
	//파라미터 받기
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String[] inter = request.getParameterValues("inter");//이름 하나에 값이 여러개니까 String[]배열반환
	//gender, inter는 선택안하면 null, name은 클릭시 null불가(당연히 최초는 null)
	//out.println(String.format("<span class='text-danger'>이름:%s,성별:%s,관심사항:%s</span>", name, gender, inter));
	String values = "";
	if(name !=null && name.trim().length()!=0 && gender !=null && inter !=null){//사용자가 다 선택함
		values=String.format("<kbd>이름:%s,성별:%s,관심사항:",name,gender);//inter는 String[]이라 주소로 출력, 빼줘야함
		for(String code:inter) values+=codeToValue(code) +" ";//위의 name,gender받은거에 code누적
		values+="</kbd>";
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
    <script src="<%=request.getContextPath() %>/bootstrap/js/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.bundle.min.js"></script> 
    
    <script src="https://kit.fontawesome.com/16e1907a6b.js" crossorigin="anonymous"></script>
    <title>RequestForParameter.jsp</title>
    <style>
        /*점보트론 세로폭 및 마진바텀 줄이기*/
        .jumbotron{
            padding-top: 1rem;
            padding-bottom: 1rem;
            margin-bottom: .5rem;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>
<body>
	<!-- 네비게이션바 -->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
        <a class="navbar-brand" href="#"><i class="fa-solid fa-house-chimney"></i></a>
		<span class="navbar-text">모두를 위한 플랫폼</span>        
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
            <ul class="navbar-nav mr-3">
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Dropdown link</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Link 1</a>
                        <a class="dropdown-item" href="#">Link 2</a>
                        <a class="dropdown-item" href="#">Link 3</a>
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
    <div class="container" style="margin-top:50px;">
        <div class="jumbotron bg-info">
            <h1>서버로 전송한 파라미터</h1>
        </div><!--jumbotron-->
        <h1 class="display-4">
  			<!-- 분홍색됨 --><code>form</code>태그를 이용한 값 전송
	   	</h1>
	   	<form><!-- 액션x, 현재페이지 재전송/ form태그에 메소드x, get방식 -->
			<div class="form-group">
				<label class="lead font-weight-bold">이름</label>
				<input type="text" class="form-control"	name="name">
			</div>
			<div class="form-group">
				<label class="lead font-weight-bold">성별</label>
				<div class="form-check">
					<label class="form-check-label"> <input type="radio" class="form-check-input" name="gender" value="남자">남자
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label"> <input type="radio" class="form-check-input" name="gender" value="여자">여자
					</label>
				</div>
			</div>
			<div class="form-group">
				<label class="lead font-weight-bold">관심사항</label>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" name="inter" value="POL">정치
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" name="inter" value="ECO">경제
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" name="inter" value="ENT">연예
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" name="inter" value="SPO">스포츠
					</label>
				</div>
			</div>		
			<button type="submit" class="btn btn-primary">확인</button>
		</form>
		<%-- out.println(String.format("<span class='text-danger'>이름:%s,성별:%s,관심사항:%s</span>", name, gender,inter)); --%>
		<!-- 사용자 입력/선택값 출력 -->
		<%=values %>
		<h1 class="display-4">
  			<!-- 분홍색됨 --><code>a</code>태그를 이용한 값 전송
	   	</h1>
	   	<a href="RequestForParameter.jsp?name=코스모&gender=남성&inter=eco&inter=spo">GET</a>
	   	<!-- a태그 무조건 get방식, 클릭시 현제 패이지 재출력, 위에 입력한대로 가져옴 -->
		<h1 class="display-4">모든 파라미터명 얻기</h1>
		<!-- -request객체의 getParameterNames()메소드로 얻는다.(단 파라미터가 전달됐을때)
			-파라미터명 존재여부 판단 -->
		<ul class="list-unstyled">
			<% 
				Enumeration<String> names = request.getParameterNames();
				while(names.hasMoreElements()){//헤더명 얻을 때와 동일
					//파라미터명 얻기
					String paramName = names.nextElement();
					//파라미터값 얻기
					String paramValue = request.getParameter(paramName);
					
					if("inter".equalsIgnoreCase(paramName)){//파람이름이 inter라면, getParameterValues로 받아야함, 값 여러개라
						String[] paramValues = request.getParameterValues(paramName);
						//방법1] 코드값 그대로 출력(대괄호, 코드값으로 나옴)
						//out.println(String.format("<li>%s : %s</li>",paramName, Arrays.toString(paramValues)));
						//방법2] 코드로 변환해서 출력
						/*
						String valus="";
						for(String code:paramValues) valus+=codeToValue(code)+" ";
						out.println(String.format("<li>%s : %s</li>", paramName,valus));*/
						//방법3] 코드로 변환해서 출력(스트림 사용)
						Stream<String> streams= Arrays.stream(paramValues);//배열로 스트림 만듦
						String valus=Arrays.toString(streams.map(x->codeToValue(x)).toArray());
						//인자 하나 꺼내서 codeToValue에 전달 후 인자 반환받음, 그걸 다시 배열로 바꿈
						out.println(String.format("<li>%s : %s</li>",paramName, valus));
					}
					else
						out.println(String.format("<li>%s : %s</li>", paramName,paramValue));
				}
			%>	
		</ul>
		<h1 class="display-4">맵으로 모든 파라미터 얻기</h1>
		<ul class="list-unstyled">
			<%
				/*
				getParameterMap():많은 파라미터가 있을때 한꺼번에 받을때 유리
							      key는 파라미터명이 되고
							      value는 사용자가 입력하거나 선택한 값이 된다
				*/
				Map<String,String[]> params = request.getParameterMap();//체크는 여러개 가능하므로 value는 String[]배열로 해놓음
				Set<String> paramNames = params.keySet();//키만 얻어옴
				for(String paramName : paramNames){
					String[] vals = params.get(paramName);//밸류 얻어옴
					if("inter".equalsIgnoreCase(paramName)){
					%>
						<li><%=String.format("%s : %s", paramName, Arrays.toString(vals))%></li>
					<%}else{ %>
						<li><%=String.format("%s : %s", paramName, vals[0]) %></li>
					<%} %>
				<%	} %>
		</ul>
    </div><!--container-->
</body>
</html>