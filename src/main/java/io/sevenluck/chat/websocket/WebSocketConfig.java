/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.websocket;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 *
 * @author loki
 */
@Configuration
@EnableAutoConfiguration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler(), "/echo").addInterceptors(httpAuthTokenHandShakeInterceptor()).setAllowedOrigins("*");
        registry.addHandler(chatWebSocketHandler(), "/chat").addInterceptors(httpAuthTokenHandShakeInterceptor()).setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler echoWebSocketHandler() {
        return new EchoWebSocketHandler();
    }
    
    @Bean
    public ChatWebSocketHandler chatWebSocketHandler() {
        return new ChatWebSocketHandler();
    }
    
    @Bean
    public HttpAuthTokenHandShakeInterceptor httpAuthTokenHandShakeInterceptor() {
        return new HttpAuthTokenHandShakeInterceptor();
    }
}
