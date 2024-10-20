package com.chatapp.user.infrastructure.dto;

import com.chatapp.user.infrastructure.dto.SearchUserDtoBuilder;
import com.chatapp.user.domain.aggregate.User;
import org.jilt.Builder;

import java.util.UUID;

@Builder
public record SearchUserDto(String lastName,
                             String firstName,
                             String email,
                             UUID publicId,
                             String imageUrl) {

}
