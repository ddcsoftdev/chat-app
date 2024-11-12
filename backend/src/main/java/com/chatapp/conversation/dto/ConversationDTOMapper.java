package com.chatapp.conversation.dto;

import com.chatapp.conversation.entity.Conversation;
import com.chatapp.message.dto.MessageDTO;
import com.chatapp.message.dto.MessageDTOMapper;
import com.chatapp.message.entity.Message;
import com.chatapp.user.dto.ChatUserDTO;
import com.chatapp.user.dto.ChatUserDTOMapper;
import com.chatapp.user.dto.ChatUserNoConversationDTO;
import com.chatapp.user.dto.ChatUserNoConversationDTOMapper;
import com.chatapp.user.entity.ChatUser;
import com.chatapp.user.entity.ChatUserBuilder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ConversationDTOMapper implements Function<Conversation, ConversationDTO> {
    public final ChatUserNoConversationDTOMapper chatUserDTOMapper;
    public final MessageDTOMapper messageDTOMapper;

    public ConversationDTOMapper(ChatUserNoConversationDTOMapper chatUserDTOMapper, MessageDTOMapper messageDTOMapper) {
        this.chatUserDTOMapper = chatUserDTOMapper;
        this.messageDTOMapper = messageDTOMapper;
    }

    @Override
    public ConversationDTO apply(Conversation conversation) {
        return new ConversationDTO(
                conversation.getId(),
                mapUsersDto(conversation.getUsers()),
                mapMessagesDto(conversation.getMessages()));
    }

    public Set<ConversationDTO> mapToDTOSet(Set<Conversation> conversations) {
        return conversations.stream()
                .map(this::apply)
                .collect(Collectors.toSet());
    }

    public Conversation toConversation(ConversationDTO conversationDTO) {
        Set<ChatUser> users = new HashSet<>();
        conversationDTO.users().forEach(user -> {
            ChatUser chatUser = new ChatUserBuilder()
                    .id(user.id())
                    .firstName(user.firstName())
                    .lastName(user.lastName())
                    .nickname(user.nickname())
                    .email(user.email())
                    .role(user.role())
                    .build();
            users.add(chatUser);
        });
        return new Conversation(
                conversationDTO.id(),
                users,
                new HashSet<>()
        );
    }

    public Set<Conversation> mapToConversationSet(Set<ConversationDTO> conversationDTOs) {
        return conversationDTOs.stream()
                .map(this::toConversation)
                .collect(Collectors.toSet());
    }

    private Set<ChatUserNoConversationDTO> mapUsersDto(Set<ChatUser> users){
       return users.stream()
                .map(chatUserDTOMapper)
               .collect(Collectors.toSet());
    }

    private Set<MessageDTO> mapMessagesDto(Set<Message> users){
        return users.stream()
                .map(messageDTOMapper)
                .collect(Collectors.toSet());
    }
}
