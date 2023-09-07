<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<style>
	p {
	  color: whitesmoke;
	  margin: 10px;
	}
	
	span {
	  display: inline-block;
	  max-width: 180px;
	  padding: 5px 10px;
	  position: relative;
	  word-wrap: break;
	}
	
	.ext {
	  text-align: left;
	}
	
	.ext span {
	  background: #000000;
	  border-radius: 0 5px 5px 5px;
	}
	
	.int {
	  text-align: right;
	}
	
	.int span {
	  background: #FF0000;
	  border-radius: 5px 5px 0 5px;
	  right: 0;
	}
</style>
<jsp:include page="/WEB-INF/views/template/Top.jsp"/><!-- webapp가 루트 -->
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>Spring Framework <small>WebSocket</small></h1> 
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">웹소켓</legend>
        	<form>
				<div class="form-group">
					<label><kbd class="lead">닉네임</kbd></label>
					<input type="text" class="form-control" id="nickname"
					placeholder="닉네임을 입력하세요">
				</div>
				<input class="btn btn-info" type="button" id="enterBtn" value="입장">
				<input class="btn btn-danger" type="button" id="exitBtn" value="퇴장">
		
		
				<div class="form-group">
					<h4>대화내용</h4>
					<div id="chatArea">
						<div id="chatMessage"
							style="height: 300px; border: 1px gray solid; overflow:auto;"></div>
					</div>
				</div>
				<div class="form-group">
					<label><kbd class="lead">메시지</kbd></label>
					<input type="text" class="form-control" id="message" placeholder="메시지를 입력하세요">
				</div>		
			</form>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/WEB-INF/views/template/Footer.jsp"/>
<script>
/*
 채팅테스트
 localhost -> 아이피로 변경
 고급 보안이 포함된 방화벽 > 인바운드 규칙 > 새규칙 > 포트 > TCP, 90909 > 이름 websocket 끝
 
 채팅 말풍선 CSS -> 여기저기 검색하면 이쁜거 많다~
 codepen
 */*/
	//웹소켓 객체 저장용
	var wsocket;
	//닉네임 저장용
	var nickname;
	//입장버튼 클릭시]- 서버와 연결된 웹소켓 클라이언트 생성
	$('#enterBtn').one('click',function(){//입장 당연히 한번만 실행돼야함
		wsocket = new WebSocket("ws://192.168.0.52:9090<c:url value="/chat-ws"/>");//엔드포인트 chat-ws 적어줌, 서버 만들어야 연결됨
		//192.168.0.52 -> cmd로 ipconfig /all 해서 localhost 바꿈
		console.log('wsocket:',wsocket);
		//서버와 연결된 웹소켓에 이벤트 등록
		wsocket.onopen = open;
		wsocket.onclose = function(){//익명함수
			appendMessage("연결이 끊어졌어요");//창에 메세지 계속 추가해주는 메소드
		};
		wsocket.onmessage = receive;
		wsocket.onerror = function(e){
			consol.log('에러:',e);
		};
		
		
	});	
	
	//사용자가 서버에 연결되었을때 호출되는 콜백함수 
	function open(){
		//서버로 연결한 사람의 정보(닉네임) 전송
		//msg:kim가(이) 입장했어요
		
		//사용자가 입력한 닉네임 저장
		nickname = $('#nickname').val();
		
		wsocket.send('msg:'+nickname+'가(이) 입장했어요');//다른 사용자가 볼 채팅
		appendMessage("연결 되었습니다");//사용자 본인이 볼 채팅
		
	}
	//서버에서 메시지를 받을때마다 호출되는 함수
	function receive(e){//e는 message이벤트 객체
		//서버로부터 받은 데이터는 이벤트객체(e).data속성에 저장되어있다
		console.log('message이벤트 객체:', e);
		console.log('서버로부터 받은 메세지:', e.data);
		if(e.data.substring(0,4).toUpperCase() == 'MSG:')
			//서버로부터 받은 메세지를 msg:부분을 제외하고 div에 출력
			appendMessage('<p class="int"><span>'+e.data.substring(4)+'</span></p>');//0~4는 msg, 4이후로가 실제 메세지임
		
	}
	//사용자가 입력한 메시지(확인용) 또는 서버로부터 받은 메시지를 채팅창에 출력하는 함수
	function appendMessage(msg){
		
		$('#chatMessage').append(msg);
		//스크롤바를 자동으로 위로 올리기(내리기 아닌가?)
		$('#chatMessage').get(0).scrollTop = $('#chatMessage').get(0).scrollHeight;
		//get(0) 으로 제이쿼리-> 자바스크립트 객체로 바꿈, 무조건 1개니까 0인덱스
	}
	
	//퇴장 버튼 클릭시
	$('#exitBtn').one('click',function(){
		wsocket.send('msg:'+nickname+'가(이) 퇴장했어요');
		wsocket.close();
	});
	
	$('#message').on('keypress',function(e){//keydown 아니야!
		
		if(e.keyCode===13){//13이면 엔터
			//엔터치면 입력한 메세지 서버로 전송
			wsocket.send('msg:'+nickname+'>>'+$(this).val());
			appendMessage('<p class="int"><span>'+$(this).val()+'</span></p>');
			//기존 메세지 클리어
			$(this).val("");
			//포커스 주기
			$(this).focus();

			
		}
	})
	








</script>



