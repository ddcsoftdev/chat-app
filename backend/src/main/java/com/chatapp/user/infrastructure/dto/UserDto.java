package com.chatapp.user.infrastructure.dto;

import com.chatapp.infrastructure.primary.user.RestAuthority;
import com.chatapp.infrastructure.primary.user.RestUser;
import com.chatapp.infrastructure.primary.user.RestUserBuilder;
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
                       Set<RestAuthority> authorities) {

    static RestUser from(User user) {
        RestUserBuilder restUserBuilder = RestUserBuilder.restUser();

        if(user.getImageUrl() != null) {
            restUserBuilder.imageUrl(user.getImageUrl().value());
        }

        return restUserBuilder
                .email(user.getEmail().value())
                .firstName(user.getFirstname().value())
                .lastName(user.getLastName().value())
                .publicId(user.getUserPublicId().value())
                .authorities(RestAuthority.fromSet(user.getAuthorities()))
                .build();
    }
}
