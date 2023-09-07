<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/Top.jsp" %>
</head>
<body>
	<div class="login-box">
	  <h2>MEDI-Q</h2>
	  <form>
	    <div class="user-box">
	      <input type="text" name="id">
	      <label>아이디</label>
	    </div>
	    <div class="user-box">
	      <input type="password" name="pwd">
	      <label>비밀번호</label>
	    </div>
	    <a href="#">
	      <span></span>
	      <span></span>
	      <span></span>
	      <span></span>
	      로그인
	    </a>
	  </form>
	</div>
<jsp:include page="/template/Footer.jsp"/>