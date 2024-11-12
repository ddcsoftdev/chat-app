package com.chatapp.message.service;

import com.chatapp.conversation.entity.Conversation;
import com.chatapp.conversation.repository.ConversationJPARepositoryAdapter;
import com.chatapp.infraestructure.exceptions.types.DuplicateResourceException;
import com.chatapp.infraestructure.exceptions.types.ResourceNotFoundException;
import com.chatapp.message.dto.MessageRegistrationRequestDTO;
import com.chatapp.message.entity.Message;
import com.chatapp.message.entity.MessageBuilder;
import com.chatapp.message.repository.MessageJPARepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MessageService {
    MessageJPARepositoryAdapter messageRepository;
    ConversationJPARepositoryAdapter conversationRepository;

    public MessageService(MessageJPARepositoryAdapter messageRepository, ConversationJPARepositoryAdapter conversationRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
    }

    public void createMessage(MessageRegistrationRequestDTO request) {
        Long conversationId = request.conversationId();
        Conversation conversation = conversationRepository.selectConversationByUserId(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id[%d] not found".formatted(conversationId)
                ));

        Message message = new MessageBuilder()
                .content(request.content())
                .conversation(conversation)
                .build();

        messageRepository.insertMessage(message);
        /*Set<Message> messages = conversation.getMessages();
        messages.add(message);
        conversation.setMessages(messages);
        conversationRepository.updateConversation(conversation);*/
    }
}
