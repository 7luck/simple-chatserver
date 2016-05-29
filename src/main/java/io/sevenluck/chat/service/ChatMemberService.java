/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.service;

import io.sevenluck.chat.domain.ChatMember;
import io.sevenluck.chat.domain.ChatSession;
import io.sevenluck.chat.dto.ChatMemberDTO;
import io.sevenluck.chat.exception.LoginException;
import io.sevenluck.chat.exception.MemberAlreadyExistsException;
import io.sevenluck.chat.mapper.ChatMemberMapper;
import io.sevenluck.chat.repository.ChatMemberRepository;
import io.sevenluck.chat.repository.ChatSessionRepository;
import io.sevenluck.chat.util.MD5Util;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author loki
 */
@Service
public class ChatMemberService {
    
    private final ChatMemberRepository memberRepository;
    private final ChatSessionRepository sessionRepository;
    
    @Autowired
    public ChatMemberService(ChatMemberRepository memberRepository, ChatSessionRepository sessionRepository) {
        this.memberRepository   = memberRepository;        
        this.sessionRepository  = sessionRepository;
    }
    
    public ChatMemberDTO create(ChatMemberDTO member) throws  Exception {
        validateIfExists(member);
        
        final ChatMember entity = memberRepository.save(ChatMemberMapper.toEntity(member));        
        return ChatMemberMapper.toDTO(entity);
    }
    
    public ChatMemberDTO login(ChatMemberDTO member) throws Exception {
        
        List<ChatMember> results = memberRepository.findByNicknameAndPassword(member.getNickname(), MD5Util.getMD5(member.getPassword()));
        
        if (results.isEmpty() || results.size() > 1) {
            throw  new LoginException("login failed");
        }
        
        String token = String.valueOf(UUID.randomUUID());
        final ChatMember logInMember = results.get(0);
        
        ChatSession session = new ChatSession(logInMember, token, null);
        session = sessionRepository.save(session);
        
        ChatMemberDTO result = ChatMemberMapper.toDTO(logInMember);
        result.setAuthtoken(session.getAuthtoken());
        
        return result;
    }
    
    private void validateIfExists(ChatMemberDTO member) throws Exception {
        
        List<ChatMember> result = memberRepository.findByNickname(member.getNickname());
        
        if (!result.isEmpty()) {
            throw new MemberAlreadyExistsException("member with nickname " + member.getNickname() + " already exists.");
        }
        
    }
    
}
