/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author loki
 */
@Entity
@Table(name = "chatuser")
public class ChatUser implements Serializable {

    
    private Long    id;
    private String  nickname;
    private String  password;
    private String  joined;

    private Set<ChatChannel> chatchannels;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getJoined() {
        return joined;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    @OneToMany(mappedBy = "member")
    public Set<ChatChannel> getChatchannels() {
        return chatchannels;
    }

    public void setChatchannels(Set<ChatChannel> chatchannels) {
        this.chatchannels = chatchannels;
    }

}
