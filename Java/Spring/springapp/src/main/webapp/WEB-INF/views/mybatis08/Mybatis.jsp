<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>MyBatis Framework</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">마이바티스 프레임워크</legend>
       	
        	<h1 class="display-4">마이바티스 사용방법</h1>
        	<span class="text-danger font-weight-bolder">${time }</span>
			<ul class="list-unstyled">
				<li><a href="<c:url value="/Mybatis/annotation"/>">어노테이션방식</a></li>
				<li><a href="<c:url value="/Mybatis/xml"/>">XML방식</a></li>
			</ul>
			<hr/>
			<h1 class="display-4">동적 SQL(XML방식)</h1>
			<span class="text-danger font-weight-bolder">${message }</span>
			<h3>if 태그</h3>
			<ul class="list-unstyled">
				<li><a href="<c:url value="/Mybatis/If1.do?title=자바"/>">WHERE절 일부에 사용 첫번째</a></li>
				<li><a href="<c:url value="/Mybatis/If2.do?title=자바&name=김길동&content=안드로이드"/>">WHERE절 일부에 사용 두번째</a></li>
			</ul>
			<h3>choose~when~otherwise 태그</h3>
			<a href="<c:url value="/Mybatis/choose.do?title=자바&name=김길동&content=안드로이드"/>">WHERE절 일부에 사용</a>
			<h3>where 태그</h3>
			<a href="<c:url value="/Mybatis/where.do?title=자바&name=김길동&content=안드로이드"/>">where태그로 where절 구성</a>
			<h3>trim 태그</h3>
			<ul class="list-unstyled">
				<li><a href="<c:url value="/Mybatis/trim1.do?title=자바&name=김길동&content=안드로이드"/>">검색문</a></li>
				<li><a href="<c:url value="/Mybatis/trim2.do?no=1&title=자바&content=안드로이드"/>">수정문</a></li>
			</ul>
			<!-- update 하고자 하는 칼럼을 동적으로 포함시키기 위해 사용 -->
			<h3>set 태그</h3>
			<a href="<c:url value="/Mybatis/set.do?no=1&title=안녕&content=인사"/>">수정문</a>
			<h3>foreach 태그</h3>
			<a href="<c:url value="/Mybatis/foreach.do"/>">foreach태그</a>
			<h3>이메일 삭제</h3>
			<form method="post" action="<c:url value="/Mybatis/foreachExam.do"/>">
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" value="1" name="email">메일 1
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" value="2" name="email">메일 2
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" value="3" name="email">메일 3
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" value="4" name="email">메일 4
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" value="14" name="email">메일 14
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" value="15" name="email">메일 15
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" value="16" name="email">메일 16
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" value="17" name="email">메일 17
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
					<input type="checkbox"	class="form-check-input" value="18" name="email">메일 18
					</label>
				</div>
				<input type="submit" value="삭제" class="btn btn-danger"/>
			</form>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>