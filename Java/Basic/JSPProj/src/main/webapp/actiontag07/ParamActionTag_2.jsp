<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ParamActionTag_2.jsp -->
<%
	/*
		톰캣 9이하에서 "포워드"(인클루드말고)로 페이지 전환시 파라미터로 한글 전달시 한글이 깨진다
		이때는 반드시 포워드 시키는 페이지에서 아래 코드 추가
	*/
	//request.setCharacterEncoding("UTF-8");
	String pageName="ForwardedPage.jsp?id=kosmo&name=한소인";
	String password="1234";
	
	request.setAttribute("requestScope", "리퀘스트 영역입니다");
%>
<%-- 
<jsp:forward page="<%=pageName %>&pwd=<%=password %>" />
--%>
<%-- page속성에 표현식 넣을 때는 무조건 표현식만!
<jsp:forward page="<%=pageName %>"/> <!-- 패스워드 전달안됨 -->
--%>
<!-- 이렇게 패스워드 전달해야함 -->
<jsp:forward page="<%=pageName %>">
	<jsp:param value="<%=password %>" name="pwd"/>
</jsp:forward>
 
 
 
 