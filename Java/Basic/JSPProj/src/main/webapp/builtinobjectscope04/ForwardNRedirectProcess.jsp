<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/Validate.jsp" %>    
<!-- ForwardNRedirectProcess.jsp -->
<%
	//파라미터 받기(아이디와 비밀번호)
	Map<String,String[]> params = request.getParameterMap();//키,밸류로 받음(파라미터도 넘겨줌)
	String id = params.get("id")[0];
	String pwd = params.get("pwd")[0];
	//유효성체크
	if(!isValidate(out, id, "아이디를 입력하세요?")) return;
	//사용자 입력한 값이 value, 파라미터는 name, 파라미터명이 키값, 하나 가져오니까 0번방
	if(!isValidate(out, pwd, "비밀번호를 입력하세요?")) return;
	//가정: 아이디 KIM, 비밀번호 1234는 회원
	if("KIM".equals(id) && "1234".equals(pwd)){//회원
		//1.리다이렉트로 자동 페이지 이동
		//response.sendRedirect("ForwardNRedirectMyPage.jsp");
		//2.포워드로 자동페이지 이동 리퀘스트 영역에 저장
		//여기 두 줄 필요 없음, getParameter로 읽으면 됨, 포워드라 프로세스에 전달된 요청이 MyPage에도 전달됨
		//어차피 영역에 저장돼있어서 또 저장할 필요 없음, request공유함(로그인버튼 눌러야 request, 파라미터 전달되는 것)
		request.setAttribute("id", id);
		request.setAttribute("pwd", pwd);
		request.getRequestDispatcher("ForwardNRedirectMyPage.jsp").forward(request, response);
		//현재 프로세스가 받은 요청을 그대로 MyPage로 전달, 심지어 요청과 관련된 응답객체 response까지, request객체 1개로 MyPage까지 공유함
	}
	else{//비회원
		//1.리다이렉트로 자동 페이지 이동
		//response.sendRedirect("ForwardNRedirectIndex.jsp");//이게 href로 재요청, 썼던 값은 사라짐
		//2.포워드로 이동하기 : 입력값을 유지하기 위해
		//에러메시지 리퀘스트 영역에 저장
		request.setAttribute("errorMsg", "아이디와 비번 불일치");
		request.getRequestDispatcher("ForwardNRedirectIndex.jsp").forward(request, response);
		//프로세스가 받은 request 그대로 인덱스로 포워드 전달하면서, request에 위에 파라미터들도 같이 전달됨
	}
	//

%>
