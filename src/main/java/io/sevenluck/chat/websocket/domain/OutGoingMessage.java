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
 * @param <T>
 */
public class OutGoingMessage<T> implements Serializable {
    
    private T           data;    
    private String      authtoken;
    private MessageType type;
    
    public static enum MessageType {
        CHAT, NEW_ROOM_EVENT
    }
    
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
    
}
