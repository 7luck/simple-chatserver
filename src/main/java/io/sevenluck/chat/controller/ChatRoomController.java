/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.controller;

import io.sevenluck.chat.dto.ChatMemberDTO;
import io.sevenluck.chat.dto.ChatRoomDTO;
import io.sevenluck.chat.dto.ExceptionDTO;
import io.sevenluck.chat.exception.ChatRoomAlreadyExists;
import io.sevenluck.chat.exception.MemberAlreadyExistsException;
import io.sevenluck.chat.service.ChatRoomService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author loki
 */
@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Autowired
    private ChatRoomService chatRoomService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<ChatRoomDTO> getAllByNickname(String nickname) {
        return chatRoomService.findByNickName(nickname);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ChatRoomDTO create(@RequestBody ChatRoomDTO chatroom) throws Exception {
        return chatRoomService.create(chatroom);
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleException(ChatRoomAlreadyExists e) {
        logger.error("validate:", e.getMessage());
        return new ResponseEntity<>(ExceptionDTO.newConflictInstance(e.getMessage()), new HttpHeaders(), HttpStatus.CONFLICT);
    }
    
    
}
