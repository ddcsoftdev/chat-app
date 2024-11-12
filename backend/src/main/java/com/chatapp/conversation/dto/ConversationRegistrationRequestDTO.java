package com.chatapp.conversation.dto;

import com.chatapp.message.dto.MessageDTO;
import com.chatapp.user.dto.ChatUserDTO;

import java.util.Set;

public record ConversationRegistrationRequestDTO(
        Set<ChatUserDTO> users,
        Set<MessageDTO> messages
) {
}
