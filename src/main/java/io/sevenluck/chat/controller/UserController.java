/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.controller;

import io.sevenluck.chat.domain.User;
import io.sevenluck.chat.dto.UserDTO;
import io.sevenluck.chat.repository.UserRepository;
import io.sevenluck.chat.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    User getById(@PathVariable("id") Long id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User save(@RequestBody @Valid User user) {
        logger.info("create {}", user);

        return userRepository.save(user);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public UserDTO update(@PathVariable("id") Long id, @RequestBody UserDTO dtoUser) {
        
        logger.info("userdto {} for id {} ", dtoUser, id);
        
        final User user = dtoUser.toUser();
        user.setId(id);
        
        return new UserDTO(userService.update(user));
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleException(Exception ex) {
        logger.error("error:", ex);
        return new ResponseEntity<>(new User(0L, "b","a"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
