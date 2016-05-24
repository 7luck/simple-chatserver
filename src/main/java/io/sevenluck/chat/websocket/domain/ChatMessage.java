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
    
    private String nickname;
    private String text;

    public ChatMessage() {}
    
    public ChatMessage(String text, String nickname) {
        this.nickname = nickname;
        this.text = text;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ChatMessage{" + "nickname=" + nickname + ", text=" + text + '}';
    }
}
