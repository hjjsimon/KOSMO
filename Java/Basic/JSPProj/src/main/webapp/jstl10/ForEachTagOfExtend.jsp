<%@page import="oracle.net.aso.s"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model.MemberDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/mytaglib.tld" %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>JSTL(JSP Standard Tag Library)의 Core Tag</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">forEach태그(확장 for문 형태)</legend>
        	<%
				//1]배열 세팅
        		String[] colors = {"red", "green", "blue", "#9944A5"};
        		//2]List컬렉션 세팅
        		List<MemberDTO> list = Arrays.asList(
        				new MemberDTO("KIM","1234","김길동","20"),
        				new MemberDTO("LEE","1234","이길동","25"),
        				new MemberDTO("PARK","1234","박길동","30")
        				);
        		//3]Map컬렉션 세팅
        		Map<String, MemberDTO> map = new HashMap<>();
        		map.put("first", list.get(0));//new MemberDTO("KIM","1234","김길동","20") 넣은것
        		map.put("second", list.get(1));
        		map.put("third", list.get(2));
        	%>
        	<!-- 아래 내용은 페이지영역(기본값)에 저장한 것 -->
        	<c:set var="colors" value="<%=colors %>" />
        	<c:set var="list" value="<%=list %>" />
        	<c:set var="map" value="<%=map %>" />
        	<h3>배열 출력</h3>
        	<!--
				 필수 속성 : items 및 var (얘네 둘은 안쓰면 에러)
				 -배열이나 컬렉션에서 값을 꺼내올때 사용	
				 -varStatus에 지정한 LoopStatusTag객체의 index속성은 항상 0부터 시작	
		 	-->
		 	<!-- colors에서 하나씩 꺼내서 var color에 담음 -->
		 	<!-- 아래 loop는 인덱스 0부터 시작 for문 기본 -->
        	<c:forEach var="color" items="${colors }">
        		<h4 style="color:${color }">Java Server Page</h4>
        	</c:forEach>
        	<h3>varStatus속성</h3>
        	<c:forEach var="color" items="${colors }" varStatus="loop">
        		<h5>${loop.count }번째 반복</h5>
        		<ul class="list-unstyled">
        			<li>\${loop.index } : ${loop.index }</li>
        			<li>\${loop.first } : ${loop.first }</li>
        			<li>\${loop.last } : ${loop.last }</li>
        			<li>\${loop.current } : ${loop.current }</li>
        		</ul>
        	</c:forEach>
        	<h3>리스트 계열 컬렉션 출력</h3>
        	<h4>일반 for문 형태의 forEach태그로 출력</h4>
        	<!-- 인덱스 0부터 시작, 비긴도 0부터 써줌 -->
        	<c:forEach var="i" begin="0" end="${list.size()-1 }">
        		아이디:${list[i].id }, 비번:${list[i].pwd }, 이름:${list[i].name }<br/>
        	</c:forEach>
        	<h4>확장 for문 형태의 forEach태그로 출력</h4><!-- 꺼내온 item에서 속성찾아씀 -->
        	<c:forEach var="item" items="${list }">
        		아이디:${item.id }, 비번:${item.pwd }, 이름:${item.name }<br/>
        	</c:forEach>
        	<h3>맵 계열 컬렉션 출력</h3>
        	<h4>키값을 알 때</h4>
        	<!-- 원래 get(key) 인데 바로 .키값 찍음 -->
        	<ul class="list-unstyled">
        		<li>아이디:${map.first.id }, 비번:${map['first']['pwd'] }, 이름:${map.first.name }<br/></li>
        	    <li>아이디:${map.second.id }, 비번:${map['second']['pwd'] }, 이름:${map.second.name }<br/></li>
        	    <li>아이디:${map.third.id }, 비번:${map['third']['pwd'] }, 이름:${map.third.name }<br/></li>
        	</ul>
        	<h4>키값을 모를 때</h4>
        	<!--
			      KEY값 얻기 : var속성에 지정한 \${el변수.key}
			      VALUE값 얻기: var속성에 지정한 \${el변수.value}
		    -->
		    <!-- 아래는 키값 모르니까 li 태그 하나만 두고 꺼내면서 반복, value꺼낸건 MemberDTO타입 -->
        	<ul class="list-unstyled">
        		<c:forEach var="item" items="${map }">
        			<li>키:${item.key}, 아이디:${item.value.id }, 비번:${item.value.pwd }, 이름:${item.value.name }<br/></li>
        		</c:forEach>
        	</ul>
        	<!-- 
        		회원제 게시판(bbs테이블)에 저장된 글을 읽어와서 목록으로 출력하라(위에서 10개만: 행시작번호,끝번호 -> start=1,end=10)
        		태그 라이브러리에 목록을 반환하는 메소드를 정의하여 그 메소드를 EL에서 호출하도록 하여라(EL,JSTL만 사용)	
        	-->
        	<table class="table table-dark table-hover text-center">
				<thead>
					<tr>
						<th class="col-1">번호</th>
						<th>제목</th>
						<th class="col-2">작성자</th>
						<th class="col-1">조회수</th>
						<th class="col-2">작성일</th>
					</tr>
				</thead>
				<tbody class="table-sm">
					<c:forEach var="item" items="${my:selectList(pageContext.servletContext, 1, 10)}">
						<tr>
							<td>${item.no }</td>
							<td>${item.title }</td>
							<td>${item.name }</td>
							<td>${item.hitCount }</td>
							<td>${item.postDate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>