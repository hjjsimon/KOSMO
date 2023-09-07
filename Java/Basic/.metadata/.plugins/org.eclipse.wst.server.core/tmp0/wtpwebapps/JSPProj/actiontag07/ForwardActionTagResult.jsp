<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ForwardActionTagResult.jsp -->    
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>&lt;jsp:forward&gt;액션 태그</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">각 영역에 저장된 속성 출력</legend>
        	<ul class="list-unstyled">
        		<li>PAGE SCOPE : <%=pageContext.getAttribute("pageVar") %></li>
        		<li>REQUEST SCOPE : <%=request.getAttribute("requestVar") %></li>
        	</ul>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>
    