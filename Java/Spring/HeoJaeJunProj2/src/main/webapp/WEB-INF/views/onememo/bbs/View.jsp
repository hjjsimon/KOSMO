<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
   <div class="login-wrap">
  	<div class="login-html">
    <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
    	<label for="tab-1" class="tab">게시판<small> 상세 페이지</small></label>
   	<input id="tab-2" type="radio" name="tab" class="sign-up">
    	<label for="tab-2" class="tab" style="float: right; font-size: 18px;">
    	<a class="no-hover" href="<c:url value="/onememo/auth/List.do"/>"><small>목록 페이지로 이동</small></a></label>
    
        <c:if test="${! empty FAILURE}">
			<div class="alert alert-success alert-dismissible fade show">
				   <button type="button" class="close" data-dismiss="alert">&times;</button>
				   <strong>Failure!</strong> ${FAILURE}
			</div>
		</c:if>
		<table class="table table-bordered mt-3">
		<tbody class="table-sm">
			<tr>
				<th class="w-25 bg-dark text-white text-center">번호</th>
				<td class="text-white">${record.no }</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">글쓴이</th>
				<td class="text-white">${record.name }</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">작성일</th>
				<td class="text-white">${record.postDate }</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">제목</th>
				<td class="text-white">${record.title }</td>
			</tr>
			<tr>
				<th class="bg-dark text-white text-center" colspan="2">내 용</th>
			</tr>
			<tr>
				<td class="text-white" colspan="2">${record.content }</td>
				<!-- 브라우저 출력이라 줄바꿈 안됨, \r\n을 <br/>태그로 바꿔줘야함 MemoController에서 시행함-->
			</tr>
		</tbody>
		</table>
		<!-- 수정/삭제/목록 컨트롤 버튼 -->
		<div class="text-center">
			<!-- 아이디 일치할 때만 수정, 삭제 보이게 만듦 -->
			<c:if test="${sessionScope.id ==record.id }" var="isWriter">
				<a href="<c:url value="/onememo/bbs/Edit.do?no=${record.no}"/>"	class="btn btn-success">수정</a> 
				<a href="javascript:isDelete()" class="btn btn-success">삭제</a>
		</c:if>
			<a href="<c:url value="/onememo/bbs/List.do"/>" class="btn btn-success">목록</a>
		</div>
		<!-- 한줄 코멘트 입력/수정 폼 -->
		<form id="form" class="form-inline col-sm-12 d-flex justify-content-center mt-3">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
			<input type="hidden" name="no" value="${record.no}" />
			<!-- 댓글 수정용 -->
			<input type="hidden" name="lno" /> <!-- 댓글의 키값(댓글 번호)을 넘겨줌 -->
			<input type="text" id="linecomment" name="lineComment" class="form-control mx-2 w-50" placeholder="한줄 댓글을 입력하세요" /> 
			<input type="button" class="btn btn-danger mx-2" value="등록" id="submit" />
		</form>
		<!-- 한줄 코멘트 목록 -->
		<div class="row d-flex justify-content-center mt-3">
			<div class="col-sm-8">
				<table class="table table-hover text-center">
					<thead>
						<tr>
							<th class="col-2 text-white">작성자</th>
							<th class="text-white">코멘트</th>
							<th class="col-2 text-white">작성일</th>
							<th class="col-2 text-white">삭제</th>
						</tr>
					</thead>
					<tbody class="table-sm down-file-body" id="comments-list">
						<c:if test="${empty record.comments }" var="isEmpty">
							<tr id="empty-comment">
								<td class="text-white" colspan="4">등록된 한줄 댓글이 없습니다.</td>
							</tr>
						</c:if>		
						<c:if test="${not isEmpty }">
							<!-- record.comments에서 꺼내고 comment에 담음 -->
							<c:forEach var="comment" items="${record.comments }">
								<tr>
									<td class="text-white">${comment.name}</td>
									<td class="text-left text-white line-comment" title="${comment.lno}">${comment.lineComment}</td>
									<td class="text-white">${comment.lpostDate}</td>
									<td>
									<!-- 세션영역에서 로그인한 사람의 아이디 가져와서 comment.name과 비교 -->
										<c:if test="${sessionScope.id==comment.id }" var="isSame">
											<button class="btn btn-info btn-sm my-delete">삭제</button> 
										</c:if>
										<c:if test="${not isSame }">
											<span class="text-danger">삭제불가</span>
										</c:if>
									</td>	
								</tr>	
							</c:forEach>		
						</c:if>	
					</tbody>
				</table>
			</div>
		</div>
	  </div>
    </div>
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>
<script>

