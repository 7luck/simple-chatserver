/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.websocket;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 *
 * @author loki
 */
public class HttpAuthTokenHandShakeInterceptor implements HandshakeInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public static final String X_TOKEN = "X-Token";
    
    @Override
    public boolean beforeHandshake(ServerHttpRequest shr, ServerHttpResponse shr1, WebSocketHandler wsh, Map<String, Object> attributes) throws Exception {
        logger.info("beforeHandshake ");
        
        if (shr instanceof ServletServerHttpRequest) {
            logger.info("weiter gehts");
            
            ServletServerHttpRequest request = (ServletServerHttpRequest) shr;
            final String token = request.getServletRequest().getHeader(X_TOKEN);
            attributes.put(X_TOKEN, token);            
            
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest shr, ServerHttpResponse shr1, WebSocketHandler wsh, Exception excptn) {
        logger.info("afterHandshake");
    }
    
    
}
