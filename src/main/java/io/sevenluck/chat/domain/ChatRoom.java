/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author loki
 */
@Entity
@Table(name = "chatrooms")
public class ChatRoom implements Serializable {
    
    private Long    id;
    @NotNull
    private Date    inserted;
    @NotNull
    private String  name;
    @NotNull
    private String  description;
    
    private String  passwordKey;
    @NotNull
    private boolean publicChat;
    private Date    updated;

    public ChatRoom() {}
    
    public ChatRoom(Date inserted, boolean publicChat) {
        this.inserted = inserted;
        this.publicChat = publicChat;
    }
    
    public static ChatRoom newInstance() {
        return new ChatRoom(new Date(), true);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPasswordKey() {
        return passwordKey;
    }

    public void setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isPublicChat() {
        return publicChat;
    }

    public void setPublicChat(boolean publicChat) {
        this.publicChat = publicChat;
    }

    @Override
    public String toString() {
        return "ChatRoom{" + "id=" + id + ", inserted=" + inserted + ", name=" + name + ", description=" + description + ", passwordKey=" + passwordKey + ", publicChat=" + publicChat + ", updated=" + updated + '}';
    }
}
