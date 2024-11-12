package com.chatapp.message.controller;

import com.chatapp.message.dto.MessageRegistrationRequestDTO;
import com.chatapp.message.service.MessageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
    MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/create")
    ResponseEntity<?> createMessage(@RequestBody MessageRegistrationRequestDTO request){
        messageService.createMessage(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, request.toString())
                .build();
    }

}
