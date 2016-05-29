/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.dto;

import java.io.Serializable;

/**
 *
 * @author loki
 */
public class ChatMemberDTO implements Serializable {
    
    private String  nickname;
    private String  password;
    
    private String  authtoken;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    @Override
    public String toString() {
        return "ChatMemberDTO{" + "nickname=" + nickname + ", password=" + password + ", authtoken=" + authtoken + '}';
    }
    
}
