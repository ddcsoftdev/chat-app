package com.chatapp.user.infrastructure.dto;

import org.jilt.Builder;

@Builder
public record LoginRequestDto(String username, String password) {

    public static LoginRequestDto from(String username, String password) {
        return LoginRequestDtoBuilder
                .loginRequestDto()
                .username(username)
                .password(password)
                .build();
    }
}
