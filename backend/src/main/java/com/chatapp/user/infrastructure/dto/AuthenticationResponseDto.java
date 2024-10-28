package com.chatapp.user.infrastructure.dto;

import org.jilt.Builder;

@Builder
public record AuthenticationResponseDto(String jwtToken) {

    public static AuthenticationResponseDto from(String jwtToken) {
        return AuthenticationResponseDtoBuilder
                .authenticationResponseDto()
                .jwtToken(jwtToken)
                .build();
    }
}
