/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.websocket;

import io.sevenluck.chat.websocket.domain.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * @author loki
 */
@Controller
public class ChatController {
    
    @MessageMapping("/chatws")
    @SendTo("/topic/ws")
    public ChatMessage postChatMessage(ChatMessage msg) throws  Exception {
        Thread.sleep(3000); // simulated delay
        return msg;        
    }
    
}
