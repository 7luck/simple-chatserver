/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.repository;

import io.sevenluck.chat.domain.ChatMessage;
import io.sevenluck.chat.domain.ChatRoom;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author loki
 */
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {
    
    
}
