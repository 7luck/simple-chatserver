/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.websocket;

import io.sevenluck.chat.domain.ChatRoom;
import io.sevenluck.chat.repository.ChatRoomRepository;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 *
 * @author loki
 */
public class EchoWebSocketHandler extends TextWebSocketHandler {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
     
    private ChatRoomRepository repository;

    public EchoWebSocketHandler() {
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("afterConnectionEstablished" + session.getRemoteAddress().getAddress() + ", attr " +  session.getAttributes().toString());
        List<ChatRoom> rooms = repository.findByName("bla");
        
        this.sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("handleTextMessage " + session.getAttributes().toString());
        
        String payload = message.getPayload();
        
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(payload));
        } 
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("afterConnectionClosed" + session.getRemoteAddress().getAddress());
        
        sessions.remove(session);
    }

    @Autowired
    public void setRepository(ChatRoomRepository repository) {
        this.repository = repository;
    }
    
    
    
}
