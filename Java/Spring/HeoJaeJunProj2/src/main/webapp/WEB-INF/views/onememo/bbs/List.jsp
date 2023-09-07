<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="login-wrap">
  	<div class="login-html">
    <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
    	<label for="tab-1" class="tab"><a class="no-hover" href="<c:url value="/onememo/bbs/List.do"/>">게시판
    	<small>목록 페이지</small></label></a>
    <input id="tab-2" type="radio" name="tab" class="sign-up">
    	<label for="tab-2" class="tab" style="float: right; font-size: 18px;"><span></span> </label>
    
    <div class="login-form">
      <div class="sign-in-htm">
       <div class="text-right mb-2">
       		
			<a href="<c:url value="/onememo/bbs/Write.do"/>" class="btn btn-danger">글등록</a>
			<a href="<c:url value="/onememo/auth/MemberUpdate.do"/>" class="btn btn-info">회원정보수정</a>
			<a href="<c:url value="/onememo/auth/Logout.do"/>" class="btn btn-success">로그아웃</a>
		</div>
		<table class="table table-dark table-hover text-center">
			<thead>
				<tr>
					<th class="col-2">글번호</th>
					<th>제목</th>
					<th class="col-2">글쓴이</th>				
					<th class="col-2">작성일</th>
				</tr>
			</thead>
			<tbody class="table-sm down-file-body">
				<c:if test="${empty listPagingData.records }" var="isEmpty">
				<!-- ListPagingData.java에 records등으로 변수 설정, set빼고 바로 변수명 접근, 꺼내오면 Dto타입 -->		
					<tr>
						<td colspan="4">등록된 글이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not isEmpty }">
					<c:forEach var="record" items="${listPagingData.records }">
						<tr>
							<td>${record.no }</td>
							<td class="text-left"><!-- 키값 no 넘김 -->
								<a href="<c:url value="/onememo/bbs/View.do?no=${record.no}"/>">${record.title }</a> 
								<span class="badge badge-light">${record.commentCounts }</span></td>
							<td>${record.name }</td>						
							<td>${record.postDate }</td>
						</tr>
					</c:forEach>
				</c:if>	
			</tbody>
		</table>
		<!-- 페이징 출력 -->
		<div>${listPagingData.pagingString }</div>
		<!-- 검색 UI -->
		<form class="form-inline justify-content-center" method="post"><!-- 파라미터 2개 넘어옴 searchColumn, searchWord -->
	       <select class="form-control" name="searchColumn">
		    <option value="title">제목</option>
		    <option value="content">내용</option>
		    <option value="name">작성자</option>	   
		  </select>
	      <input type="text" class="form-control mx-2 my-2" placeholder="검색어를 입력하세요" name="searchWord"/>
	      <button type="submit" class="btn btn-primary">검색</button>
	    </form>    
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>