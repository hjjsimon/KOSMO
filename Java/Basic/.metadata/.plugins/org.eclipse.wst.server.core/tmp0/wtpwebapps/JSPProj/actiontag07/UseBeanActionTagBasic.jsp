<%@page import="model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- UseBeanActionTagBasic.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>&lt;jsp:useBean&gt;액션태그</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">액션태그로 자바빈 객체 생성하기</legend>
        	<!--
        		자바빈(좁은의미,넓은의미 있음) = 자바클래스 
        		넓은 의미의 자바빈: 모든 자바클래스 의미
        		좁은 의미의 자바빈: 필드의 접근지정자가 private인 자바클래스(프라이빗 접근 불가라 게터 세터를 가짐,게터 세터로 초기화 가능)
        		
        		*액션태그에서의 자바빈은 좁은 의미의 자바빈 의미
        	-->
        	<%-- 이렇게 주석처리해야함
        	<jsp:useBean id="member" class="model.MemberDTO" scope="page"/>
        	--%>
        	<jsp:useBean id="member" class="model.MemberDTO" scope="request"/>
        	
        	
        	<!-- 얘도 소스보기에서 안보임, 서블릿변환시 자바코드로 바뀌어 실행되는 코드 -->
        	<!-- MemberDTO에서 기본생성자로 가져온거라 초기화 안되고 null로 나옴 -->
        	<!-- 액션태그로 MemberDTO 객체를 생성하고, id에 넣는다는 것 -->
        	<!-- id의 member는 영역에 저장된 속성명(키)이 됨, 그리고 request영역(scope로 지정)에서 member를 키로 찾음 -->
        	<!-- 근데 키로 지정한거 없음, 이럴 때는 기본생성자로 객체생성하고 member에 넣고, page영역에 넣음 -->
        	<!-- scope는 page,request,session,application 4개 -->
        	<!-- class는 있으면 찾고, 없으면 만듦/ class대신 type 시 있으면 찾고, 없으면 그냥 null줌(에러남) -->
        	<!-- 위의 액션태그를 자바코드로 코딩시 아래(4줄)와 같다 -->
        	
        	<%--
        		//1]scope속성에 지정한 영역에서 객체 얻기
				//id에 지정한 이름으로 속성명, 인스턴스 변수명 결정됨(1인 2역)
				MemberDTO member = (MemberDTO)request.getAttribute("member");//Bean코드의 변수랑 중복이라 에러
				//2]영역에 존재하지 않을때 생성
				if(member==null){
					//무조건 기본생성자로 생성]
					member = new MemberDTO();
					//생성된 객체를 scope에 지정한 영역에 저장(속성명은 id에 지정한 이름으로..)
					request.setAttribute("member", member);
				}	
			--%>		
        
        	<%=member %><!-- 이름:null,아이디:null,비번:null,나이:null -->
	        	
      	 	<h4>&lt;jsp:setProperty&gt;액션태그로 자바빈객체 속성설정</h4>
      	 	<!-- 클래스에 정의했던 id,pwd 이런걸 필드, 속성이라고도함  -->
      	 	<!-- 
      	 		-setter를 호출한 것과 같다
      	 		-name속성에는 액션태그로 자바빈 객체 생성시 id속성에 지정한 값
      	 			member.setId("KIM")와 아래 코드 동일
      	 	 -->
      	 	<jsp:setProperty property="id" name="member" value="KIM"/>
			<jsp:setProperty property="pwd" name="member" value="1234"/>
      	 	<!-- 이게 세터랑 동일, property는 속성명, 밸류는 값, name은 어느 객체에 넣는지 -->
      		<!-- 자바코드로 속성설정 -->
      		<!-- 액션태그에서 가져온걸 스크립팅요소에서도 쓸 수 있음
      			근데 반대로 자바코드로 영역에서 가져오면 액션태그에는 못씀(result페이지 확인) -->
      		<%
      			member.setAge("20");	
      			member.setName("김길동");
      		%>
      		<h2 class="display-4">속성 설정후 출력</h2>
      		<%=member %>
      		<h4>&lt;jsp:getProperty&gt;액션태그로 자바빈객체 속성읽기</h4>
      		<!-- getter를 호출한 것과 같다 -->
      		<ul class="list-unstyled">
      			<li>아이디 : <jsp:getProperty property="id" name="member"/></li>
      			<li>비밀번호 : <jsp:getProperty property="pwd" name="member"/></li>
      			<li>이름 : <%=member.getName() %></li>
      			<li>나이 : <%=member.getAge() %></li>
      		</ul>        
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>
<jsp:forward page="UseBeanActionTagBasicResult.jsp"/>