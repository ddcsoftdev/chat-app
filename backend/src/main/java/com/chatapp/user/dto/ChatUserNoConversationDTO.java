package com.chatapp.user.dto;

import com.chatapp.conversation.dto.ConversationDTO;
import com.chatapp.user.enums.EAuthRoles;
import org.jilt.Builder;

import java.util.Set;

@Builder
public record ChatUserNoConversationDTO (
        Long id,
        String firstName,
        String lastName,
        String nickname,
        String email,
        EAuthRoles role
){
}
