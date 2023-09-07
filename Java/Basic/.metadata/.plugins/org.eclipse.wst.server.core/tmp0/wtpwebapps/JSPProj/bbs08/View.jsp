<%@page import="model.PagingUtil"%>
<%@page import="model.JWTOkens"%>
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
	
	//페이지사이즈 받기-삭제시 페이징처리용
	String pageSize=request.getParameter(PagingUtil.PAGE_SIZE);
	//검색과 관련된 파라미터 받기
	String searchColumn = request.getParameter("searchColumn");//검색항목
	String searchWord = request.getParameter("searchWord");//검색어
	String searchString="";//검색시 페이징 적용을 위한 쿼리스트링 저장용
	if(searchColumn != null && searchWord.length() !=0){		
		//검색시 적용된 페이징 번호 클릭시 처리하기 위한 추가 쿼리스트링
		searchString=String.format("searchColumn=%s&searchWord=%s", searchColumn,searchWord);
	}
	
	
	//2]CRUD작업용 BBSDao생성
	BBSDao dao = new BBSDao(application);
	
	//이전 페이지명 얻기:List.jsp에서 뷰로 올때만 조회수 증가 하기 위함
	String referer=request.getHeader("referer");
	//요청헤더명으로 요청헤더 가져옴 ex)http://localhost:8080/JSPProj/bbs08/List.jsp 출력
	//referer : http://localhost:8080/JSPProj/bbs08/List.jsp
	String prevPage = referer.substring(referer.lastIndexOf("/")+1);
	//레코드 하나 가져오기
	BBSDto dto= dao.selectOne(no,prevPage);//조회수 1증가, var...args라 여러인자 가능
	//이전글/다음글 조회
	Map<String,BBSDto> map= dao.prevNext(no);//글번호, 이전페이지명 넘겨줌
	dao.close();//연결끊기

%>
<jsp:include page="/template/Top.jsp" />
<script>
	function isDelete(){
		if(confirm("정말로 삭제하시겠습니까?")){
			location.replace("Delete.jsp?no=<%=no+"&"+PagingUtil.NOWPAGE+"="+nowPage+"&"+searchString+PagingUtil.PAGE_SIZE+"="+pageSize%>");
		}
	}

</script>



<div class="container" style="margin-top: 50px">
	<div class="jumbotron bg-info">
		<h1>상세보기 페이지</h1>
	</div>
	<!--jumbotron-->
	<table class="table table-bordered">
		<tbody class="table-sm">
			<tr>
				<th class="w-25 bg-dark text-white text-center">번호</th>
				<td><%=dto.getNo() %></td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">작성자</th>
				<td><%=dto.getName() %></td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">작성일</th>
				<td><%=dto.getPostDate() %></td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">조회수</th>
				<td><%=dto.getHitCount() %></td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">제목</th>
				<td><%=dto.getTitle() %></td>
			</tr>
			<tr>
				<th class="bg-dark text-white text-center" colspan="2">내 용</th>
			</tr>
			<tr>
				<td colspan="2"><%=dto.getContent().replace("\r\n","<br/>") %></td>
				<!-- 게시판작성글의 엔터는 브라우저 표시중 스페이스 하나처리됨, 이를 줄바꿈으로 바꾸는 코드 -->

			</tr>
		</tbody>
	</table>
	<!-- 이전글/다음글 -->
	<div>
		<table class="table">
			<tbody>
				<tr>
					<td class="border-top-0 bg-info" style="width: 7%">이전글</td>
					<td class="border-top-0">
					<%=map.get("PREV")==null?"이전글이 없습니다":String.format("<a href='View.jsp?no=%s'>%s</a>",map.get("PREV").getNo(),map.get("PREV").getTitle()) %>
					</td>
				</tr>
				<tr>
					<td class="bg-info">다음글</td>
					<td class="border-top-0">
					<%=map.get("NEXT")==null?"다음글이 없습니다":String.format("<a href='View.jsp?no=%s'>%s</a>",map.get("NEXT").getNo(),map.get("NEXT").getTitle()) %>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- 수정/삭제/목록 컨트롤 버튼 -->
	<div class="text-center">
		<!-- 세션기반 -->
		<%--if(session.getAttribute("USER-ID").equals(dto.getId())){ --%>
		<!-- 세션영역에서 읽어온 id와 작성자의 id를 비교해서 같으면 수정, 삭제 나오게함(게시판 토큰전용이라 테스트x) -->
		
		<!-- 토큰기반 -->
		<%
			String token=JWTOkens.getToken(request, application.getInitParameter("COOKIE-NAME"));//web.xml에 쿠키네임 해놓음
			String authenticationId=JWTOkens.getTokenPayloads(token, "/resources/tokens","secret-key").get("sub").toString();
			//토큰으로 인증한 아이디
			if(authenticationId.equals(dto.getId())){
		%>
		<!-- 페이지 이동시 쿼리스트링으로 파라미터 잘 넘겨야 왔다갔다 이동이 용이해짐 -->
		<a href="Edit.jsp?no=<%=no+"&"+PagingUtil.NOWPAGE+"="+nowPage+"&"+searchString %>" class="btn btn-success">수정</a> 
		<!-- 쿼리스트링으로 no넘겨줘야 Edit에서 받음 -->
		<a href="javascript:isDelete();" class="btn btn-success">삭제</a> 
		<%}%>
		<a href="List.jsp?<%=PagingUtil.NOWPAGE+"="+nowPage+"&"+searchString %>" class="btn btn-success">목록</a>
	</div>
</div>

<!--container-->
<jsp:include page="/template/Footer.jsp" />
