/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.mapper;

import io.sevenluck.chat.domain.ChatRoom;
import io.sevenluck.chat.dto.ChatRoomDTO;
import io.sevenluck.chat.util.MD5Util;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author loki
 */
public class ChatRoomMapper {

    public static ChatRoomDTO toDTO(final ChatRoom value) {
        ChatRoomDTO result = new ChatRoomDTO();
        
        result.setName(value.getName());
        result.setDescription(value.getDescription());
        result.setPublicChat(value.isPublicChat());
        
        return result;
    }
    
    public static ChatRoom toEntity(ChatRoomDTO dto) {
        return toEntity(null, dto);
    }
    
    public static ChatRoom toEntity(ChatRoom chatroom, ChatRoomDTO value) {
        ChatRoom result = new ChatRoom();
        
        if (null == chatroom) {
            result = SerializationUtils.clone(chatroom);
        }
        
        result.setName(value.getName());
        result.setDescription(value.getDescription());        
        
        if(BooleanUtils.isTrue(value.isPublicChat())) {
            result.setPublicChat(true);
            result.setPasswordKey(MD5Util.getMD5(value.getPassword()));
        } else {
            result.setPublicChat(false);
            result.setPasswordKey(null);
        }
        
        return result;
        
    }
    

}
