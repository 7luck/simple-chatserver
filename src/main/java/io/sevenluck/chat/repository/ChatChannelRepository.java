/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.repository;

import io.sevenluck.chat.domain.ChatChannel;
import io.sevenluck.chat.domain.ChatRoom;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author loki
 */
public interface ChatChannelRepository extends CrudRepository<ChatChannel,Long>{
    
    public List<ChatChannel> findbyChatRoom(ChatRoom room);
}
