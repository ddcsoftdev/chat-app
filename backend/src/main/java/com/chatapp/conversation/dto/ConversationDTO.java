package com.chatapp.conversation.dto;

import com.chatapp.message.dto.MessageDTO;
import com.chatapp.user.dto.ChatUserDTO;
import org.jilt.Builder;

import java.util.Set;

@Builder
public record ConversationDTO(
    Long id,
    Set<ChatUserDTO> users,
    Set<MessageDTO> messages
) {
}
