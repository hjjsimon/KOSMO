package com.kosmo.springapp.basic.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//웹소켓 서버
@Component
public class WebSocketServer extends TextWebSocketHandler{//핸들러상속, 지금 클래스도 핸들러됨
	
	//여러명의 사용자가 서버 접속시 웹소켓 세션을 각자 부여받음, 이걸 밸류로하고 사용자의 아이디를 키로 만들자!
	//접속한 클라이언트를 저장하기 위한 속성(멤버변수)]
	//키는 웹소켓 세션 아이디
	private Map<String, WebSocketSession> clients = new HashMap<>();

	//클라이언트와 연결이 되었을때 호출되는 콜백 메소드]
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//컬렉션에 연결된 클라이언트 추가
		clients.put(session.getId(), session);
		System.out.println(session.getId()+"연결 되었습니다");
	}

	//클라이언트로부터 메세지를 받았을 때 자동 호출되는 콜백 메소드]
	//여기서 클라이언트로 메세지도 보냄(푸시)
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(session.getId()+"로부터 받은 메시지:"+message.getPayload());
		//접속한 모든 클라이언트에게 session.getId()가 보낸 메시지 뿌려주기] - 자신은 제외해야함
		for(WebSocketSession client : clients.values()){//키값 관심없이 밸류만 가져옴, 맵컬렉션에서 밸류 꺼내면 컬렉션타입 WebSocketSession타입
			if(!session.getId().equals(client.getId())) {
				//받은 메시지를 그대로 접속한 모든 인원에게 푸시
				client.sendMessage(message);
			}
		}
	}

	//클라이언트와 통신장애시 자동으로 호출되는 메소드]
	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.out.println(session.getId()+"와 통신장애 발생:"+e.getMessage());
	}

	//클라이언트와 연결이 끊어졌을때 호출되는 콜백 메소드]
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//컬렉션에 저장된 클라이언트 삭제
		clients.remove(session.getId());
		System.out.println(session.getId()+"연결이 끊어졌어요");

	}
	
	
	
	
	
	
	
	

}
