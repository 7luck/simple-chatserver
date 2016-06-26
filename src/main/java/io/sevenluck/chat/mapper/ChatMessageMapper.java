/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.mapper;

import io.sevenluck.chat.domain.ChatMessage;
import io.sevenluck.chat.websocket.domain.ChatMessageDTO;
import java.util.Date;

/**
 *
 * @author loki
 */
public class ChatMessageMapper {
    
    public static ChatMessage toEntity(ChatMessageDTO dto) {
        
        ChatMessage message = new ChatMessage();
        message.setInserted(new Date());
        message.setText(dto.getText());
        return message;
    }
    
}
