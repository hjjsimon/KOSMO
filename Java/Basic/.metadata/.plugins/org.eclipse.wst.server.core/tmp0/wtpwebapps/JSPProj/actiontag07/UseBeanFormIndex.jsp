<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- UseBeanFormIndex.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>&lt;jsp:useBean&gt;액션태그 폼요소값 받기</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
			<legend class="w-auto p-3">파라미터명과 자바빈의 속성이 불일치시</legend>
			<form action="UseBeanFormResult.jsp">
				<div class="form-group">
					<label for="username">아이디</label> <input type="text"
						class="form-control" placeholder="아이디를 입력하세요" id="username"
						name="username">
				</div>
				<div class="form-group">
					<label for="password">비밀번호</label> <input type="password"
						class="form-control" placeholder="비밀번호를 입력하세요" id="password"
						name="password">
				</div>
				<div class="form-group">
					<label>이름</label> <input type="text" class="form-control"
						placeholder="이름를 입력하세요" name="name">
				</div>
				<button type="submit" class="btn btn-primary">확인</button>
			</form>
		</fieldset>
		<fieldset class="form-group border p-3">
			<legend class="w-auto p-3">파라미터명과 자바빈의 속성이 일치시</legend><!-- 일치시키는게 좋음 -->
			<form action="UseBeanFormResult.jsp">
				<div class="form-group">
					<label for="username">아이디</label> <input type="text"
						class="form-control" placeholder="아이디를 입력하세요" id="username"
						name="id">
				</div>
				<div class="form-group">
					<label for="password">비밀번호</label> <input type="password"
						class="form-control" placeholder="비밀번호를 입력하세요" id="password"
						name="pwd">
				</div>
				<div class="form-group">
					<label>이름</label> <input type="text" class="form-control"
						placeholder="이름를 입력하세요" name="name">
				</div>
				<button type="submit" class="btn btn-primary">확인</button>
			</form>
		</fieldset>
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>