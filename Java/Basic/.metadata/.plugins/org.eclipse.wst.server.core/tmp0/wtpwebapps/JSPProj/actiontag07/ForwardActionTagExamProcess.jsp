<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/Validate.jsp" %> 
<!-- ForwardActionTagExamProcess.jsp -->
<%
	//1. 서버측 유효성 체크를 추가하라(메소드 가져다쓰기/ 단, 체크박스는 2개이상 선택)
	//2. 모든 파라미터를 받아서 맵 컬렉션에 저장하고(키는 파라미터명) 다시 맵 컬렉션을 리퀘스트 영역에 저장하여라
	//3. 액션태그를 이용해서 ForwardActionTagExamResult.jsp로 이동하여라
	
	//1.
	Map<String,String[]> map=request.getParameterMap();
	if(!isValidate(out, map.get("username")[0],"아이디를 입력하세요")) return;
	if(!isValidate(out, map.get("password")[0],"비밀번호를 입력하세요")) return;
	if(!isValidate(out, map.get("sports"), "운동종목을 선택하세요", 2)) return;//체크박스
	String ages = map.get("ages")==null ? null : map.get("ages")[0];//선택하나만해도 넘겨지는 과정생김, 나이 null이면 뭐 에러난대
	if(!isValidate(out,ages,"연령대를 선택하세요")) return;
	//라디오박스는 선택안하면 파라미터 안넘어옴, null체크도 메소드에서 해놓음, value가 string형 배열

	//2.
	request.setAttribute("params", map);
	
	/*
	String username = params.get("username")[0];
	String password = params.get("password")[0];
	String[] sports = params.get("sports");
	String[] ages = params.get("ages");
	request.setAttribute("username", username);
	request.setAttribute("password", password);
	request.setAttribute("sports", sports);
	request.setAttribute("ages", ages);
	
	
	//유효성체크
	if(!isValidate(out, username, "아이디를 입력하세요?")) return;
	if(!isValidate(out, password, "비밀번호를 입력하세요?")) return;
	if(!isValidate(out, sports, "체크박스는 2가지 이상 선택하세요?", 2)) return;
	if(!isValidate(out, ages, "나이를 선택하세요?", 1)) return;
	*/
%>	
<!-- 3. -->
<jsp:forward page="/actiontag07/ForwardActionTagExamResult.jsp"/>




