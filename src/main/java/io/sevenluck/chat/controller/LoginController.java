/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.controller;

import io.sevenluck.chat.dto.ChatMemberDTO;
import io.sevenluck.chat.dto.ExceptionDTO;
import io.sevenluck.chat.exception.LoginException;
import io.sevenluck.chat.exception.MemberAlreadyExistsException;
import io.sevenluck.chat.service.ChatMemberService;
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
@RequestMapping("/api/login")
public class LoginController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ChatMemberService service;
    
    @RequestMapping(method = RequestMethod.POST)
    public ChatMemberDTO login(@RequestBody ChatMemberDTO member) throws Exception {
        logger.info("login member {}", member);
        return service.login(member);
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleException(LoginException e) {
        logger.error("login failed:", e.getMessage());
        return new ResponseEntity<>(ExceptionDTO.newUnauthorizedInstance(e.getMessage()), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
    
}
