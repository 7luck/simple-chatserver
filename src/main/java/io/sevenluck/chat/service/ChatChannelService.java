/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.service;

import io.sevenluck.chat.domain.ChatChannel;
import io.sevenluck.chat.domain.ChatMember;
import io.sevenluck.chat.domain.ChatRoom;
import io.sevenluck.chat.dto.ChatChannelDTO;
import io.sevenluck.chat.exception.EntityNotFoundException;
import io.sevenluck.chat.mapper.ChatChannelMapper;
import io.sevenluck.chat.repository.ChatChannelRepository;
import io.sevenluck.chat.repository.ChatMemberRepository;
import io.sevenluck.chat.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author loki
 */
@Service
public class ChatChannelService {
    
    private final ChatChannelRepository repository;
    private final ChatMemberRepository  memberRepository;
    private final ChatRoomRepository    roomRepository;
    
    @Autowired
    public ChatChannelService(ChatChannelRepository repository, ChatMemberRepository memberRepository, ChatRoomRepository roomRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
        this.roomRepository = roomRepository;
    }
    
    public ChatChannelDTO create(ChatChannelDTO channel) throws Exception {
        
        final ChatRoom chatroom = roomRepository.findOne(channel.getChatroomId());
        if (null == chatroom) {
            throw new EntityNotFoundException("chatroom with id " + channel.getChatroomId() + " not found.");
        }
        
        final ChatMember member = memberRepository.findOne(channel.getMemberId());
        if (null == member) {
            throw new EntityNotFoundException("member with id " + channel.getChatroomId() + " not found");
        }
        
        ChatChannel entity = repository.save(ChatChannel.newInstance(chatroom, member));        
        return ChatChannelMapper.toDTO(entity);
    }
    
    public void delete(Long id) throws Exception {
        ChatChannel delete = repository.findOne(id);
        if (null == delete) {
            throw new EntityNotFoundException("channel with id " + id + " not found.");
        }
        
        repository.delete(delete);        
    }
}
