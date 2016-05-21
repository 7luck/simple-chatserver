/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.service;

import io.sevenluck.chat.domain.User;
import io.sevenluck.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author loki
 */
@Service
public class UserService {
    
    private final UserRepository repository;
 
    @Autowired
    UserService(UserRepository repository) {
        this.repository = repository;
    }
    
    public User update(User user) {
        
        User updateUser = repository.findOne(user.getId());
        
        updateUser.setFirstname(user.getFirstname());
        updateUser.setLastname(user.getLastname());
        
        return this.repository.save(updateUser);        
    }
    
    
}
