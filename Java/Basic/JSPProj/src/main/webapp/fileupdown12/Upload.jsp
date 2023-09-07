<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Upload.jsp -->
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>파일 업로드/다운로드</h1>            
        </div><!--jumbotron-->
        
        <div class="d-flex justify-content-end">
			<a href="UploadList.jsp" class="btn btn-success">파일목록</a>
		</div>
		<% if(request.getAttribute("ERROR") !=null) {%>
		<div class="alert alert-danger alert-dismissible">
	      <button class="close" data-dismiss="alert">&times;</button>
	      <strong>${ERROR }</strong><!-- request영역에서 읽어오는 것 -->
	    </div>
		<%} %>
		<form action="${pageContext.request.contextPath }/fileupload/Upload.kosmo" method="post" enctype="multipart/form-data">
		<!-- /JSPProj <-소스보기하면 action 안에 들어가있음, html에서는 반드시 컨텍스트 루트 넣어야함, .jsp말고 서블릿으로 보냄 -->
		<!-- application/x-www-form-urlencoded 이게 form으로 보내는 enctype 기본값(근데 내용전달x,파일명만 전달) 이것도 암기********* -->
		<!-- 파일 업로드 반드시 post방식(용량제한 없는 요청바디에 포함되기때문), get은 용량제한있음(용량 정해진 요청헤더에 포함되기때문)  -->
			<div class="form-group">
				<label><kbd class="lead">올린이</kbd></label>
				<input type="text" class="form-control" name="name" placeholder="올린이를 입력하세요">
			</div>
			<div class="form-group">
				<label><kbd class="lead">제목</kbd></label> <input type="text"
					class="form-control" name="title" placeholder="제목을 입력하세요">
			</div>
			<div class="form-group">
				<label><kbd class="lead">관심사항</kbd></label>
				<div class="d-flex">
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input" name="inter"
							value="정치" id="POL"> <label class="custom-control-label"
							for="POL">정치</label>
					</div>
					<div class="custom-control custom-checkbox mx-2">
						<input type="checkbox" class="custom-control-input" name="inter"
							value="경제" id="ECO"> <label class="custom-control-label"
							for="ECO">경제</label>
					</div>
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input" name="inter"
							value="연예" id="ENT"> <label class="custom-control-label"
							for="ENT">연예</label>
					</div>
					<div class="custom-control custom-checkbox ml-2">
						<input type="checkbox" class="custom-control-input" name="inter"
							value="스포츠" id="SPO"> <label class="custom-control-label"
							for="SPO">스포츠</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label><kbd class="lead">첨부파일</kbd></label>
				<input type="file" multiple name="attachfile" class="form-control-file border">
			</div>
			
			<button type="submit" class="btn btn-primary">파일 업로드</button>
		</form>    
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>