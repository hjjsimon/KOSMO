<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- UseBeanFormResult.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>&lt;jsp:useBean&gt;액션태그로 폼 요소값 받기 결과 페이지</h1>            
        </div><!--jumbotron-->
        <!-- STEP1]액션태그로 자바빈 객체 생성(액션태그 쓰는 이유가 자바코드 안쓰기 위함) -->
        <jsp:useBean id="action" class="model.MemberDTO"/>
        <!-- 위 실행시 영역에 없으니, MemberDTO객체 하나 생성됨 -->

        <h1 class="display-4">파라미터명과 자바빈의 속성이 불일치시</h1>
        <!-- 받은 파라미터로 속성 설정 -->
        <!-- value는 사용자입력값으로 자동세팅, 쓸 필요 없음 -->
        <!-- username파라미터로 넘어오는걸 value에 넣음  -->
        <jsp:setProperty property="id" name="action" param="username"/>
        <jsp:setProperty property="pwd" name="action" param="password"/>
        <jsp:setProperty property="name" name="action" />
        <h1 class="display-4">파라미터명과 자바빈의 속성이 일치시</h1>
        <jsp:setProperty property="*" name="action" /><!-- * 하면 다 가져옴 -->
        <%=action %>
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>
    