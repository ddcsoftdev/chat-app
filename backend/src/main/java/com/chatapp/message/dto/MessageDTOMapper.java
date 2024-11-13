package com.chatapp.message.dto;

import com.chatapp.infraestructure.encryption.EncryptionUtil;
import com.chatapp.message.entity.Message;
import com.chatapp.user.dto.ChatUserDTO;
import com.chatapp.user.entity.ChatUser;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MessageDTOMapper implements Function<Message, MessageDTO> {
    private final EncryptionUtil encryptionUtil;

    public MessageDTOMapper(EncryptionUtil encryptionUtil) {
        this.encryptionUtil = encryptionUtil;
    }

    @Override
    public MessageDTO apply(Message message) {
        String decryptedContent = message.getContent();
        try {
            decryptedContent = encryptionUtil.decrypt(message.getContent());
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt message content", e);
        }
        return new MessageDTO(
                message.getId(),
                decryptedContent,
                message.getConversation().getId(),
                message.getUser().getId(),
                message.getPostedAt()
        );
    }
}
