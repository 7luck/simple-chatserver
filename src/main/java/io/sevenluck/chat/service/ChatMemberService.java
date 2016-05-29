/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.service;

import io.sevenluck.chat.domain.ChatMember;
import io.sevenluck.chat.dto.ChatMemberDTO;
import io.sevenluck.chat.exception.MemberAlreadyExistsException;
import io.sevenluck.chat.mapper.ChatMemberMapper;
import io.sevenluck.chat.repository.ChatMemberRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author loki
 */
@Service
public class ChatMemberService {
    
    private final ChatMemberRepository memberRepository;
    
    @Autowired
    public ChatMemberService(ChatMemberRepository memberRepository){
        this.memberRepository = memberRepository;        
    }
    
    public ChatMemberDTO create(ChatMemberDTO member) throws  Exception {
        validateIfExists(member);
        
        final ChatMember entity = memberRepository.save(ChatMemberMapper.toEntity(member));        
        return ChatMemberMapper.toDTO(entity);
    }
    
    private void validateIfExists(ChatMemberDTO member) throws Exception {
        
        List<ChatMember> result = memberRepository.findByNickname(member.getNickname());
        
        if (!result.isEmpty()) {
            throw new MemberAlreadyExistsException("member with nickanme " + member.getNickname() + " already exists.");
        }
        
    }
    
}
