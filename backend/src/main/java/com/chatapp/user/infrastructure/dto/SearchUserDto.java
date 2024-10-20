package com.chatapp.user.infrastructure.dto;


import com.chatapp.user.domain.aggregate.User;
import org.jilt.Builder;

import java.util.UUID;

@Builder
public record SearchUserDto(String lastName,
                             String firstName,
                             String email,
                             UUID publicId,
                             String imageUrl) {

    public static SearchUserDto from(User user) {
        SearchUserDtoBuilder searchUserDtoBuilder = SearchUserDtoBuilder.searchUserDto();

        if (user.getLastName() != null) {
            searchUserDtoBuilder.lastName(user.getLastName().value());
        }

        if (user.getFirstname() != null) {
            searchUserDtoBuilder.firstName(user.getFirstname().value());
        }

        if (user.getImageUrl() != null) {
            searchUserDtoBuilder.imageUrl(user.getImageUrl().value());
        }

        return searchUserDtoBuilder.email(user.getEmail().value())
                .publicId(user.getUserPublicId().value())
                .build();
    }
}
