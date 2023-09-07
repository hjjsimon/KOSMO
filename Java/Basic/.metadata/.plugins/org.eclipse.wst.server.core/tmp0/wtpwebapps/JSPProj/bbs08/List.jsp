<%@page import="model.PagingUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.bbs.BBSDto"%>
<%@page import="java.util.List"%>
<%@page import="model.bbs.BBSDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 로그인 여부 판단:필터(로그인 했는지 검증해서 걸러버림) 사용시 아래 주석처리 -->
<%-- 
<jsp:include  page="/common/IsMember.jsp"/>
--%>
<%	
	//JSP 모델1 방식, 모델2 방식 존재(그림교안확인)
	//모델1 방식(소규모 프로젝트용): JSP는 HTML을 생성하는 템플릿, JSP + 자바코드 구성(JSP가 요청받아(자바코드 섞일수밖에 없음) 파라미터 받고 처리함)
						//유지보수가 어려움, 개발생산성이 높다(1달 걸릴거 1주일)
	//모델2 방식(대규모 프로젝트용): 자바코드가 없음, 서블릿으로 작성하여(JSP가 요청받지않음, 별도의 서블릿이 요청받음, JSP는 결과만 출력함(이 때 쓰는게 EL, JSTL 언어))
						//서블릿에서 request영역에 저장 후 JSP로 포워딩시킴
						//유지보수가 쉬움, 개발생산성이 낮다(1주일 걸릴거 1달)

	//시작 및 끝 행번호(페이징관련) 와 검색 관련 데이타 저장용
	Map map = new HashMap();
	//검색과 관련된 파라미터 받기
	//검색UI의 form태그 action값없으므로 현재페이지로 전송, 여기서 파라미터 받아야함
	String searchColumn = request.getParameter("searchColumn");//검색항목
	String searchWord = request.getParameter("searchWord");//검색어
	String searchString="";//검색시 페이징 적용을 위한 쿼리스트링 저장용
	if(searchColumn != null && searchWord.length() !=0){
		map.put("searchWord",searchWord);
		map.put("searchColumn",searchColumn);
		//검색시 적용된 페이징 번호 클릭시 처리하기 위한 추가 쿼리스트링
		searchString=String.format("searchColumn=%s&searchWord=%s", searchColumn,searchWord);
	}
	

	//전체 글 목록 가져오기
	BBSDao dao = new BBSDao(application);
	
	//페이징을 위한 로직 시작
	//[제너릭 메소드 미 사용]
	/*
	//전체 레코드수
	int totalRecordCount = dao.getTotalRecordCount(map);
	//페이지 사이즈
	int pageSize=Integer.parseInt(this.getInitParameter("PAGE-SIZE"));//한페이지에 3개씩(web.xml에서 초기화파라미터 써서 2개로 바꿈)
	//블락 페이지
	int blockPage=Integer.parseInt(this.getInitParameter("BLOCK-PAGE"));//하단에 2페이지씩 보여줌(web.xml에서 초기화파라미터 써서 3개로 바꿈)
	//현재 페이지 번호
	int nowPage = request.getParameter(PagingUtil.NOWPAGE)==null ? 1 : Integer.parseInt(request.getParameter(PagingUtil.NOWPAGE));
	//시작 및 끝 ROWNUM구하기(구간쿼리용)
	int start = (nowPage -1)*pageSize+1;
	int end = nowPage*pageSize;	
	//페이징을 위한 로직 끝
	map.put(PagingUtil.START,start);
	map.put(PagingUtil.END,end);
	*/
	//[제너릭 메소드 사용]//이걸로 map 세팅, 검색안하면 map 비어있음
	PagingUtil.setMapForPaging(map, dao, request, this);
	int totalRecordCount=Integer.parseInt(map.get(PagingUtil.TOTAL_RECORD_COUNT).toString());
	int pageSize=Integer.parseInt(map.get(PagingUtil.PAGE_SIZE).toString());
	int blockPage=Integer.parseInt(map.get(PagingUtil.BLOCK_PAGE).toString());
	int nowPage=Integer.parseInt(map.get(PagingUtil.NOWPAGE).toString());
	List<BBSDto> artices= dao.selectList(map);
	dao.close();
	System.out.println("목록 요청작업 완료");
%>


<jsp:include page="/template/Top.jsp" />
<div class="container" style="margin-top: 50px">
	<div class="jumbotron bg-info">
		<h1>
			목록 페이지 <small>(<%=nowPage %>/<%=map.get(PagingUtil.TOTAL_PAGE) %>)</small>
		</h1>
	</div>
	<!--jumbotron-->
	<div class="text-right mb-2">
		<a href="Write.jsp" class="btn btn-danger">글등록</a>
	</div>
	<table class="table table-dark table-hover text-center">
		<thead>
			<tr>
				<th class="col-1">번호</th>
				<th>제목</th>
				<th class="col-2">작성자</th>
				<th class="col-1">조회수</th>
				<th class="col-2">작성일</th>
			</tr>
		</thead>
		<tbody class="table-sm">
			<% if(artices.isEmpty()){ %>
			<tr>
				<td colspan="5">등록된 글이 없습니다.</td>
			</tr>
			<%}else{
					int loopCount=0;
					for(BBSDto article:artices){
						
				%>

			<tr>
				<td><%=totalRecordCount - (((nowPage - 1) * pageSize) + loopCount) %></td>
				<td class="text-left"><a href="View.jsp?no=<%=article.getNo() %>&<%=PagingUtil.NOWPAGE+"="+nowPage+"&"+searchString+"&"+PagingUtil.PAGE_SIZE+"="+pageSize %>"><%=article.getTitle() %></a></td>
				<!-- no와 nowpage번호를 넘김 -->
				<td><%=article.getName() %></td>
				<td><%=article.getHitCount() %></td>
				<td><%=article.getPostDate() %></td>
			</tr>
			
			<%		loopCount++;
			
					}} %>

		</tbody>
	</table>
	<!-- 페이징 출력 -->
	<%=PagingUtil.pagingBootStrapStyle(totalRecordCount, pageSize, blockPage, nowPage, request.getContextPath()+"/bbs08/List.jsp?"+searchString+"&") %>
	<!-- 검색 UI -->
	<form class="form-inline justify-content-center" method="post"><!-- action없으므로 현재페이지로 action(기본값) -->
		<select class="form-control" name="searchColumn">
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="name">작성자</option>
		</select> 
		<input type="text" class="form-control mx-2 my-2"
			placeholder="검색어를 입력하세요" name="searchWord" />
		<button type="submit" class="btn btn-primary">검색</button>
	</form>
	
</div>
<!--container-->
<jsp:include page="/template/Footer.jsp" />
