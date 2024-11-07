package com.chatapp.auth;

import com.chatapp.user.dto.ChatUserDTO;

public record AuthenticationResponseDTO(ChatUserDTO chatUserDTO, String token) {
}
