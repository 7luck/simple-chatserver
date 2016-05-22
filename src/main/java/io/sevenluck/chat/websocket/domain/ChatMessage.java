/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.websocket.domain;

import java.io.Serializable;

/**
 *
 * @author loki
 */
public class ChatMessage implements Serializable {
    
    private String firstname;
    private String message;

    public ChatMessage() {
    }
    
    public ChatMessage(String firstname, String message) {
        this.firstname = firstname;
        this.message = message;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
