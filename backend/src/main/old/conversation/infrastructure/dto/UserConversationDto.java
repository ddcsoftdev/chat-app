package old.conversation.infrastructure.dto;

import com.chatapp.conversation.infrastructure.dto.UserConversationDtoBuilder;
import old.user.domain.aggregate.User;
import org.jilt.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
public record UserConversationDto(String lastName, String firstName,
                                  UUID publicId, String imageUrl,
                                  Instant lastSeen) {
    public static UserConversationDto from(User user) {
        UserConversationDtoBuilder userConversationDTOBuilder = UserConversationDtoBuilder.userConversationDto();

        if (user.getImageUrl() != null) {
            userConversationDTOBuilder.imageUrl(user.getImageUrl().value());
        }

        return userConversationDTOBuilder
                .lastName(user.getLastName().value())
                .firstName(user.getFirstname().value())
                .publicId(user.getUserPublicId().value())
                .lastSeen(user.getLastSeen())
                .build();
    }

    public static List<UserConversationDto> from(Set<User> users) {
        return users.stream().map(UserConversationDto::from).toList();
    }
}
