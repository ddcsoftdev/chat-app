package com.chatapp.auth;

public record AuthenticationRequest(
        String email,
        String password
) {
}
