package com.chatapp.user.service;

import com.chatapp.conversation.dto.ConversationDTOMapper;
import com.chatapp.conversation.entity.Conversation;
import com.chatapp.conversation.entity.ConversationBuilder;
import com.chatapp.exceptions.DuplicateResourceException;
import com.chatapp.user.dto.ChatUserRegistrationRequestDTO;
import com.chatapp.user.entity.ChatUser;
import com.chatapp.user.entity.ChatUserBuilder;
import com.chatapp.user.repository.ChatUserJPARepositoryAdapter;
import com.chatapp.user.utils.EAuthRoles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class ChatUserService {

    private final PasswordEncoder passwordEncoder;
    private final ChatUserJPARepositoryAdapter chatUserRepository;
    private final ConversationDTOMapper conversationDTOMapper;

    public ChatUserService(PasswordEncoder passwordEncoder, ChatUserJPARepositoryAdapter chatUserRepository, ConversationDTOMapper conversationDTOMapper) {
        this.passwordEncoder = passwordEncoder;
        this.chatUserRepository = chatUserRepository;
        this.conversationDTOMapper = conversationDTOMapper;
    }

    public void addCustomer(ChatUserRegistrationRequestDTO request) {
        //check if email exists
        if (chatUserRepository.existsUserWithEmail(request.email())) {
            throw new DuplicateResourceException("User email is already in use.");
        }

        ChatUser chatUser = new ChatUserBuilder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .nickname(request.nickname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(EAuthRoles.valueOf(request.role()))
                .build();

        chatUserRepository.insertUser(chatUser);
    }
}
