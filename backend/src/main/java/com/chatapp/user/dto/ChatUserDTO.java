package com.chatapp.user.dto;

import com.chatapp.conversation.dto.ConversationDTO;
import com.chatapp.user.enums.EAuthRoles;
import org.jilt.Builder;

import java.util.Set;

/**
 * Class that determines which properties to expose to the client.
 */
@Builder
public record ChatUserDTO(
        Long id,
        String firstName,
        String lastName,
        String nickname,
        String email,
        EAuthRoles role,
        Set<ConversationDTO> conversations
){
}
