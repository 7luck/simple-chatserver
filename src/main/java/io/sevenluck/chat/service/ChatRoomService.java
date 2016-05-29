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
import io.sevenluck.chat.domain.ChatRoom;
import io.sevenluck.chat.dto.ChatRoomDTO;
import io.sevenluck.chat.exception.ChatRoomAlreadyExists;
import io.sevenluck.chat.mapper.ChatRoomMapper;
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
    
    public ChatRoomDTO create(ChatRoomDTO chatroom) throws Exception {
        
        validateIfExists(chatroom);
        
        final ChatRoom entity = repository.save(ChatRoomMapper.toEntity(chatroom));
        return ChatRoomMapper.toDTO(entity);
    }
    
    private void validateIfExists(ChatRoomDTO chatroom) throws Exception {
        
        List<ChatRoom> results = repository.findByName(chatroom.getName());
        if (!results.isEmpty()) {
            throw new ChatRoomAlreadyExists("chatroom with name " + chatroom.getName() + " already exists.");
        }
        
    }

}
