package old.conversation.infrastructure.dto;

import old.conversation.domain.aggregate.Conversation;
import com.chatapp.conversation.infrastructure.dto.ConversationDtoBuilder;
import old.message.infrastructure.dto.MessageDto;
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
