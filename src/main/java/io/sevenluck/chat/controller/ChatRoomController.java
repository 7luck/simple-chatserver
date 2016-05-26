/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.controller;

import io.sevenluck.chat.domain.User;
import io.sevenluck.chat.dto.ChatRoomDTO;
import io.sevenluck.chat.service.ChatRoomService;
import io.sevenluck.chat.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    
}
