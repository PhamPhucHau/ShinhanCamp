package com.example.demo;

import com.example.demo.Model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    private static final Logger logger= LoggerFactory.getLogger(WebSocketEventListener.class);
    @Autowired
    private SimpMessageSendingOperations messageTemplate;
    @EventListener

    public void handleWebSocketConnectionListioner(SessionConnectedEvent event)
    {
        logger.info("Receive a new socket Connection");
    }
    @EventListener
    public void handleWebSocketDisconectedListenser(SessionDisconnectEvent event)
    {
        StompHeaderAccessor headerAccessor= StompHeaderAccessor.wrap(event.getMessage());
        String username=(String) headerAccessor.getSessionAttributes().get("username");
        if(username!=null){
            logger.info("user disconnected: "+username);
            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);
            messageTemplate.convertAndSend("/topic/public",chatMessage);

        }
    }
}
