package com.chatapp.conversation.infrastructure.dto;

import com.chatapp.conversation.domain.aggregate.Conversation;
import com.chatapp.message.infrastructure.dto.MessageDto;
import org.jilt.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ConversationDto(UUID publicId, String name,
                              List<UserConversationDto> members,
                              List<MessageDto> messages) {
    public static ConversationDto from(Conversation conversation) {
        ConversationDtoBuilder conversationDTOBuilder = ConversationDtoBuilder.conversationDto()
                .name(conversation.getConversationName().name())
                .publicId(conversation.getConversationPublicId().value())
                .members(UserConversationDto.from(conversation.getMembers()));

        if (conversation.getMessages() != null) {
            conversationDTOBuilder.messages(MessageDto.from(conversation.getMessages()));
        }

        return conversationDTOBuilder.build();
    }

}
