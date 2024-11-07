package com.chatapp.user.service;

import com.chatapp.conversation.dto.ConversationDTOMapper;
import com.chatapp.shared.exceptions.types.DuplicateResourceException;
import com.chatapp.shared.exceptions.types.ResourceNotFoundException;
import com.chatapp.user.dto.ChatUserDTO;
import com.chatapp.user.dto.ChatUserDTOMapper;
import com.chatapp.user.dto.ChatUserRegistrationRequestDTO;
import com.chatapp.user.entity.ChatUser;
import com.chatapp.user.entity.ChatUserBuilder;
import com.chatapp.user.repository.ChatUserJPARepositoryAdapter;
import com.chatapp.user.utils.EAuthRoles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatUserService {

    private final PasswordEncoder passwordEncoder;
    private final ChatUserJPARepositoryAdapter chatUserRepository;
    private final ChatUserDTOMapper chatUserDTOMapper;
    private final ConversationDTOMapper conversationDTOMapper;

    public ChatUserService(PasswordEncoder passwordEncoder, ChatUserJPARepositoryAdapter chatUserRepository, ChatUserDTOMapper chatUserDTOMapper, ConversationDTOMapper conversationDTOMapper) {
        this.passwordEncoder = passwordEncoder;
        this.chatUserRepository = chatUserRepository;
        this.chatUserDTOMapper = chatUserDTOMapper;
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

    public List<ChatUserDTO> getAllUsers(){
        return chatUserRepository.selectAllUsers()
                .stream()
                .map(chatUserDTOMapper)
                .collect(Collectors.toList());
    }

    public ChatUserDTO getUserById(Long id) {
        return chatUserRepository.selectUserById(id)
                .map(chatUserDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id[%d] not found".formatted(id)
                ));
    }
}
