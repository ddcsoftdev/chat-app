package com.chatapp.user.service;

import com.chatapp.conversation.dto.ConversationDTOMapper;
import com.chatapp.infraestructure.exceptions.types.DuplicateResourceException;
import com.chatapp.infraestructure.exceptions.types.RequestValidationException;
import com.chatapp.infraestructure.exceptions.types.ResourceNotFoundException;
import com.chatapp.infraestructure.exceptions.types.UnauthorizedUserException;
import com.chatapp.infraestructure.jwt.JWTAuthenticationFilter;
import com.chatapp.user.dto.ChatUserDTO;
import com.chatapp.user.dto.ChatUserDTOMapper;
import com.chatapp.user.dto.ChatUserRegistrationRequestDTO;
import com.chatapp.user.dto.ChatUserUpdateRequestDTO;
import com.chatapp.user.entity.ChatUser;
import com.chatapp.user.entity.ChatUserBuilder;
import com.chatapp.user.repository.ChatUserJPARepositoryAdapter;
import com.chatapp.user.enums.EAuthRoles;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public void removeUserById(Long id) {
        if (!chatUserRepository.existsUserWithId(id)){
            throw new ResourceNotFoundException("User with id[%d] not found".formatted(id));
        }
        chatUserRepository.deleteUser(id);
    }

    public void updateUserById(Long id, ChatUserUpdateRequestDTO request) {
        boolean anyChanges = false;
        ChatUser user = chatUserRepository.selectUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id[%d] not found".formatted(id)
                ));

        //Verify each field to check if update is needed
        if (request.firstName() != null &&
                !request.firstName().equals(user.getFirstName())){
            user.setFirstName(request.firstName());
            anyChanges = true;
        }
        if (request.lastName() != null &&
                !request.lastName().equals(user.getLastName())){
            user.setLastName(request.lastName());
            anyChanges = true;
        }
        if (request.nickname() != null &&
                !request.nickname().equals(user.getNickname())){
            user.setNickname(request.nickname());
            anyChanges = true;
        }
        if (request.email() != null &&
                !request.email().equals(user.getEmail())){
            user.setEmail(request.email());
            anyChanges = true;
        }
        if (request.password() != null &&
                !passwordEncoder.matches(request.password(), user.getPassword())){
            user.setEmail(passwordEncoder.encode(request.password()));
            anyChanges = true;
        }
        if (request.role() != null &&
                !request.email().equals(user.getEmail())){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            ChatUser requester = getAuthenticatedUser();
            if (requester.getRole() == EAuthRoles.ADMIN) {
                user.setEmail(request.email());
                anyChanges = true;
            } else {
                throw new UnauthorizedUserException(
                        "%s has no permission to change ROLE".formatted(requester.getEmail()));
            }
        }

        //Validate and save
        if (!anyChanges){
            throw new RequestValidationException(
                    "No changes detected for UPDATE request"
            );
        }
        chatUserRepository.updateUser(user);
    }

    public ChatUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                authentication.getPrincipal() instanceof ChatUser) {
            return (ChatUser) authentication.getPrincipal();
        }
        throw new UnauthorizedUserException("User is not authenticated");
    }
}
