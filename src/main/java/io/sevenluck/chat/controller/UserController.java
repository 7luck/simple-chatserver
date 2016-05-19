/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.controller;

import io.sevenluck.chat.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author loki
 */

@RestController
public class UserController {
    
    @RequestMapping(path = "/users", method=RequestMethod.GET)
    public User users() {
        return new User("lukas", "kaminski");
    }
    
}
