<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	//[선언부(Declaration)]
	//선언코드만 있어야함, 실행코드 있으면 안됨
	//선언부에 작성한 자바코드는 서블릿으로 변환시 Class안에 생성됨
	//즉, 멤버변수나 멤버메소드 혹은 상수등을 선언부에서 선언함(클래스 선언시 내부클래스, 인터페이스도 가능)
	//[멤버상수]
	public static final int INT_MAX = Integer.MAX_VALUE;//21억 얼마 나옴
	//[멤버변수]
	private String mVariable = "<h3>선언부에서 선언한 변수</h3>";
	//[멤버메소드]
	private int getMaxNumber(int num1, int num2){
		return num1 > num2? num1 : num2; //num1이 크면 num1반환
	}
	//[선언부에서 내장객체 사용하는 방법]
	//방법1]
	//멤버변수 선언]
	private JspWriter out;
	private void showMessage(String message){
		//out은 service안에 선언된 지역변수라 여기서 바로 out.println 사용불가
		try{
			out.println(message);//(저기 아래에서는 다 에러 throwable로 캐치, 여기는 캐치해야함)
			//멤버변수는 타입이 클래스니까 무조건 참조형 null로 초기화됨, 지역변수는 초기화 안되지만, 여튼 out이 null이라 out.으로 찾으면 null포인트 익셉션
		}
		catch(IOException e){e.printStackTrace();}
	}
	//방법2]매개변수로 전달받는다()
	private void showMessage(String message, JspWriter out){
		//out은 service안에 선언된 지역변수라 여기서 바로 out.println 사용불가
		try{
			out.println(message);//(저기 아래에서는 다 에러 throwable로 캐치, 여기는 캐치해야함)
			//멤버변수는 타입이 클래스니까 무조건 참조형 null로 초기화됨, 지역변수는 초기화 안되지만, 여튼 out이 null이라 out.으로 찾으면 null포인트 익셉션
		}
		catch(IOException e){e.printStackTrace();}
	}
			
			
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/16e1907a6b.js" crossorigin="anonymous"></script>
    <title>ScriptingBasic.jsp</title>
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
        <a class="navbar-brand" href="#">KOSMO</a>
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
            <h1>스크립팅 요소 3개 <small>Declaration/Scriptlet/Expression</small></h1>
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">스크립틀릿 및 표현식</legend>
        	<%
        		//[스크립틀릿(Scriptlet)]
        		//스크립틀릿 안에 작성한 자바코드는 서블릿으로 변환시 _jspService()메소드 안에 생성됨, 따라서 스크립트릿 안에서는 메소드 정의불가
        		//JSP에서 제공하는 내장객체는 _jspService()메소드 안에 선언된 지역변수이므로 지역에서만 사용가능, 선언부에서는 사용불가(중요)
        		
        		//void method(){}//[x]메소드 정의불가
        		//out은 service안에 선언된 지역변수라 위에서 사용불가
        		String localVariable="<h4>스크립틀릿 안에서 선언한 변수(지역변수)</h4>";
        		out.println("<h1 class='display-4'>스크립틀릿으로 출력</h1>");//sysout이라고 생각하면됨
        		out.println(INT_MAX);
        		out.println(mVariable);//맨위 선언부에 선언한거
        		out.println(localVariable);//위에 방금 선언한거
        		out.println("최대값:"+getMaxNumber(10, 100));
        		//방법1] this는 자기자신 out, .class로 바뀜, _jspService() 변수안의 out을 이 주소에 넣은것
        		//클래스의 멤버변수(this.out)= 서비스메소드 안에서 선언한 지역변수(out)
        		//선언부에서 선언된 out에 _jspService()메소드 안에서 선언된 out대입
        		this.out=out; 
        		showMessage("<h4>this.out=out방식으로 선언부에 선언한 필드에 내장객체 out대입</h4>");
        		//방법2] 두번째 인자는 _jspService()메소드에서 선언된 내장객체
        		showMessage("<h4>인자로 내장객체 out전달</h4>",out);

        	        		
        	%>
        	<h1 class='display-4'>표현식으로 출력</h1>
        	<!-- 표현식은 _jspService()메소드 안에서 out.print("EXPRESSION");으로 변환됨, 따라서 표현식 안에서 ; 붙이면 에러 발생함-->
        	<%=INT_MAX %>
        	<%=mVariable %>
        	<%=localVariable %>
        	<%="최대값:"+getMaxNumber(10, 100) %>
        	
        </fieldset>
        
    </div><!--container-->
</body>
</html>