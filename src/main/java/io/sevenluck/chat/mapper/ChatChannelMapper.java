/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.mapper;

import io.sevenluck.chat.domain.ChatChannel;
import io.sevenluck.chat.dto.ChatChannelDTO;

/**
 *
 * @author loki
 */
public class ChatChannelMapper {
    
    public static ChatChannelDTO toDTO(ChatChannel channel) {
        ChatChannelDTO result = new ChatChannelDTO();
        
        result.setChatroomId(channel.getChatRoom().getId());
        result.setMemberId(channel.getMember().getId());
        result.setId(channel.getId());
        result.setJoined(channel.getJoined());
        
        return result;
    }
    
}
