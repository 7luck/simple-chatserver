/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sevenluck.chat.domain.ChatChannel;
import io.sevenluck.chat.domain.ChatMember;
import io.sevenluck.chat.domain.ChatMessage;
import io.sevenluck.chat.domain.ChatRoom;
import io.sevenluck.chat.domain.ChatSession;
import io.sevenluck.chat.mapper.ChatMessageMapper;
import io.sevenluck.chat.repository.ChatChannelRepository;
import io.sevenluck.chat.repository.ChatMessageRepository;
import io.sevenluck.chat.repository.ChatRoomRepository;
import io.sevenluck.chat.repository.ChatSessionRepository;
import io.sevenluck.chat.websocket.domain.ChatMessageDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
public class ChatWebSocketHandler extends TextWebSocketHandler {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private ChatMessageRepository   chatMessageRepository;
    private ChatSessionRepository   chatSessionRepository;
    private ChatRoomRepository      chatroomRepository;
    private ChatChannelRepository   chatChannelRepository;
    
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    
    Map<String, SessionItem> sessionMap = new ConcurrentHashMap<>();
    
    public ChatWebSocketHandler() {}
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("afterConnectionEstablished from ip " + session.getRemoteAddress().getAddress());        
        
        final String token = (String) session.getAttributes().get(HttpAuthTokenHandShakeInterceptor.X_TOKEN);
        List<ChatSession> chatSessions = chatSessionRepository.findByAuthtoken(token);
        if (!sessions.isEmpty()) {
            logger.info("chatsession {} assigned to token {}", chatSessions.get(0), token);
            this.sessionMap.put(token, new SessionItem(chatSessions.get(0), session));
        }        
    }
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        final String myToken = (String) session.getAttributes().get(HttpAuthTokenHandShakeInterceptor.X_TOKEN);
        
        logger.info("handleTextMessage with message {} and token {}", message, myToken);
        String payload = message.getPayload();        
        
        try {            
            ObjectMapper mapper = new ObjectMapper();            
            ChatMessageDTO chatMessage = mapper.readValue(payload, ChatMessageDTO.class);
            
            
            final ChatRoom room = findChatRoom(chatMessage);
            if (null == room) return;
            
            chatMessageRepository.save(newInstance(session, room, chatMessage));
            List<ChatChannel> channels = chatChannelRepository.findbyChatRoom(room);
            
            for (ChatChannel channel : channels) {
                
                List<ChatSession> chatSessions = chatSessionRepository.findByMember(channel.getMember());
                for (ChatSession chatSession : chatSessions) {
                    
                    if (!myToken.equals(chatSession.getAuthtoken()) && sessionMap.containsKey(chatSession.getAuthtoken())) {
                        WebSocketSession wsSession = sessionMap.get(chatSession.getAuthtoken()).getSession();
                        final String stringifyJSONObject = mapper.writeValueAsString(chatMessage);
                        wsSession.sendMessage(new TextMessage(stringifyJSONObject));
                    }
                }
            }
            
        } catch (Exception e) {
            logger.error("TAG", e);
        }
    }
    
    private ChatMessage newInstance(WebSocketSession wsSession, ChatRoom room, ChatMessageDTO chatMessage) {
        
        final String token = (String) wsSession.getAttributes().get(HttpAuthTokenHandShakeInterceptor.X_TOKEN);
        final ChatMember author = sessionMap.get(token).getChatSession().getMember();
        ChatMessage message = ChatMessageMapper.toEntity(chatMessage);
        message.setAuthor(author);
        message.setChatRoom(room);
        
        return message;
    }
    
    private ChatRoom findChatRoom(ChatMessageDTO chatMessage) {
        return chatroomRepository.findOne(chatMessage.getChatroomId());
    }
    
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        final String token = (String) session.getAttributes().get(HttpAuthTokenHandShakeInterceptor.X_TOKEN);
        
        logger.info("afterConnectionClosed with token {} and cause {}", token, status.getReason());
        if (sessionMap.containsKey(token)) {
            sessionMap.remove(token);
        } else {
            logger.info("could not find any websession for token {}", token);
        }
    }
    
    @Autowired
    public void setChatChannelRepository(ChatChannelRepository chatChannelRepository) {
        this.chatChannelRepository = chatChannelRepository;
    }

    @Autowired
    public void setChatRoomRepository(ChatRoomRepository chatroomRepository) {
        this.chatroomRepository = chatroomRepository;
    }
    
    @Autowired
    public void setMessageRepository(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Autowired
    public void setChatSessionRepository(ChatSessionRepository chatSessionRepository) {
        this.chatSessionRepository = chatSessionRepository;
    }
    
    public class SessionItem implements Serializable {
        private final ChatSession       chatSession;
        private final WebSocketSession  session;

        public SessionItem(ChatSession chatSession, WebSocketSession session) {
            this.chatSession = chatSession;
            this.session = session;
        }

        public ChatSession getChatSession() {
            return chatSession;
        }

        public WebSocketSession getSession() {
            return session;
        }
    }
    
    
}
