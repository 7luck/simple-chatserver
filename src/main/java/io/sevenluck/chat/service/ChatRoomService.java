/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.service;

/**
 *
 * @author loki
 */
import io.sevenluck.chat.dto.ChatRoomDTO;
import io.sevenluck.chat.repository.ChatRoomRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

    private final ChatRoomRepository repository;

    @Autowired
    ChatRoomService(ChatRoomRepository repository) {
        this.repository = repository;
    }
    
    public List<ChatRoomDTO> findByNickName(final String nickname) {
        return null;
    }

}
