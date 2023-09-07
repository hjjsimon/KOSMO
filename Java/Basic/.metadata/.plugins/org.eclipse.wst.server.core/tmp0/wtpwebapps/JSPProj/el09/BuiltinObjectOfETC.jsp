<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- BuiltinObjectOfETC.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- 보통 c줌 -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>EL의 기타 내장객체</h1>            
        </div><!--jumbotron-->
        <!--
			※EL에서는 값을 설정하거나 영역에 속성을
			  저장(설정)하지는 못한다.(set계열 없음)
			  단지 저장된 값을 출력만 할 수 있다.(get계열만 있음)
			  (원래 EL은 출력이 목적)
	 	-->
        <h1 class="display-2">1. EL의 pageContext내장객체</h1><!-- JSP에서는 잘 안썼음, EL에서 잘 씀 -->
        <!-- 
        	JSP의 pageContext내장객체와 같다
        	단, 자바빈 규칙으로 접근 가능한 메소드만 제공한다 
        -->
        <%-- 
        	pageContext.getResponse() 
        	-> pageContext에 response가 있는것, 그러니까 get으로 불러오지(자바빈 좁은의미 설명추가하기)
        	-> EL도 똑같이 getXXX로 불러오는건 그 필드가 존재하는 것
        --%>
		<h1 class="display-4">자바코드로 컨텍스트 루트(/JSPProj) 얻기</h1>
		<!-- getContextPath()는 request 꺼라서 pageContext로 얻어오려면 request변수를 getRequest로 부른 후 request로 형변환이 필요 -->
		방법1(request내장객체) : <%=request.getContextPath() %><br/>
		방법2(pageContext내장객체) : <%=((HttpServletRequest)pageContext.getRequest()).getContextPath() %>
		<h1 class="display-4">EL로 컨텍스트 루트(/JSPProj) 얻기</h1>
		\${pageContext.request.contextPath } : ${pageContext.request.contextPath }
		<!-- get이 빠짐, /JSPProj 출력, 형변환이 필요 없어서 좋음, contextPath 안뜸 그냥 하드코딩하면 출력 -->
		<h1 class="display=4">자바코드로 세션의 유효시간 얻기</h1>
		<!-- MaxInactiveInterval은 session꺼라 형변환 필요 -->
		<%=session.getMaxInactiveInterval() %>초<br/><!-- 86400초 -->
		<%=request.getSession().getMaxInactiveInterval() %>초<br/>
		<%=pageContext.getSession().getMaxInactiveInterval() %>초<br/>
		<%=((HttpServletRequest)pageContext.getRequest()).getSession().getMaxInactiveInterval() %>초<br/>
		<h1 class="display-4">EL로 세션의 유효시간 얻기</h1>
		\${pageContext.session.maxInactiveInterval } : ${pageContext.session.maxInactiveInterval }초<!-- EL의 내장객체 -->
		\${pageContext.request.session.maxInactiveInterval} : ${pageContext.request.session.maxInactiveInterval}초
		
		<h1 class="display-2"> 2. EL의 header내장객체</h1>
		<h1 class="display-4">자바코드로 요청헤더값 얻기</h1>
		<!-- 요청헤더 request에 있음 -->
		<%=request.getHeader("user-agent")%>
		<h1 class="display-4">EL로 요청헤더값 얻기</h1>
		<!-- 요청헤더 request에 있음, request로 바꾸기 게임 -->
		\${header.user-agent } : ${header.user-agent }<br/><!-- 이름 중간에 - 들어가서 문제, 0출력 -->
		\${header['user-agent'] } : ${header['user-agent'] }<!-- 키값에 특수문자 포함시 대괄호를 써야한다 -->
		<!-- 
			pageContext.request의 header속성이 없다
			즉 요청헤더 구할 때는 무조건 header내장객체 사용
		 -->
		<!-- \${pageContext.request.header.user-agent } 에러발생 JSP에서 getHeader임, 이런건 다 위에 했던 것처럼 get빼고 소문자시작 --><br/>
		\${pageContext.request.header}<br/> 얘도 안됨
		
		<h1 class="display-2">3. EL의 cookie내장객체</h1>
		<%
			//자바코드로 쿠키 설정
			Cookie cookie = new Cookie("KOSMO","한소인");
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		%>
		<h1 class="display-4">자바코드로 쿠키 얻기</h1>
		<%
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(Cookie cook : cookies){
					String name = cook.getName();
					String value = cook.getValue();
					out.println(name + " : "+value+"<br/>");
				}
			}
		%>
		<h1 class="display-4">EL로 쿠키 얻기</h1>
		\${pageContext.request.cookies } : ${pageContext.request.cookies }<br/><!-- 이것도 get빠지고 cookies남음, 배열이라 주소 출력 -->
		<!-- cookie내장객체 미사용 -->
		<c:forEach var="item" items="${pageContext.request.cookies }">
		<!-- items에서 꺼내 item에 담음 -->
			\${item.name } : ${item.name }<br/><!-- getName 대신 name -->
			\${item.value } : ${item.value }<br/>
		</c:forEach>
		<!--
		 	cookie.쿠키명.value로 쿠키명에 해당하는 쿠키값을 얻을 수 있다.		
	    -->
		<!-- cookie내장객체 사용 -->
		\${cookie.KOSMO.value } : ${cookie.KOSMO.value }<br/>
		\${cookie['User-Token'].value } : ${cookie['User-Token'].value } 
		<h1 class="display-2">4. EL의 initParam내장객체</h1>
		<!--
	     	컨텍스트 초기화 파라미터를 얻어 올수 있는 내장 객체
		   
		   [Context초기화 파라미터]
		   -Context초기화 파라미터는 ServletContext의
		    getInitParameter("파라미터명")메소드를 통해서
		    얻는다.
		   -Context를 구성하는 모든 서블릿에서 공유할 수 있는 변수
		   [Servlet초기화 파라미터]
		   -Servlet초기화 파라미터는 ServletConfig의
		    getInitParameter("파라미터명")메소드를 통해서 얻는다
		   -해당 서블릿에서만 사용할 수 있는 변수		  
		      ※초기화 파라미터는 web.xml에 등록
   		-->
		<h1 class="display-4">자바코드로 컨텍스트 초기화 파라미터 얻기</h1>
		<ul class="list-styled">
			<li>ORACLE-URL : <%=application.getInitParameter("ORACLE-URL") %></li>
			<li>ORACLE-DRIVER : <%=application.getInitParameter("ORACLE-DRIVER") %></li>
		</ul>
		<h1 class="display-4">EL로 컨텍스트 초기화 파라미터 얻기</h1>
		<ul class="list-styled">
			<li>ORACLE-URL : ${initParam['ORACLE-URL'] }</li><!-- 대쉬(-)들어가서 대괄호 필요 -->
			<li>ORACLE-DRIVER : ${initParam['ORACLE-DRIVER'] }</li>
		</ul>
		
		<h4 class="display-4">컬렉션에 저장된 객체 EL로 출력</h4>
		<%
			//데이터 준비(원래 서블릿에 작성, 어쩔 수 없이 자바코드로)
			MemberDTO first = new MemberDTO("KIM", "1234", "김길동", "20");
			MemberDTO second = new MemberDTO("LEE", "1234", "이길동", "30");
			
			//리스트 계열 컬렉션에 객체 저장
			List<MemberDTO> list = Arrays.asList(first, second);
			//맵 계열 컬렉션에 객체 저장
			Map<String, MemberDTO> map = new HashMap<>();
			map.put("first",first);
			map.put("second",second);
			//page영역에 저장
			//아래 list와 map은 EL에서 못씀, 그래서 영역에 저장해야함
			pageContext.setAttribute("list", list);//list이름으로 list값 저장
			pageContext.setAttribute("map", map);
			
		%>
		<h4>리스트 계열 컬렉션</h4>
		<h6>JSTL 미사용</h6>
		\${list } : ${list }<br/><!-- page영역에서 가져온것, 앞에 pageScope있는데 생략된것 -->
		<!-- [이름:김길동,아이디:KIM,비번:1234,나이:20, 이름:이길동,아이디:LEE,비번:1234,나이:30] -->
		\${list[0] } : ${list[0] }<br/><!-- List계열 컬렉션은 내부적으로 배열이라, 인덱스로 접근 -->		
		<!-- 지금 잘 나오는건 toString오버라이딩 해놨기 때문, .toString안하고 이름만으로도 잘 나옴 -->
		<ul class="list-styled">
			<li>이름:${list[0].name },아이디:${list[0].id },비밀번호:${list[0].pwd }</li>
			<!-- MemberDTO에 게터,세터뿐, getName이니까 name찍으면 됨(name필드 당연히 애초에 존재하니까!) -->
			<li>이름:${list[1].name },아이디:${list[1].id },비밀번호:${list[1].pwd }</li>
		</ul>
		\${list[100] } : ${list[100] }<!-- 출력만 안된다, 애초에 없음, null -->
		
		<h6>JSTL사용 : 저장된 객체 수를 모를 때</h6>
		<ul class="list-styled">
			<c:forEach var="item" items="${list}"><!-- 왼쪽 items의 {} 안에는 공백있으면 안됨 -->
				<li>이름:${item.name },아이디:${item.id },비밀번호:${item.pwd }</li>
			</c:forEach>
		</ul>
		<h4>맵 계열 컬렉션</h4>
		<h6>JSTL 미사용</h6>
		\${map } : ${map }<br/>
		<!-- {first=이름:김길동,아이디:KIM,비번:1234,나이:20, second=이름:이길동,아이디:LEE,비번:1234,나이:30} -->
		\${map.first } : ${map.first }<br/>
		<ul class="list-styled">
			<li>이름:${map.first.name },아이디:${map.first['id']},비밀번호:${map['first']['pwd'] }</li>
			<li>이름:${map.second.name },아이디:${map.second['id']},비밀번호:${map['second']['pwd'] }</li>
		</ul>
		<h6>JSTL 사용 : 키를 모를때</h6>
		<!--
	 		JSTL의 forEach사용시
	 		var에 지정한
	 		변수명.value는 밸류값
	 		변수명.key는 키값
		-->
		<ul class="list-styled"><!-- 자바는 확장for문 안됐는데 이건 됨 -->		
			<c:forEach var="item" items="${map}">
				<li>키:${item.key }, 이름:${item.value.name }, 아이디:${item.value.id }, 비밀번호:${item.value.pwd }</li>
			</c:forEach>
		</ul>
		<!-- 
			\${}에서 자바객체의 메소드 호출가능
			단, 객체의 값을 변화시키는 메소드는 호출시 에러날 수 있음
			호출가능한 메소드는 주로 getXXX류의 메소드
		-->
		<h1 class="display-4">컬렉션에 읽기용 성격의 메소드 호출</h1>
		\${list.isEmpty() } : ${list.isEmpty() }<br/> <!-- 비어있지 않으니 false -->
		\${list.size() } : ${list.size() }<br/>
		\${map.isEmpty() } : ${map.isEmpty() }<br/>
		\${map.size() } : ${map.size() }<br/>
		\${list.clear() } : \${list.clear() }<br/> <!-- 이런 지우는 등 설정변경 메소드는 에러 발생 -->
		\${map.clear() } : ${map.clear() }<br/> <!-- 얘는 또 됨, 골떄림 -->
		\${map.isEmpty() } : ${map.isEmpty() }<br/> <!-- t나옴 -->
		
		
		
		
		
		
		
		
		
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>