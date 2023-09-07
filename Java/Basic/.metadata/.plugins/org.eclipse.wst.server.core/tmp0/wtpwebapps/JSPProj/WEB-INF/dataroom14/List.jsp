<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/template/Top.jsp" />
<div class="container" style="margin-top: 50px">
	<div class="jumbotron bg-info">
	<!-- WEB-INF 내부에 있으면 URL알아도 못찾음, 반드시 컨트롤러로 접근해야함 -->
	<!-- D:\HJJ\Workspace\Java\Basic\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPProj\upload에 올린거 저장됨 -->
		<h1>
			자료실 목록 페이지 <small>모든 자료실의 목록입니다</small>
		</h1>
	</div>
	<!--jumbotron-->
	<div class="text-right mb-2">
		<a href="<c:url value="/DataRoom/Write.kosmo"/>" class="btn btn-danger">자료 등록</a>
	</div>
	<table class="table table-dark table-hover text-center">
		<thead>
			<tr>
				<th class="col-1">번호</th>
				<th>제목</th>
				<th class="col-2">올린이</th>
				<th class="col-2">자료파일</th>
				<th class="col-1">다운수</th>
				<th class="col-2">등록일</th>
			</tr>
		</thead>
		<tbody class="table-sm down-file-body">
			<c:if test="${empty records }" var="isEmpty">
				<tr>
					<td colspan="6">등록된 자료가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not isEmpty }">
				<c:forEach var="record" items="${records}" varStatus="loop">
					<tr>
						<td>${record.no}</td>
						<td class="text-left"><a href="<c:url value="/DataRoom/View.kosmo?no=${record.no}&nowPage="/><c:out value="${param.nowPage}" default="1"/>">${record.title }</a></td>
						<td>${record.name }</td>
						<td class="text-left">
							<ul class="list-unstyled">
								<c:forEach var="file" items="${fn:split(record.attachFile,',')}">
									<li><a class="down-file${loop.count }" href="<c:url value="/DataRoom/Download.kosmo?filename=${file }&no=${record.no}"/>">${file }</a></li>
								</c:forEach>
							</ul>
						</td>
						<td id="down-count${loop.count }">${record.downCount }</td>
						<td>${record.postDate }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<!-- 페이징 출력 -->
	${paging}
	
</div>
<!--container-->
<jsp:include page="/template/Footer.jsp" />
<script>
	var tbody = document.querySelector('.down-file-body');
	tbody.onclick= function(e){
		if(e.target.nodeName==='A'){//nodeName으로 태그명 가져오면 다 대문자, tbody 내부중 a태그눌렀을 때만 if로 들어옴
			//a태그의 클래스명에서 끝에 숫자 추출
			var className = e.target.className;//타겟의 클래스명 얻어옴
			var numbering = className.substring('down-file'.length,className.length);
			//down-file92 이런식이면 앞의 length로 9, length -1이므로 마지막 숫자, 결론적으로 숫자만 추출함
			console.log('넘버링 숫자:',numbering);
			//아이디로 다운수가 있는  td요소 얻기
			var td = tbody.querySelector('#down-count'+numbering);
			//다운수 읽어오기(숫자로 변환)
			var downCount = parseInt(td.textContent);
			//1을 더해서 다시 설정
			td.textContent = downCount+1;
		}
	};

</script>

