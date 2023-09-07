<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 어느 컨트롤러에서 왔는지(포워드 되었는지)에 따라 분기 -->
<c:choose>
	<c:when test="${WHERE == 'INS' }">
		<c:set var="successMessage" value="입력 성공 했어요"/>
		<c:set var="failureMessage" value="입력 실패 했어요"/>
		<c:set var="successUrl" value="/DataRoom/List.kosmo"/><!-- 입력시 List로 이동 -->
	</c:when>
	<c:when test="${WHERE == 'EDT' }">
		<c:set var="successMessage" value="수정 성공 했어요"/><!-- 수정성공시 View.kosmo로 이동 -->
		<c:set var="failureMessage" value="수정 실패 했어요"/>
		<c:set var="successUrl" value="/DataRoom/View.kosmo?no=${param.no }&nowPage=${param.nowPage }"/><!-- 글번호,현재 페이지 그대로 받음 -->
	</c:when>
	<c:otherwise>
		<c:set var="successMessage" value="삭제 성공 했어요"/>
		<c:set var="failureMessage" value="삭제 실패 했어요"/>
		<c:set var="successUrl" value="/DataRoom/List.kosmo"/>
	</c:otherwise>
</c:choose>

<script>
	<c:choose>
	<c:when test="${SUCCFAIL == 1 }">
		alert("${successMessage }");//입력 성공한것
		location.replace("<c:url value="${successUrl }"/>");
	</c:when>
	<c:when test="${SUCCFAIL == 0 }">
		alert("${failureMessage }");
		history.back();
	</c:when>
	<c:otherwise>
		alert("파일 업로드 용량을 초과 했어요");//서버측에서 유효성체크
		history.back();
	</c:otherwise>
	</c:choose>
</script>
