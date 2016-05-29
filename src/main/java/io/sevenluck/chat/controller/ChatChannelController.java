/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.controller;

import io.sevenluck.chat.dto.ChatChannelDTO;
import io.sevenluck.chat.dto.ExceptionDTO;
import io.sevenluck.chat.exception.EntityNotFoundException;
import io.sevenluck.chat.service.ChatChannelService;
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
@RequestMapping("/api/chatchannels")
public class ChatChannelController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChatChannelService service;

    @RequestMapping(method = RequestMethod.POST)
    public ChatChannelDTO create(@RequestBody ChatChannelDTO channel) throws Exception {
        logger.info("create channel " + channel);
        return service.create(channel);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable Long id) throws Exception {
        logger.info("delete channel " + id);
        service.delete(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleException(EntityNotFoundException e) {
        logger.error("validate:", e.getMessage());
        return new ResponseEntity<>(ExceptionDTO.newNotFoundInstance(e.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
