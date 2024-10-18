package com.chatapp.conversation.infrastructure;

import com.chatapp.conversation.domain.Conversation;
import com.chatapp.infrastructure.primary.conversation.RestUserForConversation;
import com.chatapp.infrastructure.primary.message.RestMessage;
import org.jilt.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ConversationDTO(UUID publicId, String name,
                               List<RestUserForConversation> members,
                               List<RestMessage> messages) {

    public static ConversationDTO from(Conversation conversation) {
        ConversationDTOBuilder conversationDTOBuilder = ConversationDTOBuilder.conversationDTO()
                .name(conversation.getConversationName().name())
                .publicId(conversation.getConversationPublicId().value())
                .members(RestUserForConversation.from(conversation.getMembers()));

        if (conversation.getMessages() != null) {
            conversationDTOBuilder.messages(RestMessage.from(conversation.getMessages()));
        }

        return conversationDTOBuilder.build();
    }

}