//[ajax로 서버에 데이터를 요청하는 함수]
/*
	※제이쿼리 AJAX에서의 요청방식
	 1]GET, POST요청 :
		 1) data:key1=value1&key2=value2&... 혹은 data:{key1:value1,key2:value2,...} -> (JSON으로 해도 키=밸류로 넘어감)
		 2) contentType:"application/x-www-form-urlencoded"(디폴트)			
		 3) 스프링에서는 @RequestParam으로 데이타를 받는다
	 2]POST, PUT, DELETE요청
		1) data:JSON.stringify({key1:value1,key2:value2,...}) -> JSON문자열로 반환한 것, stringify 안하면 키=밸류쌍으로 넘어감
	    2) contentType:"application/json" -> 내가 보내는게 JSON 타입이다 라고 명시해줘야함
	    3) 스프링에서는 @RequestBody 로 데이터를 받는다.
*/
	//[등록 혹은 수정버튼 클릭 이벤트 처리]
	$('#submit').click(function(){//id submit인 태그 가져옴, 화살표함수로 클릭이벤트 걸어줌
		console.log($(this).val());//this는 id가 submit인 요소, value값 가져옴
		console.log($('#form').serialize());//form객체의 serialize(): form의 값들을 키=밸류 쌍으로 묶어줌(제이쿼리에 있는것, 자바돔에 없음)
											//no=12&lno=&linecomment=%EC%95%88%EB%85%95 <- 이건 URL 인코딩된거
		let url;//액션변수설정 
		if($(this).val()=='등록')
			url="<c:url value="/onememo/comments/Write.do"/>";//등록이면 action 설정 이렇게 세팅
		else
			url="<c:url value="/onememo/comments/Edit.do"/>";
		//ajax로 요청
		$.ajax({
			url:url,
			data:$('#form').serialize(),//서버로 데이터 보냄, 키밸류쌍으로
			dataType:'json',//서버에서 데이터는 json으로 받음
			type:'post'//post방식
			
		})
		.done(function(data){//서버로부터 data 받음
			console.log('서버로부터 받은 데이터:',data);
			if($('#submit').val()==='등록'){//value가 등록이면, 코멘트 등록처리
				//data에 담긴 name 등가져옴
				let tr="<tr><td class='text-white'>" + data.name+"</td><td class='text-left text-white line-comment' title='"+data.lno+"'>"+data.lineComment+"</td><td class='text-white'>"+data.lpostDate+"</td><td><span class='btn btn-info btn-sm my-delete'>삭제</span></td></tr>";	
				$('#comments-list').prepend(tr);
				//댓글 등록시 "한줄댓글 없습니다" 라는 요소 삭제
				if($('#empty-comment').length !=0){
					$('#empty-comment').remove();
				}
			}
			else{//아니면 코멘트 수정처리(등록,수정 한방에 처리)
				$('#submit').val('등록');
				//입력한 값으로 클릭한 제목 변경
				$('td[title="'+data.lno+'"]').html(data.lineComment);//td태그중 title속성이~인 애들, innerHtml로 바꿔줌
				$('td[title="'+data.lno+'"]').css('color','red');//방금 수정한건 색 바꿔줌
				
			}
			//입력값 클리어 및 포커스 주기
			$('#linecomment').val('');//value 가져와서 빈문자열 '' 로 설정
			$('#linecomment').focus();
		})
		.fail(function(e){
			console.log('에러:',e);
		});
		
	});
	
	//[댓글의 제목 클릭시]-왕중요
	//※click이벤트 걸 때 반드시 이 형태! 
	//$(document).on('이벤트명','셀렉터',콜백함수)
	//그래야 동적으로 추가된 요소에도 이벤트가 발생한다(아니면 에러도 아니고 걍 안됨)
	$(document).on('click','.line-comment',function(){		
		console.log('댓글 번호:',$(this).attr('title'));//this는 클릭한 태그, 뒤에는 DOM API에서 getAttribute와 동일
		var this_ = $(this);//클릭한 제목, 제이쿼리 객체
		//ajax요청 보냄
		$.ajax({
			url:"<c:url value="/onememo/comments/GetId.do"/>",
			data:"lno="+this_.attr('title')//여기 안에서 $(this) 불가
		})
		.done(function(data){
			console.log('댓글의 작성자 아이디:',data);
			if('${sessionScope.id}'===data){//아이디가 같냐? 본인 댓글만 수정하도록 UI변경, 다른 사람이 쓴 댓글 클릭시 댓글수정입력칸에 안들어옴
				//입력상자값을 클릭한 제목으로 변경, innerHtml과 같은게 .html, 사이값을 this_로 바꿔줄예정
				$('#linecomment').val(this_.html());
				//버튼의 텍스트를 수정으로 변경
				$('#submit').val('수정');
				//폼의 hidden인 lno의 value를 클릭한 제목의 lno값으로 설정
				$('input[name=lno]').val(this_.attr('title'));
				console.log('히든값 설정확인(lno):',$('input[name=lno]').val());
			}
		})
		.fail(function(e){
			console.log('아이디 찾기 오류:',e);
		});
	});

	//[댓글 삭제 처리]
	$(document).on('click','.my-delete',function(){
		if(confirm('정말로 삭제하시겠습니까?')){
			//lno값 가져와서 클릭한 댓글 삭제할 예정, .my-delete클래스 가진 태그에서부터 부모,형,형까지 넘어감 
			console.log('댓글의 키(lno):',$(this).parent().prev().prev().attr('title'));
			let lno=$(this).parent().prev().prev().attr('title');
			var this_ = $(this);
			//삭제처리 요청(ajax로)
			$.ajax({
				url:"<c:url value="/onememo/comments/Delete.do"/>",
				type:'delete',//자원에 대한 요청, REST컨트롤러, 서버의 자원을 처리시 메소드가 delete, 입력은 post, 수정은 put(수정할게 없을때는 입력가능)
				data:JSON.stringify({'lno':lno}),//delete,post,put 모두 json형식으로 보내야함
				dataType:'json',//서버로부터 데이터도 json으로 받음?
				contentType:"application/json"//json으로 보낼때 필수
			}).done(function(data){//서버로부터 받은 데이터는 data 인자로 전달
				console.log('삭제 성공:',data)//페이지를 다시 받는게 아니고 데이터를 받는거라 댓글입력칸에 ~~~입력후 삭제해도 입력한거 안날아감
				//클릭한 댓글을 포함한 tr삭제
				this_.parent().parent().remove();
			});
		}
	});
	
	//[메모글 삭제]
	function isDelete() {
		if(confirm("삭제 할래요?")){
			location.replace("<c:url value="/onememo/bbs/Delete.do?no=${record.no}"/>");//삭제 후 목록으로 보냄
			
		}
	}
	










</script>