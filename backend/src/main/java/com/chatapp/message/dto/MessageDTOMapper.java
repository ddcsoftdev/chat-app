package com.chatapp.message.dto;

import com.chatapp.message.entity.Message;
import com.chatapp.user.dto.ChatUserDTO;
import com.chatapp.user.entity.ChatUser;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MessageDTOMapper implements Function<Message, MessageDTO> {
    @Override
    public MessageDTO apply(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getContent(),
                message.getConversation().getId()
        );
    }
}
