<%@page import="java.util.HashMap"%>
<%@page import="model.PagingUtil"%>
<%@page import="model.bbs.BBSDto"%>
<%@page import="model.bbs.BBSDao"%>
<%@page import="java.util.Map"%>
<%@page import="model.JWTOkens"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Delete.jsp --><!-- 얘는 페이지 사이즈 안넘어서 에러, 넘겨주는 처리안함 -->
<!-- 로그인 여부 판단:필터 사용시 아래 주석처리 -->
<%-- 
<jsp:include  page="/common/IsMember.jsp"/>
--%>
<%
	
	//파라미터 받기
	String no = request.getParameter("no");

	//현재 페이지번호 받기
	int nowPage=Integer.parseInt(request.getParameter(PagingUtil.NOWPAGE));
	//검색과 관련된 파라미터 받기
	String searchColumn = request.getParameter("searchColumn");
	String searchWord = request.getParameter("searchWord");
	String searchString="";//검색시 페이징 적용을 위한 쿼리스트링 저장용
	Map map = new HashMap();
	if(searchColumn != null && searchWord.length() !=0){		
		//검색시 적용된 페이징 번호 클릭시 처리하기 위한 추가 쿼리스트링
		searchString=String.format("searchColumn=%s&searchWord=%s", searchColumn,searchWord);
		map.put("searchColumn",searchColumn);
		map.put("searchWord",searchWord);
		
	}
	
	
	//CRUD작업용 DAO계열 객체 생성
	BBSDao dao = new BBSDao(application);
	BBSDto dto=new BBSDto();
	dto.setNo(no);
	int affected=dao.delete(dto);
	int totalRecordCount = dao.getTotalRecordCount(map);
	int pageSize = Integer.parseInt(request.getParameter(PagingUtil.PAGE_SIZE));
	int totalPage= (int)Math.ceil((double)totalRecordCount/pageSize);
	dao.close();
	
	if(totalPage < nowPage) nowPage=totalPage;
	
	if(affected ==1){
		response.sendRedirect("List.jsp?"+PagingUtil.NOWPAGE+"="+nowPage+"&"+searchString);
	}
	else{
		out.println("<script>");
		out.println("alert('삭제실패 했어요')");
		out.println("history.back();");
		out.println("</script>");
	}
	

%>
