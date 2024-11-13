package com.chatapp.message.dto;

public record MessageRegistrationRequestDTO(
        String content,
        Long conversationId,
        Long userId
) {
}
