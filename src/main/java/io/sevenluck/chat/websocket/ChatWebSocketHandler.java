/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sevenluck.chat.websocket.domain.ChatMessage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 *
 * @author loki
 */
public class ChatWebSocketHandler extends TextWebSocketHandler {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    
    public ChatWebSocketHandler() {}
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("afterConnectionEstablished" + session.getRemoteAddress().getAddress());
        this.sessions.add(session);
    }
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("handleTextMessage");
        
        try {
            ObjectMapper mapper = new ObjectMapper();

            String payload = message.getPayload();

            ChatMessage msg = mapper.readValue(payload, ChatMessage.class);

            logger.info("msg " + msg);

            for (WebSocketSession webSocketSession : sessions) {

                final String stringifyJSONObject = mapper.writeValueAsString(msg);

                webSocketSession.sendMessage(new TextMessage(stringifyJSONObject));
            } 
        } catch (Exception e) {
            logger.error("TAG", e);
        }
    }
    
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("afterConnectionClosed" + session.getRemoteAddress().getAddress());
        
        sessions.remove(session);
    }
    
    
}