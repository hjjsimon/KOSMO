<%@page import="model.PagingUtil"%>
<%@page import="java.util.Map"%>
<%@page import="model.bbs.BBSDto"%>
<%@page import="model.bbs.BBSDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 로그인 여부 판단:필터 사용시 아래 주석처리 -->
<%-- 
<jsp:include  page="/common/IsMember.jsp"/>
--%>
<%
	//1]파라미터(키값) 받기	
	String no = request.getParameter("no");
	//현재 페이지번호 받기
	String nowPage=request.getParameter(PagingUtil.NOWPAGE);
	//검색과 관련된 파라미터 받기
	String searchColumn = request.getParameter("searchColumn");//검색항목
	String searchWord = request.getParameter("searchWord");//검색어
	
	
	//2]CRUD작업용 BBSDao생성
	BBSDao dao = new BBSDao(application);
	//레코드 하나 가져오기
	BBSDto dto= dao.selectOne(no);//현재페이지에서 selectOne쓰므로 수정페이지에서도 조회수 1증가
	//이전글/다음글 조회
	Map<String,BBSDto> map= dao.prevNext(no);
	dao.close();

%>

<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>글 수정 페이지</h1>            
        </div><!--jumbotron-->
        <form method="post" action="EditOk.jsp">
		<!-- post방식이므로 input타입 히든으로 넘기면 됨 -->
        <!-- 에러 발생은 무조건 url로 값이 넘어가는 것들을 확인하면 됨 -->
        <!-- input type="hidden"으로 value 넘기는건 노출 안되게 이동페이지로 파라미터 넘기기 위함 -->
        	<input type="hidden" name="no" value="<%=no%>"/>
        	<input type="hidden" name="<%=PagingUtil.NOWPAGE %>" value="<%=nowPage%>"/>
        	<%if(searchColumn!=null && searchWord.length() !=0 ){ %>
        	<input type="hidden" name="searchColumn" value="<%=searchColumn%>"/>
        	<input type="hidden" name="searchWord" value="<%=searchWord%>"/>
        	<%} %>
			<div class="form-group">
				<label><kbd class="lead">제목</kbd></label> <input type="text" value="<%=dto.getTitle() %>"
					class="form-control" placeholder="제목을 입력하세요" name="title">
			</div>
			<div class="form-group">
				<label><kbd class="lead">내용</kbd></label>
				<textarea class="form-control" rows="5" name="content"><%=dto.getContent() %></textarea>
			</div>
			<button type="submit" class="btn btn-primary">수정</button>
		</form>     
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>
    