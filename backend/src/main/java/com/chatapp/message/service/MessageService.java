package com.chatapp.message.service;

import com.chatapp.conversation.entity.Conversation;
import com.chatapp.conversation.repository.ConversationJPARepositoryAdapter;
import com.chatapp.infraestructure.encryption.EncryptionUtil;
import com.chatapp.infraestructure.exceptions.types.DuplicateResourceException;
import com.chatapp.infraestructure.exceptions.types.ResourceNotFoundException;
import com.chatapp.message.dto.MessageRegistrationRequestDTO;
import com.chatapp.message.entity.Message;
import com.chatapp.message.entity.MessageBuilder;
import com.chatapp.message.repository.MessageJPARepositoryAdapter;
import com.chatapp.user.entity.ChatUser;
import com.chatapp.user.repository.ChatUserJPARepositoryAdapter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class MessageService {
    private final MessageJPARepositoryAdapter messageRepository;
    private final ConversationJPARepositoryAdapter conversationRepository;
    private final ChatUserJPARepositoryAdapter userRepository;
    private final EncryptionUtil encryptionUtil;

    public MessageService(MessageJPARepositoryAdapter messageRepository, ConversationJPARepositoryAdapter conversationRepository, ChatUserJPARepositoryAdapter userRepository, EncryptionUtil encryptionUtil) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.encryptionUtil = encryptionUtil;
    }

    public void createMessage(MessageRegistrationRequestDTO request) {
        Long conversationId = request.conversationId();
        Conversation conversation = conversationRepository.selectConversationById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Conversation with id[%d] not found".formatted(conversationId)
                ));

        Long userId = request.userId();
        ChatUser user = userRepository.selectUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id[%d] not found".formatted(conversationId)
                ));

        String encryptedMessage = request.content();
        try{
            encryptedMessage = encryptionUtil.encrypt(request.content());
        } catch (Exception e){
            throw new RuntimeException("Failed to encrypt message content", e);
        }

        LocalDateTime time = LocalDateTime.now();

        Message message = new MessageBuilder()
                .content(encryptedMessage)
                .conversation(conversation)
                .user(user)
                .postedAt(time)
                .build();

        messageRepository.insertMessage(message);
    }
}
