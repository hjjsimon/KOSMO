<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--ELError.jsp-->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>EL 에러(에러는 아니지만 출력이 안되거나 0출력되는 경우)</h1>            
        </div><!--jumbotron-->
        <h4>+를 숫자형식이 아닌 문자열에 적용시</h4>       
        <ul class="list-unstyled">
        	<li>\${'100'+100 }[정상] : ${'100'+100 }</li><!-- 자바는 100100, EL은 200(문자숫자는 숫자처리,Integer.parseInt()하는것) -->
        	<li>\${'백'+100 }[500에러] : \${'백'+100 }</li>
        </ul>
        <h4>.인덱스번호 형식으로 배열이나 리스트계열 컬렉션 요소 접근시(에러)</h4>
        <%
        	String[] mountains = {"설악산","소백산","비슬산","덕유산"};
        	List<String> collection = Arrays.asList(mountains);
        %>
        <c:set var="mountains" value="<%=mountains %>"/>
        <c:set var="collection" value="<%=collection %>"/>
        \${mountains } : ${mountains }<br/><!-- [Ljava.lang.String;@2924b8c5 배열이니까 주소출력 -->
        \${collection } : ${collection }<!-- [설악산, 소백산, 비슬산, 덕유산] 컬렉션은 오버라이딩되어 잘 나옴 -->
        <ul class="list-unstyled">
        	<li>\${mountains[0] }[정상] : ${mountains[0] }</li><!-- 설악산 -->
        	<li>\${collection[0] }[정상] : ${collection[0] }</li><!-- 설악산 -->
        	<!-- 반드시 배열형태로 접근(인덱스로),get 후에 0이니까 get빼고 0만? 당연히 안됨  -->
        	<!-- 아래 두줄은 Failed to parse the expression -->
        	<li>\${mountains.0 }[정상] : \${mountains.0 }</li>
        	<li>\${collection.0 }[정상] : \${collection.0 }</li>
        </ul>
        <h4>배열이나 리스트계열 컬렉션의 인덱스를 벗어난 경우(에러NO,출력NO)</h4>
        <ul class="list-unstyled">
        	<li>\${mountains[100] } : ${mountains[100] }</li>
        	<li>\${mountains[100]==null } : ${mountains[100]==null }</li><!-- t -->
        	<li>\${collection[100] } : ${collection[100] }</li>
        	<li>\${collection[100]==null } : ${collection[100]==null }</li><!-- t -->
        </ul>
        <h4>없는 속성이거나 속성은 존재하지만 게터가 없는 경우(에러-자바빈의 경우)</h4>
        <% pageContext.setAttribute("member", new MemberDTO("KIM","1234","김길동","20")); %>
        <ul class="list-unstyled">
        	<li>없는 속성 : \${member.addr }</li><!-- addr은 member에 없는 속성이라 에러, PropertyNotFoundException -->
        	<li>속성은 존재,게터가 없는 경우 : ${member.name }</li><!-- model/MemberDTO 들어가서 getName을 주석처리하고옴, 에러남, PropertyNotFoundException -->
        	<li>EL변수명이 틀린경우 : ${membar.id }</li><!-- membar변수명 없음, 에러는 아니지만 출력이 안됨 -->
        	<li>\${membar==null } : ${membar==null }</li><!-- null이라 출력안된것, 위에 null.id였던 것  -->
        </ul>
        <h4>EL내장객체의 없는 속성으로 접근시</h4>
        <!--
	  		EL의 내장객체중 사용자가 정의한 속성에 접근하는
			내장객체(xxxScope계열(영역에 있는 값 읽어올 때 씀)/param계열(파라미터 설정) 등) 및 맵 컬렉션인 경우,
			존재하지 않은 속성이나 키값으로 접근시 null임으로 에러는 안나고 출력만 안됨
			
			표현언어의 내장 개체]

			pageScope: pageContext 기본 개체에 저장된 속성의 <속성,값> 매핑을 저장한 Map Collection
			requestScope:request 기본 개체에 저장된 속성의 <속성,값> 매핑을 저장한 Map Collection
			sessionScope:session 기본 개체에 저장된 속성의 <속성,값> 매핑을 저장한 Map Collection
			applicationScope:application 기본 개체에 저장된 속성의 <속성,값> 매핑을 저장한 Map Collection
			
			값을 얻어올때: XXXXScope.속성명
			
			param:요청 파라미터의 <파라미터명,값> 매핑을 저장한 
			         Map Collection,파라미터값의 타입은 String
			      request.getParameter(파라미터명)와 동일

			 값을 얻어올때:param.파라미터명

			paramValues:요청 파라미터의 <파라미터명,값배열> 매핑을 저장한 Map Collection
						파라미터값의 타입은 String[], request.getParametervalues(파라미터명)와 동일
			
			값을 얻어올때: paramValues.파라미터명

			header:요청정보의 <헤더이름,값> 매필을 저장한 Map Collection
			값을 얻어올때: header.헤더명 단, user-agent의 경우
			                    헤더명에 - (대쉬) 가 들어가기때문에
					    header["user-agent"]로 얻어 옫?
			
			headerValues:요청정보의 <헤더이름,값배열> 매필을 저장한 Map Collection
			             request.getHeaders(이름)결과와 동일

			cookie:<쿠키명,값> 매핑을 저장한 Map Collection.
			
			값 얻어올때: cookie.쿠키명.value

		
			initParam:초기화 파라미터의 <이름,값>매핑르 저장한 Map Collection .
			application.getInitParameter(이름)결과와 동일

			값 얻어올때: initParam.파라미터명(web.xml에 설정한)
			
			
			pageContext:JSP의 pageContext기본 개체와 동일(JSP는 set으로 설정가능, EL은 읽어오기만 가능)

					  jsp에서 컨텍스트 루트를 얻어 올때 <%=request.getContextPath()%> 이용
					  el에서는 ${pageContext.request.contextPath}
					  즉 request객체 얻어서(getReuest()) getContextPath() 메서드 호출한 것과 같다
			
  	 	-->
        <ul class="list-unstyled">
        	<c:set var="myrequest" value="리퀘스트 영역" scope="request"/>
        	<%
        		Map<String, String> map = new HashMap<>();
        		map.put("mymap","맵 컬렉션");
        		pageContext.setAttribute("map", map);//map이라는 이름으로 map저장
        	%>
        	<!-- 아래는 getter가 아니라 저장한걸 읽어오는 것 -->
        	<li>\${requestScope.myrequest } : ${requestScope.myrequest }</li><!-- 속성명은 내가 위해 정한 var -->
     		<li>\${requestScope.yourrequest } : ${requestScope.yourrequest }</li><!-- 이런거 없으니 안나옴, null -->
        	<li>\${map.mymap } : ${map.mymap }</li><!-- page영역의 pageScope는 생략, 위에 pageContext에 저장한 map 꺼내옴 -->
        	<li>\${map.yourmap } : ${map.yourmap }</li><!-- 없으니 안나옴, null  -->
        	<li>\${pageContext.noproperty } : \${pageContext.noproperty }</li>
        	<!-- 내장객체라도 게터성격의 없는 속성으로 접근시는 에러, PropertyNotFoundException, 이건 속성명을 가져오는게 아니라 getter 느낌  -->
        </ul>
 		<h4>숫자를 0으로 나누면 Infinity</h4>       
 		\${100/0 } : ${100/0 }<!-- Infinity 출력 -->
 		<h4>임의의 변수(선언한적 없는 변수)로 .을 통해서 접근시 - 에러NO, 출력NO(null임)</h4>
 		\${myvar } : ${myvar }<!-- null이라 안나옴 --><br/>
         \${myvar.age==null } : ${myvar.age==null }<!-- t, null이라 안나옴 -->
        <%--
        <!-- 
	        Math.ceil 같은건 되는데 내가만든 클래스의 메소드는 바로 ${}로 불러오지 못함, 방법이 있음 
	        이전에는 jar로 class 라이브러리를 씀, 이젠 tld 라이브러리를 씀
        -->
         --%>
        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>