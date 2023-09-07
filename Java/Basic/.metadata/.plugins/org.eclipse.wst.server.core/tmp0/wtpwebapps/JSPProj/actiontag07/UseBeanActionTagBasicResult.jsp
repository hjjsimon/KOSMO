<%@page import="model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- UseBeanActionTagBasicResult.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>&lt;jsp:useBean&gt;액션태그 결과페이지</h1>            
        </div><!--jumbotron-->
       		<%--
       			//자바코드로 영역에서 가져오기
       			MemberDTO member = (MemberDTO)request.getAttribute("member");
       			//자바코드(스크립팅 요소)에서 사용한 인스턴스 변수(member)는 useBean액션태그에서 사용불가
       			//단, 액션태그에서 사용한 변수(id속성에 지정한 값)는 자바코드(스크립팅 요소)에서도 사용가능
       			//<li>아이디 : <jsp:getProperty property="id" name="member"/> </li>  -> 이거 에러남
       		--%>
       		<!-- useBean액션태그로 영역에서 가져오기 -->
       		<jsp:useBean id="member" scope="request" type="model.MemberDTO"/>
       		<!-- 이번에는 class말고 type을 씀 이미 있으니까(class도됨) -->
       		
       		<h1 class="display-4">액션태그로 출력</h1>
      		<ul class="list-unstyled">
      			<li>아이디 : <jsp:getProperty property="id" name="member"/></li>
      			<li>비밀번호 : <jsp:getProperty property="pwd" name="member"/></li>
      			<li>이름 : <jsp:getProperty property="name" name="member"/></li>
      			<li>나이 : <jsp:getProperty property="age" name="member"/></li>
      		</ul>      
      		<h1 class="display-4">자바태그(스크립팅요소)로 출력</h1>
      		<ul class="list-unstyled">
      			<li>아이디 : <%=member.getId() %></li>
      			<li>비밀번호 : <%=member.getPwd() %></li>
      			<li>이름 : <%=member.getName() %></li>
      			<li>나이 : <%=member.getAge() %></li>
      		</ul>      
      		  
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>