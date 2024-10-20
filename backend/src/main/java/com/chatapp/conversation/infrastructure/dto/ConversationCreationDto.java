package com.chatapp.conversation.infrastructure.dto;

import com.chatapp.messaging.domain.message.aggregate.ConversationToCreate;
import com.chatapp.messaging.domain.message.aggregate.ConversationToCreateBuilder;
import com.chatapp.messaging.domain.message.vo.ConversationName;
import com.chatapp.user.domain.vo.UserPublicId;
import org.jilt.Builder;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record ConversationCreationDto(Set<UUID> members, String name) {

    public static ConversationToCreate toDomain(ConversationCreationDto restConversationToCreate) {
        ConversationCreationDtoBuilder restConversationToCreateBuilder = ConversationCreationDtoBuilder.conversationCreationDto();

        Set<UserPublicId> userUUIDs = restConversationToCreate.members
                .stream()
                .map(UserPublicId::new)
                .collect(Collectors.toSet());

        return ConversationToCreateBuilder.conversationToCreate()
                .name(new ConversationName(restConversationToCreate.name()))
                .members(userUUIDs)
                .build();
    }
}
