package com.chatapp.infraestructure.websocket;

import com.chatapp.message.dto.MessageDTO;
import com.chatapp.message.dto.MessageRegistrationRequestDTO;
import com.chatapp.message.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    MessageService messageService;
    public WebSocketController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessageRegistrationRequestDTO sendMessage(@Payload MessageRegistrationRequestDTO request) {
        return request;
    }
}
