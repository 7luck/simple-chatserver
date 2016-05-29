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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author loki
 */
@Entity
@Table(name = "chatchannels")
public class ChatChannel implements Serializable {

    private Long id;

    private Date joined;
    private ChatRoom chatRoom;
    private ChatMember member;

    public ChatChannel() {
    }

    
    
    public static ChatChannel newInstance(ChatRoom chatRoom, ChatMember member) {
        ChatChannel channel = new ChatChannel(chatRoom, member);
        channel.setJoined(new Date());
        return channel;
    }
    
    private ChatChannel(ChatRoom chatRoom, ChatMember member) {
        this.chatRoom = chatRoom;
        this.member = member;
    }

    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    
    @ManyToOne
    @JoinColumn(name = "member_id")
    public ChatMember getMember() {
        return member;
    }

    public void setMember(ChatMember member) {
        this.member = member;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

}
