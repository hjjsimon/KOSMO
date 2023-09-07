<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/template/Top.jsp"/><!-- Top에 taglib넣어도 안됨, 인클루드는 바뀐 후 들어오는거라 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>자료실 작성 <small>자료 파일을 수정하세요</small></h1>            
        </div><!--jumbotron-->
        <c:if test="${! empty ERROR }">
		<%-- if(request.getAttribute("ERROR") !=null) {--%><!-- 이거 대신 JSTL씀, 모델2니까! -->
			<div class="alert alert-danger alert-dismissible">
				<button class="close" data-dismiss="alert">&times;</button>
				<strong>${ERROR}</strong><!-- request영역에서 읽어오는 것 -->
			</div>
		</c:if>
        <form method="post" action="<c:url value="/DataRoom/Edit.kosmo"/>" enctype="multipart/form-data">
        	<input type="hidden" name="no" value="${record.no }"/><!-- 글번호, 오리지날 파일명 넘겨줌 -->
        	<input type="hidden" name="nowPage" value="${param.nowPage }"/>
        	<input type="hidden" name="originalFileName" value="${record.attachFile }"/>
			<div class="form-group">
				<label><kbd class="lead">올린이</kbd></label> <input type="text"
					class="form-control" placeholder="이름을 입력하세요" name="name" value="${record.name}">
				<!-- 처음 요청시 param 비어있음, 그러면 빈문자열 나오게함 -->
			</div>
			<div class="form-group">
				<label><kbd class="lead">제목</kbd></label> <input type="text"
					class="form-control" placeholder="제목을 입력하세요" name="title" value="${record.title}">
			</div>
			<div class="form-group">
				<label><kbd class="lead">자료 파일</kbd> </kbd></label> <input type="file"
				multiple name="attachFile" class="form-control-file border">${record.attachFile }
				<!-- JSP로 포워드할 때, <input type="file">로 선택한 파일은 기본적으로 포워딩되지 않는다 -->
			</div>
			<div class="form-group">
				<label><kbd class="lead">비밀번호</kbd></label> <input type="password"
					class="form-control" placeholder="비밀번호를 입력하세요" name="password" value="${record.password}">
			</div>
			
			<div class="form-group">
				<label><kbd class="lead">내용</kbd></label>
				<textarea class="form-control" rows="5" name="content">${record.content}</textarea>
			</div>
			<button type="submit" class="btn btn-primary">수정</button>
		</form>     
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>
<script>
	/*   
		서버에서는 파일용량초과시 에러, 클라이언트측에서도 해보기
		[0]은 첫번째 파일 의미
		파일객체은 input type="file" 요소를 의미
		파일 사이즈(바이트):파일객체(자스 DOM).files[0].size
		파일 명:파일객체(자스 DOM).files[0].name
		파일 컨텐츠 타입:파일객체(자스 DOM).files[0].type
	*/
		var attaches = document.querySelector("input[type=file][name=attachFile]");//인풋태그 중 타입속성 파일, 이름은 attachFile인 애
		var form = document.querySelector("form[enctype='multipart/form-data']");// 대시/ 같은거 있으면 ''또는 ""로 감싸주는게 좋음, 밖에 ""니까 ''함
		var totalUploadSize=0;
		form.onsubmit=function(){
			console.log(attaches.files);//FileList타입(요소는 File타입)
			var fileList = attaches.files;//유사배열
			for(var i=0; i < fileList.length;i++){
				console.log('파일명:%s,크기:%s,컨텐츠타입:%s',
						fileList[i].name,fileList[i].size,fileList[i].type);
				totalUploadSize+=fileList[i].size;
				if(fileList[i].size > 1024 *500){
					alert("최대 파일 업로드 용량(500KB)을 초과 했어요");
					return false;//이벤트 취소
				}
				
			}///for
			if(totalUploadSize > 1024 *500*5){
				alert("총 파일 업로드 최대 용량(2.5MB)을 초과 했어요");
				return false;//이벤트 취소
			}
			
		};
</script>
    