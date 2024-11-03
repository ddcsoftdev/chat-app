package com.chatapp.user.dto;

import com.chatapp.conversation.dto.ConversationDTOMapper;
import com.chatapp.user.entity.ChatUser;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ChatUserDTOMapper implements Function<ChatUser, ChatUserDTO> {

    public final ConversationDTOMapper conversationDTOMapper;

    public ChatUserDTOMapper(ConversationDTOMapper conversationDTOMapper) {
        this.conversationDTOMapper = conversationDTOMapper;
    }

    @Override
    public ChatUserDTO apply(ChatUser chatUser) {
        return new ChatUserDTO(
                chatUser.getId(),
                chatUser.getFirstName(),
                chatUser.getLastName(),
                chatUser.getNickname(),
                chatUser.getEmail(),
                chatUser.getRole()
                //conversationDTOMapper.mapToDTOSet(chatUser.getConversations())
        );
    }
}
