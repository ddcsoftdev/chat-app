package com.chatapp.user.infrastructure.dto;

import com.chatapp.user.domain.aggregate.User;
import org.jilt.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record UserDto(UUID publicId,
                       String firstName,
                       String lastName,
                       String email,
                       String imageUrl,
                       Set<AuthorityDto> authorities) {

    public static UserDto from(User user) {
        UserDtoBuilder userDtoBuilder = UserDtoBuilder.userDto();

        if(user.getImageUrl() != null) {
            userDtoBuilder.imageUrl(user.getImageUrl().value());
        }

        return userDtoBuilder
                .email(user.getEmail().value())
                .firstName(user.getFirstname().value())
                .lastName(user.getLastName().value())
                .publicId(user.getUserPublicId().value())
                .authorities(AuthorityDto.fromSet(user.getAuthorities()))
                .build();
    }
}
