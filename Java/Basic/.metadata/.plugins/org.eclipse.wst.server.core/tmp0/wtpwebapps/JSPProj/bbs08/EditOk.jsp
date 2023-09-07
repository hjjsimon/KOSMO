<%@page import="model.PagingUtil"%>
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
	String no = request.getParameter("no");
	
	//현재 페이지번호 받기
	String nowPage=request.getParameter(PagingUtil.NOWPAGE);
	//검색과 관련된 파라미터 받기
	String searchColumn = request.getParameter("searchColumn");//검색항목
	String searchWord = request.getParameter("searchWord");//검색어
	String searchString="";//검색시 페이징 적용을 위한 쿼리스트링 저장용
	if(searchColumn != null && searchWord.length() !=0){		
		//검색시 적용된 페이징 번호 클릭시 처리하기 위한 추가 쿼리스트링
		searchString=String.format("searchColumn=%s&searchWord=%s", searchColumn,searchWord);
	}
		
	
	//데이타를 전달할 DTO객체 생성및 데이타 설정
	BBSDto dto = new BBSDto();
	dto.setContent(content);
	dto.setNo(no);
	dto.setTitle(title);
	//CRUD작업용 DAO계열 객체 생성
	BBSDao dao = new BBSDao(application);
	int affected=dao.update(dto);
	dao.close();
	
	if(affected ==1){
		response.sendRedirect("View.jsp?no="+no+"&"+PagingUtil.NOWPAGE+"="+nowPage+"&"+searchString);
	}
	else{
		out.println("<script>");
		out.println("alert('수정실패 했어요')");
		out.println("history.back();");
		out.println("</script>");
	}
	

%>
