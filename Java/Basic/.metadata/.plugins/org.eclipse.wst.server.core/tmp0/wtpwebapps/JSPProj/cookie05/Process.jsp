<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/Validate.jsp" %>
<%
	String id = request.getParameter("id");
	if(!isValidate(out, id, "아이디를 입력하세요")) return;
	String pwd = request.getParameter("pwd");
	if(!isValidate(out, pwd, "비밀번호를 입력하세요")) return;
	String idSave = request.getParameter("id-save");
	if("KOSMO".equals(id.trim()) && "1234".equals(pwd.trim())){
		session.setAttribute("USER-ID", id.trim());
		if(idSave != null){
			Cookie cookie = new Cookie("USER_ID",id.trim());
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
		else{
			Cookie cookie = new Cookie("USER_ID","");
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		response.sendRedirect("Login.jsp");
	}
	else{
		request.setAttribute("ERROR", "아이디와 비번 불일치");
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}
	

%>
