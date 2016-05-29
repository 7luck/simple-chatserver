/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.exception;

/**
 *
 * @author loki
 */
public class ChatRoomAlreadyExists extends Exception {

    public ChatRoomAlreadyExists(String message) {
        super(message);
    }

    public ChatRoomAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    
}
