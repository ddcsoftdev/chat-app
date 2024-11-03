package old.conversation.infrastructure.dto;

import old.conversation.domain.vo.CreateConversation;
import com.chatapp.conversation.domain.vo.CreateConversationBuilder;
import old.conversation.domain.vo.ConversationName;
import com.chatapp.conversation.infrastructure.dto.ConversationCreationDtoBuilder;
import old.user.domain.vo.UserPublicId;
import org.jilt.Builder;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record ConversationCreationDto(Set<UUID> members, String name) {

    public static CreateConversation toDomain(ConversationCreationDto restConversationToCreate) {
        ConversationCreationDtoBuilder restConversationToCreateBuilder = ConversationCreationDtoBuilder.conversationCreationDto();

        Set<UserPublicId> userUUIDs = restConversationToCreate.members
                .stream()
                .map(UserPublicId::new)
                .collect(Collectors.toSet());

        return CreateConversationBuilder.createConversation()
                .name(new ConversationName(restConversationToCreate.name()))
                .members(userUUIDs)
                .build();
    }
}
