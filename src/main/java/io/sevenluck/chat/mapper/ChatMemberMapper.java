/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.mapper;

import io.sevenluck.chat.domain.ChatMember;
import io.sevenluck.chat.dto.ChatMemberDTO;
import io.sevenluck.chat.util.MD5Util;
import java.util.Date;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author loki
 */
public class ChatMemberMapper {
    
    public static ChatMember toEntity(ChatMemberDTO value) {
        return toEntity(null, value);
    }
    
    public static ChatMember toEntity(ChatMember member, ChatMemberDTO value) {
        ChatMember result = new ChatMember();
        result.setJoined(new Date());
        
        if (null != member) {
            result = SerializationUtils.clone(member);
        }
        
        result.setNickname(value.getNickname());
        result.setPassword(MD5Util.getMD5(value.getPassword()));
        
        return result;
    }
    
    public static ChatMemberDTO toDTO(ChatMember entity) {
        ChatMemberDTO result = new ChatMemberDTO();
        
        result.setNickname(entity.getNickname());
        
        return result;
    }
    
}
