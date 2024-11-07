package com.chatapp.user.dto;

public record ChatUserUpdateRequestDTO (
        String firstName,
        String lastName,
        String nickname,
        String email,
        String password,
        String role
) {
}
