<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ResponseForRedirectProcess.jsp -->
<!-- 여기서 아이디,비밀번호 받고 회원이면 MyPage.jsp로 이동, 회원아니면 index.jsp로 이동 (회원여부에 따라 자동으로 강제이동)-->
<!-- 아이디,비밀번호 체크를 입력 즉시 하는건 자바스크립트로 클라이언트 측에서 하는거라 여기 서버측에서 따로 해줘야함 
		중간 process를 쓰는건 여기에 Logic쓰고 유지보수 편하게 하기 위함임, 최종 MyPage에서는 결과값만 보내는 것-->
<%@ include file="/common/Validate.jsp" %><!-- 유효성체크 -->
<%
	if(!"POST".equals(request.getMethod())){//POST만 받아야함, POST가 아니면 아래 alert 띄움
%>
	<script>
		alert("잘못된 접근입니다");
		history.back();
	</script>
<%
		return;
	}
	//서버측 유효성체크 끝
	String id = request.getParameter("id");
	if(!isValidate(out, id, "아이디를 입력하세요")) return;//아이디 입력안하면 메소드 바로 빠져나감 
	String pwd = request.getParameter("pwd");
	if(!isValidate(out, pwd, "비밀번호를 입력하세요")) return;
	
	/*
		사용자가 입력한 아이디와 비번을 받아서 회원인지 판단 후 회원인경우 마이페이지로 이동
		회원이 아닌경우 다시 로그인페이지로 이동
		가정] 아이디 KIM, 비밀번호 1234가 회원이라고 가정
	*/
	//2]회원여부 판단(데이터베이스 테이블에서 조회해야함, 현재 위 가정으로 인해 생략)
	//3]회원인경우 로그인처리(session객체로) 후 마이페이지로 이동, 회원이 아니면 로그인페이지로 이동
	if("KIM".equals(id) && "1234".equals(pwd)){//회원인경우
		//방법1]sendRedirect("자동으로 이동할  페이지 주소");	
		//response.sendRedirect(request.getContextPath()+"/builtinobject03/ResponseForMyPage.jsp?username="+id+"&password="+pwd);
		//쿼리스트링으로 마이페이지로 전달(form 또는 a태그(쿼리스트링)으로 값을 넘길 수 있음) 그러나 get방식이라 문제
		//방법2]자스로 메시지(선택) 띄운 후 이동
		/*
		out.println("<script>");
		out.println("alert('"+id+"님 반갑습니다');");//이거 띄우고 마이페이지로 이동
		out.println("location.replace('"+request.getContextPath()+"/builtinobject03/ResponseForMyPage.jsp?username="+id+"&password="+pwd+"');");
		//BOM객체, 바로 이동시킴, 근데 이것도 쿼리스트링이라 id,pwd노출 문제		
		out.println("</script>");
		*/
		//response.sendRedirect(request.getContextPath()+"/builtinobject03/ResponseForMyPage.jsp?username="+id+"&password="+pwd);
		//이 뒤에 이거 실행시 alert후 이동x, 바로 이동함
		//버퍼에 먼저 위 out.println이 차곡차곡 쓰인 후 바로 이동해서 버퍼에 쓰인게 의미x
		//방법3]POST방식으로 이동시켜야한다(id,pwd노출막기)
%>
	<form method="post" action="ResponseForMyPage.jsp">
		<input type="hidden" name="username" value="<%=id%>"/>
		<input type="hidden" name="password" value="<%=pwd%>"/><!-- 버튼 누르게하면 안된대 -->
	
	</form>
	<script>
		alert("<%=id%>님 즐 서비스...");
		document.forms[0].submit();
		//form첫번째를 서브밋함, 이거 호출한다고 서브밋이벤트 발생안함, 서브밋되어 액션에 지정한 페이지로 이동함, 
		//파라미터 username, password 제출, post방식으로
	</script>
<%
		
	}
	else{//비회원인 경우
		//개발자만 편함, 아래처럼 바로 보내면 안됨
		//response.sendRedirect("ResponseForRedirectIndex.jsp");//페이지 다시 보내지므로 입력값 지워짐
		out.println("<script>");
		out.println("alert('아이디와 비번이 틀려요');");
		out.println("history.back();");//아이디는 남아있음, 비밀번호는 사라짐
		//out.println("location.href='ResponseForRedirectIndex.jsp';");//location객체로 이동은 다 사라짐
		out.println("</script>");
	}
%>