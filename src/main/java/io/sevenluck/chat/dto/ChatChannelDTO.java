/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author loki
 */
public class ChatChannelDTO implements Serializable {
    
    private Long id;
    private Long chatroomId;
    private Long memberId;
    private Date joined;

    public ChatChannelDTO(Long chatroomId, Long memberId) {
        this.chatroomId = chatroomId;
        this.memberId = memberId;
    }

    public ChatChannelDTO() {
        super();
    }

    public Long getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(Long chatroomId) {
        this.chatroomId = chatroomId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    @Override
    public String toString() {
        return "ChatChannelDTO{" + "id=" + id + ", chatroomId=" + chatroomId + ", memberId=" + memberId + ", joined=" + joined + '}';
    }
    
    
}
