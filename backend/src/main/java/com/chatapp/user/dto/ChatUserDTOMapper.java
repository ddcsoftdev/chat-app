package com.chatapp.user.dto;

import com.chatapp.conversation.dto.ConversationDTO;
import com.chatapp.conversation.dto.ConversationDTOMapper;
import com.chatapp.conversation.entity.Conversation;
import com.chatapp.infraestructure.exceptions.types.ResourceNotFoundException;
import com.chatapp.message.dto.MessageDTOMapper;
import com.chatapp.user.entity.ChatUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ChatUserDTOMapper implements Function<ChatUser, ChatUserDTO> {
    MessageDTOMapper messageDTOMapper;
    public ChatUserDTOMapper(MessageDTOMapper messageDTOMapper) {
        this.messageDTOMapper = messageDTOMapper;
    }

    @Override
    public ChatUserDTO apply(ChatUser chatUser) {
        return new ChatUserDTO(
                chatUser.getId(),
                chatUser.getFirstName(),
                chatUser.getLastName(),
                chatUser.getNickname(),
                chatUser.getEmail(),
                chatUser.getRole(),
                mapConversations(chatUser.getConversations())
        );
    }

    private Set<ConversationDTO> mapConversations(Set<Conversation> conversations){
        Set<ConversationDTO> conversationsDTO = new HashSet<>();
        conversations.forEach(c -> {
                    ConversationDTO conversationDTO = new ConversationDTO(
                            c.getId(),
                            c.getUsers().stream()
                                    .map(chatUser -> {
                                        return new ChatUserNoConversationDTOBuilder()
                                                .id(chatUser.getId())
                                                .firstName(chatUser.getFirstName())
                                                .lastName(chatUser.getLastName())
                                                .nickname(chatUser.getNickname())
                                                .email(chatUser.getEmail())
                                                .role(chatUser.getRole())
                                                .build();
                                            }

                                    )
                                    .collect(Collectors.toSet()),
                            c.getMessages().stream()
                                    .map(messageDTOMapper)
                                    .collect(Collectors.toSet())
                            );
                    conversationsDTO.add(conversationDTO);
                });
        return conversationsDTO;
    }
}
