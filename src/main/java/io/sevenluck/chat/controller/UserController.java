/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.controller;

import io.sevenluck.chat.domain.User;
import io.sevenluck.chat.repository.UserRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author loki
 */

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(method=RequestMethod.GET)
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public User save(@RequestBody User user) {
        logger.info("{}", user);
        
        return userRepository.save(user);
    }
    
    
    
    
    
}
