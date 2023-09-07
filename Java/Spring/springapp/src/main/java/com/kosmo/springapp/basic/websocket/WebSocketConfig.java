package com.kosmo.springapp.basic.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket//웹소켓 활성화
public class WebSocketConfig implements WebSocketConfigurer{
	
	//웹소켓 서버를 생성자 인젝션으로 초기화
	private final WebSocketServer webSocketServer;
	public WebSocketConfig(WebSocketServer webSocketServer) {
		this.webSocketServer = webSocketServer;
	}
	
	//클라이언트 접속을 위한 엔드포인트 설정(/chat-ws 했던게 엔드포인트)
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketServer, "/chat-ws").setAllowedOrigins("*");//* 해야 도메인(브라우저?) 달라도 접속 가능
	}

	
}
