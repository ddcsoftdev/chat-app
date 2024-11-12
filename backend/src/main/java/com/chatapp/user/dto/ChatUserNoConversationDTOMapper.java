package com.chatapp.user.dto;

import com.chatapp.conversation.dto.ConversationDTO;
import com.chatapp.conversation.entity.Conversation;
import com.chatapp.message.dto.MessageDTOMapper;
import com.chatapp.user.entity.ChatUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ChatUserNoConversationDTOMapper implements Function<ChatUser, ChatUserNoConversationDTO> {
    MessageDTOMapper messageDTOMapper;
    public ChatUserNoConversationDTOMapper(MessageDTOMapper messageDTOMapper) {
        this.messageDTOMapper = messageDTOMapper;
    }

    @Override
    public ChatUserNoConversationDTO apply(ChatUser chatUser) {
        return new ChatUserNoConversationDTO(
                chatUser.getId(),
                chatUser.getFirstName(),
                chatUser.getLastName(),
                chatUser.getNickname(),
                chatUser.getEmail(),
                chatUser.getRole()
        );
    }
}
