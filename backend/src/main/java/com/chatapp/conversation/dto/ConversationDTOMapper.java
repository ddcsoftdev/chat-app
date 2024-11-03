package com.chatapp.conversation.dto;

import com.chatapp.conversation.entity.Conversation;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ConversationDTOMapper implements Function<Conversation, ConversationDTO> {
    @Override
    public ConversationDTO apply(Conversation conversation) {
        return new ConversationDTO(
                conversation.getId()
        );
    }

    public Set<ConversationDTO> mapToDTOSet(Set<Conversation> conversations) {
        return conversations.stream()
                .map(this::apply)
                .collect(Collectors.toSet());
    }

    public Conversation toConversation(ConversationDTO conversationDTO) {
        return new Conversation(
                conversationDTO.id()
        );
    }

    public Set<Conversation> mapToConversationSet(Set<ConversationDTO> conversationDTOs) {
        return conversationDTOs.stream()
                .map(this::toConversation)
                .collect(Collectors.toSet());
    }
}
