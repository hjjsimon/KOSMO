<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- CookieExamLoginProcess.jsp -->
<%@include file="/common/Validate.jsp" %>
<%
	//사용자 입력값 받기
	String id = request.getParameter("id");
	if(!isValidate(out, id, "아이디를 입력하세요")) return;//입력안했으니 리턴
	String pwd = request.getParameter("pwd");
	if(!isValidate(out, pwd, "비밀번호를 입력하세요")) return;//입력안했으니 리턴
	//체크박스는 유효성체크x, 사용자가 체크할수도,안할수도 있음
	String idSave = request.getParameter("id-save");//변수에 - 금지, 아이디저장 체크안하면 null
	if("KIM".equals(id.trim()) && "1234".equals(pwd.trim())){
		//1.로그인처리]-세션영역에 속성(주로 식별자(아이디)만) 저장
		session.setAttribute("USER-ID", id.trim());//속성명내맘
		session.setAttribute("USER-PWD", pwd.trim());//원래 pwd저장 안함, 위에 id만 저장, 공부용
		
		//아이디저장 체크여부 판단
		if(idSave != null){//체크한경우-아이디를 쿠키에 저장
			Cookie cookie = new Cookie("USER_ID",id.trim());//쿠키명 내맘
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);//응답헤더에 추가
		}
		else{//체크안한경우-저장된 쿠키삭제
			Cookie cookie = new Cookie("USER_ID","");//빈문자열로 바꿈
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(-1);//음수
			response.addCookie(cookie);
		}
		//2.로그인처리후 CookieExamIndex.jsp로 이동 (세션영역 저장이라 굳이 포워드 불필요)
		response.sendRedirect("CookieExamIndex.jsp");
	}
	else{//아이디, 비번 불일치
		//리퀘스트 영역에 필요한 데이터 저장
		request.setAttribute("ERROR", "아이디와 비번 불일치");
		//리퀘스트에 저장했으니 포워드로 이동
		request.getRequestDispatcher("CookieExamIndex.jsp").forward(request, response);
	}
	

%>
