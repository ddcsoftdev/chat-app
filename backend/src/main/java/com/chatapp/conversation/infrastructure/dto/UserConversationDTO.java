package com.chatapp.conversation.infrastructure.dto;

import com.chatapp.messaging.domain.user.aggregate.User;
import org.jilt.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
public record UserConversationDTO(String lastName, String firstName,
                                      UUID publicId, String imageUrl,
                                      Instant lastSeen) {

    public static UserConversationDTO from(User user) {
        UserConversationDTOBuilder userConversationDTOBuilder = UserConversationDTOBuilder.userConversationDTO();

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

    public static List<UserConversationDTO> from(Set<User> users) {
        return users.stream().map(UserConversationDTO::from).toList();
    }
}
