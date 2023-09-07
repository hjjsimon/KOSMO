<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="model.MemberDTO"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	JSTL사용]
	1] WEB-INF/lib폴더에 필요한 jar파일 추가(이미 함)
	2]taglib지시어를 JSP페이지에 추가
 -->
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- SetTagIndex.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">set태그</legend><!-- setAttribute 동일, 자바코드 쓰기 싫어서 쓰는것 -->
        	<h3>set태그로 EL에서 사용할 변수 설정</h3>
        	<!--
				var속성:문자열만
				value속성:값(문자열이든 숫자든),표현식(원래안씀, 지금공부용, 서블릿만들기 귀찮아서 씀),EL식 모두 가능
				scope속성:문자열만(
					"page","request","session","application"중 하나)"page"가 기본 값			        
		 	-->
        	<ul class="list-unstyled"><!-- 아래 모두 안나옴, 소스보기에도 없음, 자바코드로 바뀌기 때문 -->
       			<li>value속성에 직접 값 설정 : <c:set var="directvar" value="100" /> </li><!-- 태그형태지만 소스보기에서 안보임, 자바코드로 setAttribute로 바뀜 -->
        		<!-- 위 코드는 아래의 자바코드와 동일 -->
        		<%-- pageContext.setAttribute("directvar","100"); --%>
        		<li>value속성에 EL로 값 설정(주로씀) : <c:set var="elvar" value="${directvar }"/> </li><!-- directvar 앞에 pageScope생략된것 -->
        		<li>value속성에 표현식으로 값 설정(잘안씀) : <c:set var="expvar" value="<%=new Date(new java.util.Date().getTime()) %>" /></li>
        		<li>시작태그와 종료태그 사이에 값 설정 : <c:set var="betweenvar"><h4>시작태그와 종료태그</h4>사이에 값을 설정합니다</c:set> </li>
        	</ul>
        	<h3>set태그로 설정한 값 출력하기</h3>
        	<ul class="list-unstyled">
        		<li>directvar : ${pageScope.directvar }</li><!-- pageScope생략된것,써도됨 -->
        		<li>elvar : ${elvar }</li>
        		<li>expvar : ${expvar }</li>
        		<li>betweenvar : ${betweenvar }</li>
        	</ul>
        	<h3>set태그로 각 영역에 저장</h3>
        	<c:set var="pageVar" value="페이지 영역"/>
        	<c:set var="requestVar" value="리퀘스트 영역" scope="request"/>
        	<c:set var="sessionVar" value="세션 영역" scope="session"/>
        	<c:set var="applicationVar" value="어플리케이션 영역" scope="application"/>
        	<h3>EL로 출력하기</h3>
        	<ul class="list-unstyled">
        		<li>pageVar : ${pageVar }</li>
        		<li>requestVar : ${requestVar }</li>
        		<li>sessionVar : ${sessionVar }</li>
        		<li>applicationVar : ${applicationVar }</li>
        	</ul>
        	<h3>set태그로 자바빈 객체 저장</h3>
        	<c:set scope="request" var="defaultMember" value="<%=new MemberDTO() %>" /><!-- 기본생성자라 초기화x, 아래 모두 null이라 EL출력안됨 -->
        	<h5>EL로 출력</h5>
        	<ul class="list-unstyled">
        		<li>이름 : ${defaultMember.name }</li><!-- 원래 getName, name속성이 이미 있으니까 게터세터 만들어져있음, 그냥 바로 name으로 접근 -->
        		<li>아이디 : ${defaultMember.id }</li>
        		<li>비번 : ${defaultMember.pwd }</li>
        	</ul>
        	<c:set scope="request" var="argsMember" value='<%=new MemberDTO("KIM","1234","김길동","20") %>' /><!-- 이제 잘 나옴 -->
        	<h5>EL로 출력</h5>
        	<ul class="list-unstyled">
        		<li>이름 : ${argsMember.name }</li>
        		<li>아이디 : ${argsMember.id }</li>
        		<li>비번 : ${argsMember.pwd }</li>
        	</ul>
        	<!--
				set태그의 target속성과 property속성은
				자바빈 객체나
				컬렉션계열 객체 설정할때
				사용할 수 있는 속성.
				
				※target속성:반드시 EL식이나 표현식만 가능
				 property속성:값,표현식,EL식 모두 가능.
				  자바빈 인 경우-속성명(멤버변수명)
				 Map컬렉션 - 키값  	
				
				※scope속성은 var속성을 지정한 태그에서만 설정 가능(아래 c:set에서 설정해봤자 안먹음)
			     -->
				<!-- target과 property를 사용해서
			         자바빈이나 컬렉션에 값을 설정할때는
			         var속성을 지정하면 안된다.
			    -->
				<!-- target속성과 property속성을 이용해서
			            자바빈 객체의 속성값 설정
	    	 -->
	    	 <!-- 위에 defaultMember 내의 값이 초기화 안돼있음, 이를 지금껏 안썼던 target, property으로 초기화, target에 그냥 문자열x, EL식 써야함-->
        	<c:set target="${defaultMember }" property="name" value="박길동" />
        	<c:set target="${defaultMember }" property="id" value="PARK" />
        	<c:set target="${defaultMember }" property="pwd" value="9999" />
        	<h5>target 및 property 속성으로 자바빈의 필드값 설정 후 EL로 출력</h5>
        	<ul class="list-unstyled">
        		<li>이름 : ${defaultMember.name }</li><!-- 원래 getName, name속성이 이미 있으니까 게터세터 만들어져있음, 그냥 바로 name으로 접근 -->
        		<li>아이디 : ${defaultMember.id }</li>
        		<li>비번 : ${defaultMember.pwd }</li>
        	</ul>
        	<%
        		//리스트 계열 컬렉션-자바빈(MemberDTO) 저장
        		List<MemberDTO> list = Arrays.asList(
        				(MemberDTO)request.getAttribute("defaultMember"), 
        				(MemberDTO)request.getAttribute("argsMember"));
        	%>
        	<c:set scope="request" value="<%=list %>" var="list" />
        	\${list }<!-- [이름:박길동,아이디:PARK,비번:9999,나이:null, 이름:김길동,아이디:KIM,비번:1234,나이:20] -->
        	<c:set target="${list[0] }" property="name" value="한소인" /><!-- 0번방 defaultMember의 속성 바꿈 -->
        	<c:set target="${list[0] }" property="id" value="KOSMO" />
       	 	<c:set target="${list[0] }" property="pwd" value="5678" />
        	<h5>target 및 property속성으로 리스트 컬렉션의 첫번째 요소의 값 설정후 EL로 출력</h5>
        	<ul class="list-unstyled">
        		<li>이름 : ${defaultMember.name }</li>
        		<li>아이디 : ${defaultMember.id }</li>
        		<li>비번 : ${defaultMember.pwd }</li>
        	</ul>	
        	<%
        		//맵 계열 컬렉션
        		Map<String,MemberDTO> map = new HashMap<>();
        		map.put("default",list.get(0));
        		map.put("args",list.get(1));
        	%>
        	<c:set var="map" value="<%=map %>" scope="request" />
        	<c:set target="${map['default'] }" property="name" value="한소인2"/>
        	<c:set target="${map['default'] }" property="id" value="KOSMO2"/>
        	<c:set target="${map['default'] }" property="pwd" value="1212"/>
        	<!-- map에 저장한 default키값의 list.get(0)의 속성변경 -->	
        	<!-- 근데 에러남, default는 키워드가 EL에 설정돼있어서 []로 해줘야함, 안쓰는게 좋음 -->
        	<h5>target 및 property속성으로 맵 컬렉션의 첫번째 요소의 값 설정후 EL로 출력</h5>
        	<ul class="list-unstyled">
        		<li>이름 : ${defaultMember.name }</li>
        		<li>아이디 : ${defaultMember.id }</li>
        		<li>비번 : ${defaultMember.pwd }</li>
        	</ul>
        </fieldset>        
    </div><!--container-->
<!-- 여기부터 주석처리하고 출력해보기 -->    
<jsp:forward page="SetTagResult.jsp" >
	<jsp:param value="android" name="subject"/>
</jsp:forward>