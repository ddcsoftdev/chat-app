package com.chatapp.conversation.controller;


import com.chatapp.conversation.dto.ConversationDTO;
import com.chatapp.conversation.dto.ConversationRegistrationRequestDTO;
import com.chatapp.conversation.service.ConversationService;
import com.chatapp.message.dto.MessageDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conversation")
public class ConversationController {
    ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("")
    public List<ConversationDTO> getAllConversations(){
        return conversationService.getAllConversations();
    }

    @GetMapping("/user/{id}")
    public List<ConversationDTO> getAllConversationsWithUserId(@PathVariable Long id){
        return conversationService.getAllConversationsWithUserId(id);
    }

    @GetMapping("/{id}")
    public ConversationDTO getConversationWithId(@PathVariable Long id){
        return conversationService.getConversationWithId(id);
    }

    @PostMapping("/create")
    ResponseEntity<?> createConversation(@RequestBody ConversationRegistrationRequestDTO request){
        conversationService.createConversation(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, request.toString())
                .build();
    }
}
