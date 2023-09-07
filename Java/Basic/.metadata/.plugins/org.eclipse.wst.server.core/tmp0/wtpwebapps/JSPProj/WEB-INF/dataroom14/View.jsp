<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/template/Top.jsp" />

<div class="container" style="margin-top: 50px">
	<div class="jumbotron bg-info">
		<h1>자료실 상세 <small>상세 보기 페이지입니다</small></h1>
	</div>
	<!--jumbotron-->
	<table class="table table-bordered">
		<tbody class="table-sm">
			<tr>
				<th class="w-25 bg-dark text-white text-center">번호</th>
				<td>${record.no }</td><!-- ViewController에서 record변수명에 담음 -->
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">올린이</th>
				<td>${record.name }</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">등록일</th>
				<td>${record.postDate }</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">다운수</th>
				<td id="down-count">${record.downCount }</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">자료 파일</th>
				<td>
					<ul class="list-unstyled">
						<c:forEach var="file" items="${fn:split(record.attachFile, ',') }"><!-- ,로 파일구분해서 배열자르고 file에 담음 -->
							<li><a class="down-file" href="<c:url value="/DataRoom/Download.kosmo?filename=${file }&no=${record.no }"/>" >${file }</a></li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">제목</th>
				<td>${record.title }</td>
			</tr>
			<tr>
				<th class="bg-dark text-white text-center" colspan="2">내 용</th>
			</tr>
			<tr>
				<td colspan="2">${record.content }</td>
			</tr>
		</tbody>
	</table>
	<!-- 수정/삭제/목록 컨트롤 버튼 -->
	<div class="text-center">
		<a href="" class="btn btn-success password-update-delete" data-toggle="modal" data-target="#passwordModal" data-backdrop="static">수정</a> 
		<a href="" class="btn btn-success password-update-delete" data-target="#passwordModal" data-backdrop="static">삭제</a> 
		<!-- data-toggle="modal" 써야 모달창 띄워짐, backdrop하면 x버튼 말고 다른거 눌러도 안꺼짐 -->
		<a href="<c:url value="/DataRoom/List.kosmo?nowPage=${param.nowPage }"/>" class="btn btn-success">목록</a>
	</div>
</div>

<!--container-->
<jsp:include page="/template/Footer.jsp" />
<!-- 수정/삭제시 사용할 모달 시작 -->
<div class="modal fade" id="passwordModal">	
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">비밀번호 입력창</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<!-- Modal body -->
			<div class="modal-body d-flex justify-content-center">
				<form class="form-inline" action="<c:url value="/DataRoom/Password.kosmo"/>" method="POST">					
					<!-- 키값 -->
					<input type="hidden" name="no" value="${record.no}"/>
					<!-- 수정/삭제 판단용 -->
					<input type="hidden" name="mode" />
					<!-- 현재 페이지 번호 -->
					<input type="hidden" name="nowPage" value="${param.nowPage}"/>
					<!-- 업로드된 파일명:삭제메뉴 클릭시 테이블 데이타 삭제후 업로드된 기존 파일 삭제하기 위함 -->
					<input type="hidden" name="orignalFileName" value="${record.attachFile }"/>
					<label>비밀번호</label>
					<input type="password" name="password" class="form-control mx-2" placeholder="비밀번호를 입력하세요"/>
					<input type="submit" class="btn btn-danger mx-2" value="확인" />
				</form>			
			</div>
		</div>
	</div>
</div>
<!-- 수정/삭제시 사용할 모달 끝 -->
<script>
	//수정/삭제버튼 클릭시 이벤트 처리
	var mode = document.querySelector('input[name=mode]');
	var buttons = document.querySelectorAll(".password-update-delete");
	
	buttons.forEach(function(button){//buttons에서 하나씩 꺼내서 button에 담음
		button.onclick = function(e){
			console.log(e.target.textContent);
			if(e.target.textContent === "수정"){
				mode.value = "UPDATE";
				document.querySelector(".modal-title").textContent = "수정용 비밀번호 입력창";
			}
			else{
				if(confirm("정말로 삭제하시겠습니까?")){
					mode.value = "DELETE";	
					//data-toggle="modal" 속성 추가 그래야 모달창이 뜬다
					e.target.setAttribute("data-toggle","modal");//삭제하겠다고 하면 modal 속성을 추가해서 띄워줌
					document.querySelector(".modal-title").textContent = "삭제용 비밀번호 입력창";
					//바로 속성 지우면 모달창의 안뜨니까 띄운후 2초 지나면 속성 제거
					window.setTimeout(()=>e.target.removeAttribute("data-toggle","modal"),2000);
				};
			}
		};
	});
	//다운로드 수 증가
	var td = document.querySelector("#down-count");
	var downFiles = document.querySelectorAll(".down-file");
	
	downFiles.forEach(function(file){
		
		file.onclick=function(e){
			var downCount = parseInt(td.innerHTML);
			td.innerHTML = downCount+1;
		}
	});


</script>



