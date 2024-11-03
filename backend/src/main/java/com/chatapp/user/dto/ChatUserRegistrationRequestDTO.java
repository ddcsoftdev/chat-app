package com.chatapp.user.dto;

import com.chatapp.conversation.dto.ConversationDTO;

import java.util.Set;

public record ChatUserRegistrationRequestDTO(
        String firstName,
        String lastName,
        String nickname,
        String email,
        String password,
        String role
) {
}
