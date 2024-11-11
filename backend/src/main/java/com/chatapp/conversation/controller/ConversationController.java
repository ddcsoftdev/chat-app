package com.chatapp.conversation.controller;


import com.chatapp.conversation.dto.ConversationDTO;
import com.chatapp.conversation.service.ConversationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public List<ConversationDTO> getAllConversationsWithUserId(@PathVariable Long id){
        return conversationService.getAllConversationsWithUserId(id);
    }
}
