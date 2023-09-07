<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
		https://tomcat.apache.org/tomcat-10.0-doc/jndi-resources-howto.html에서 아래 내용 확인
		
		// Obtain our environment naming context
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		
		// Look up our data source
		DataSource ds = (DataSource)
		  envCtx.lookup("jdbc/EmployeeDB");
		
		// Allocate and use a connection from the pool
		Connection conn = ds.getConnection();
		... use this connection to access the database ...
		conn.close();
		
		커넥션에서 이름으로 가져옴, 먼저 디렉토리 구조라 드라이브로 들어감
		그게 Context envCtx = (Context) initCtx.lookup("java:comp/env"); 암기
		그리고 이름으로 설정한 jsp로 들어가면 커넥션 객체가 있음
		그걸 DataSource로 형변환 후 getConnection(); 하면 됨
		이렇게하면 톰캣 시작시 글로벌자원 설정된 정보대로 커넥션을 최대 8개 만들어 풀에 넣어놓음
		그리고 getConnection으로 가져다 쓰기만 하면됨, 반납은 close();

 -->    
<%
	/*
	커넥션pool을 이용한 커넥션 객체 사용]
	톰캣 서버가 커넥션 pool에 미리 생성해놓은 Connection 객체를 갖다 사용하는 것
	*/
	
	//1]InitialContext객체 생성(Context > InitalContext)
	Context ctx = new InitialContext();//javax.naming에서 임포트, https://tomcat.apache.org/tomcat-10.0-doc/jndi-resources-howto.html 복붙
	
	/*
	 2]InitialContext 객체로 JNDI서비스 구조의  
	   초기 ROOT디렉토리 얻기(예:C드라이브로 접근)
	   lookup해서 각 WAS서버의 서비스 루트 디렉토리를 얻는다 
	   단, 톰캣은 루트 디렉토리명이 java:comp/env이다. 
	 */
	/////ctx = (Context)ctx.lookup("java:comp/env");// java:comp/env(C드라이브라고 생각) 여기가 루트, 모든 자원이 있음, 이걸로 다시 덮어씀
	
	/* 
	  3]context.xml에 등록한 네이밍을 lookup
	    -톰캣의 context.xml에 등록한 네이밍으로 DataSource를 얻는다 
	*/
	/////DataSource source = (DataSource)ctx.lookup("jsp");//context.xml에 설정한 jsp로 찾아들어간것, 이걸로 찾으면 javax.sql.DataSource 이 타입임(.xml들어가보면 됨)
	//풀 경로로 접근하기
	DataSource source=(DataSource)ctx.lookup("java:comp/env/jsp");//이 코드 하나면 위에 2],3]두 코드 필요없음, 한번에 찾아가는 것
	
	 /* 
	   	4]커넥션pool에서 톰캣 서버가 생성해 놓은 Connection객체를 갖다 쓰자
	    	DataSource의 getConnection()메소드로 
	    	톰캣이 pool에 미리 생성해 놓은  Connection객체를 가져다 쓴다.
    */  
	Connection conn = source.getConnection();//DB연결할때 쓰던 Connection객체로 연결
	
	String connString;
	if(conn != null) {
		connString="<h3>커넥션 객체 가져오기 성공</h3>";//연결된것
		//5]커넥션 풀에 사용한 커넥션 객체 다시 반납(메모리 해제가 아님)
		conn.close();
	}
	else connString="<h3>커넥션 객체가져오기 실패</h3>";
	
%>    

<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Connection Pool</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">커넥션 풀</legend>
        	<%=connString %><!-- 커넥션 객체 가져오기 성공 -->
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>