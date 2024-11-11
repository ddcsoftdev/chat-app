package com.chatapp.conversation.service;

import com.chatapp.conversation.dto.ConversationDTO;
import com.chatapp.conversation.dto.ConversationDTOMapper;
import com.chatapp.conversation.repository.ConversationJPARepositoryAdapter;
import com.chatapp.infraestructure.exceptions.types.ResourceNotFoundException;
import com.chatapp.user.repository.ChatUserJPARepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversationService {
    ConversationJPARepositoryAdapter conversationRepository;
    ChatUserJPARepositoryAdapter chatUserJPARepositoryAdapter;
    ConversationDTOMapper conversationDTOMapper;

    public ConversationService(ConversationJPARepositoryAdapter conversationRepository, ConversationDTOMapper conversationDTOMapper, ChatUserJPARepositoryAdapter chatUserJPARepositoryAdapter) {
        this.conversationRepository = conversationRepository;
        this.conversationDTOMapper = conversationDTOMapper;
        this.chatUserJPARepositoryAdapter = chatUserJPARepositoryAdapter;
    }

    public List<ConversationDTO> getAllConversations() {
        return conversationRepository.selectAllConversations()
                .stream()
                .map(conversationDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ConversationDTO> getAllConversationsWithUserId(Long id) {
        if (!chatUserJPARepositoryAdapter.existsUserWithId(id)){
            throw new ResourceNotFoundException(
                    "No conversations because user with id[%d] not found".formatted(id));
        }
        return conversationRepository.selectConversationByUserId(id)
                .stream()
                .map(conversationDTOMapper)
                .collect(Collectors.toList());
    }
}
