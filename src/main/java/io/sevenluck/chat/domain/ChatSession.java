/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author loki
 */
@Entity
@Table(name = "chatsessions")
public class ChatSession implements Serializable {
    
    private Long        id;
    @NotNull
    private Date        inserted;
    private Date        lastModified;
    private ChatMember  member;
    private String      nickname;
    private String      authtoken;
    private String      ipAddress;

    public ChatSession() {
        super();
        this.inserted = new Date();
    }

    
    
    public ChatSession(ChatMember member, String authtoken, String ipAddress) {
        this();
        this.member = member;
        this.nickname = member.getNickname();
        this.authtoken = authtoken;
        this.ipAddress = ipAddress;
    }

    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInserted() {
        return inserted;
    }

    public void setInserted(Date inserted) {
        this.inserted = inserted;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @ManyToOne
    public ChatMember getMember() {
        return member;
    }

    public void setMember(ChatMember member) {
        this.member = member;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    
    
    
}
