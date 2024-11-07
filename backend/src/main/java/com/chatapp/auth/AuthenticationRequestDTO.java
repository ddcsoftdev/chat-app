package com.chatapp.auth;

public record AuthenticationRequestDTO(
        String email,
        String password
) {
}
