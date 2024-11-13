package com.chatapp.message.controller;

import com.chatapp.message.dto.MessageDTO;
import com.chatapp.message.dto.MessageRegistrationRequestDTO;
import com.chatapp.message.dto.MessageUpdateRequestDTO;
import com.chatapp.message.service.MessageService;
import com.chatapp.user.dto.ChatUserDTO;
import com.chatapp.user.dto.ChatUserUpdateRequestDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
    MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("")
    public List<MessageDTO> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public MessageDTO getMessageById(@PathVariable("id") Long id) {
        return messageService.getMessageById(id);
    }

    @PostMapping("/create")
    ResponseEntity<?> createMessage(@RequestBody MessageRegistrationRequestDTO request){
        messageService.createMessage(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, request.toString())
                .build();
    }

    @DeleteMapping("/{id}")
    public void removeMessageById(@PathVariable("id") Long id){
        messageService.removeMessageById(id);
    }

    @PutMapping("/{id}")
    public void updateMessageById(@PathVariable("id") Long id,
                               @RequestBody MessageUpdateRequestDTO request){
        messageService.updateMessageById(id, request);
    }

}
