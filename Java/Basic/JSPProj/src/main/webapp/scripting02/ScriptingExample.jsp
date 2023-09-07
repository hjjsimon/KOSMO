<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	//[멤버변수]
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private JspWriter out;
	
	//[메소드]
	private void connect(ServletContext app){//데이터베이스 연결하는 메소드, 여기 선언부라 코드못씀, 대신 값을 받아오자(아래 드라이버 로딩내용)
		try {
			//드라이버 로딩] D:\HJJ\Workspace\Java\Basic\javaSEProj\lib 의 ojdbc6.jar 복붙함
			//Class.forName("oracle.jdbc.OracleDriver");
			Class.forName(app.getInitParameter("ORACLE-DRIVER"));
			//데이타 베이스 연결]
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott", "scott");
			conn = DriverManager.getConnection(app.getInitParameter("ORACLE-URL"),"scott", "scott");
			
		}
		catch(ClassNotFoundException e) {
			try{
				out.println("<h2>드라이버 로딩 실패</h2>");
			}
			catch(IOException e1){}
		}
		catch(SQLException e) {
			try{
				out.println("<h2>데이터 베이스 연결 실패</h2>");
			}
			catch(IOException e1){}
		}
	}
	
	private void close(){
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}
		catch(SQLException e){}
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
    <title>ScriptingExample.jsp</title>
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
	<%-- this.out=out; connect();--%>
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
            <h1>스크립팅 요소 연습하기</h1>
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">표현식으로 출력</legend>
        	<table class="table table-bordered text-center">
				<thead class="thead-dark">
					<tr>
						<th class="col-1">사번</th>
						<th class="col-2">이름</th>
						<th class="col-2">직책</th>
						<th>입사일</th>
						<th class="col-1">연봉</th>
						<th class="col-1">보너스</th>
						<th class="col-1">부서코드</th>
					</tr>
				</thead>
				<tbody class="table-sm">
					<!-- 아래 tr을 반복 -->
					<%
						this.out=out;
						//데이터 베이스 연결]
						//connect();
						connect(application);
						//쿼리 실행용 객체 얻기] 커서를 위아래로 이동가능하게할 것임
						psmt = conn.prepareStatement(
								"SELECT * FROM emp ORDER BY sal DESC",
								ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY
								);
						//쿼리 실행]
						rs = psmt.executeQuery();
						while(rs.next()){
					%>
					<tr>
						<td><%=rs.getString(1) %></td>
						<td><%=rs.getString(2) %></td>
						<td><%=rs.getString(3) %></td>
						<td><%=rs.getDate(5) %></td>
						<td><%=rs.getString(6) %></td>
						<td><%=rs.getString(7)==null? "":rs.getString(7) %></td>
						<td><%=rs.getString(8) %></td>
					</tr>
					<% } %>
				</tbody>
			</table>
        </fieldset>
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">out내장객체로 출력</legend>
        	<table class="table table-bordered text-center">
				<thead class="thead-dark">
					<tr>
						<th class="col-1">사번</th>
						<th class="col-2">이름</th>
						<th class="col-2">직책</th>
						<th>입사일</th>
						<th class="col-1">연봉</th>
						<th class="col-1">보너스</th>
						<th class="col-1">부서코드</th>
					</tr>
				</thead>
				<tbody class="table-sm">
					<%
						while(rs.previous()){
							out.println("<tr>");
							out.println(String.format("<td>%s</td>", rs.getString(1)));
							out.println(String.format("<td>%s</td>", rs.getString(2)));
							out.println(String.format("<td>%s</td>", rs.getString(3)));
							out.println(String.format("<td>%s</td>", rs.getDate(5)));
							out.println(String.format("<td>%s</td>", rs.getString(6)));
							out.println(String.format("<td>%s</td>", rs.getString(7)==null?"":rs.getString(7)));		
							out.println(String.format("<td>%s</td>", rs.getString(8)));
							out.println("</tr>");
						}
						//자원반납
						close();
					%>
				</tbody>
				
			</table>
        </fieldset>
        
    </div><!--container-->
</body>
</html>