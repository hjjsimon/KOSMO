<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ParamActionTag_1.jsp -->
<%
	/*
	톰캣 9이하에서 인클루드로 페이지 포함시 파라미터로 한글 전달시 한글이 깨진다
	이때는 반드시 인클루드 시키는 페이지에서 아래 코드 추가
	*/
	request.setCharacterEncoding("UTF-8");//현재 톰캣10이라 안해도됨
	/*
	톰캣 업데이트 전에는
	include의 page속성에는 반드시 페이지명만 적어야한다	
	include액션태그는 반드시 하위 요소인 
	param액션 태그를 통해서만 파라미터 전달 가능
	(단,2022-11-24현재는 쿼리스트링으로 전달 가능)
	
	param액션태그의  value속성은 표현식 가능
			       name속성은 표현식 사용 불가.즉 문자열만 와야한다
	*/

request.setAttribute("requestScope", "리퀘스트 영역입니다");


%>
<!-- ParamActionTag_1.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>&lt;jsp:param&gt; 액션태그 첫번째</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">include액션태그안에 param액션태그 사용</legend>
        	<%-- 
        	<jsp:include page="IncludedPage.jsp?id=kim&pwd=1234&name=김길동"/>
        	--%>
        	<!-- 빨간줄 이클립스오류 이전에는 ?이하를 주소로 못이해함 -->
        	<!--
			톰캣 업데이트 전에는
			include의 page속성에는 반드시 페이지명만 적어야한다	
			include액션태그는 반드시 하위 요소인
			param액션 태그를 통해서만 파라미터 전달 가능
			(단,2022-11-24현재는 쿼리스트링으로 전달 가능)
			
			param액션태그의  value속성은 표현식 가능
					      name속성은 표현식 사용 불가.즉 문자열만 와야한다
			-->
			<!-- 톰캣 9이하부터는 무조건 param액션태그로 파라미털르 전달해야했다 -->
        	<jsp:include page="IncludedPage.jsp">
        		<jsp:param value="park" name="id"/>
        		<jsp:param value="1234" name="pwd"/>
        		<jsp:param value="박길동" name="name"/>
        	</jsp:include>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>