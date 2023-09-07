<%@page import="model.bbs.BBSDto"%>
<%@page import="model.bbs.BBSDao"%>
<%@page import="java.util.Map"%>
<%@page import="model.JWTOkens"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- WriteOk.jsp -->
<!-- 로그인 여부 판단:필터 사용시 아래 주석처리 -->
<%-- 
<jsp:include  page="/common/IsMember.jsp"/>
--%>
<%
	//POST방식일때 한글 깨지는 거 처리용(톰캣 10.0.27버전은 안깨짐)
	//request.setCharacterEncoding("UTF-8");
	//파라미터 받기
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	//작성자의 아이디 얻기
	//String id = session.getAttribute("USER-ID").toString();//세션영역에서
	
	Map<String,Object> payloads= JWTOkens.getTokenPayloads(JWTOkens.getToken(request,application.getInitParameter("COOKIE-NAME")), 
			"/resources/tokens", "secret-key");
	
	String id= payloads.get("sub").toString();
	//데이타를 전달할 DTO객체 생성및 데이타 설정
	BBSDto dto = new BBSDto();
	dto.setContent(content);
	dto.setId(id);
	dto.setTitle(title);
	//CRUD작업용 DAO계열 객체 생성
	BBSDao dao = new BBSDao(application);
	int affected=dao.insert(dto);
	dao.close();
	
	if(affected ==1){
		response.sendRedirect("List.jsp");
	}
	else{
		out.println("<script>");
		out.println("alert('입력실패 했어요')");
		out.println("history.back();");//히스토리.백 -> 입력값 유지됨
		out.println("</script>");
	}
	

%>
