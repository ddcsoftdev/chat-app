package com.chatapp.message.infrastructure.repository;

import com.chatapp.conversation.domain.aggregate.Conversation;
import com.chatapp.conversation.infrastructure.entity.ConversationEntity;
import com.chatapp.message.infrastructure.entity.MessageEntity;
import com.chatapp.infrastructure.secundary.repository.JpaMessageBinaryContent;
import com.chatapp.user.infrastructure.entity.UserEntity;
import com.chatapp.message.domain.aggregate.Message;
import com.chatapp.messaging.domain.message.repository.MessageRepository;
import com.chatapp.conversation.domain.vo.ConversationPublicId;
import com.chatapp.message.domain.vo.MessageSendState;
import com.chatapp.message.domain.vo.MessageType;
import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.vo.UserPublicId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessagePersistenceAdapter implements MessageRepository {

    private final JpaMessageRepository jpaMessageRepository;
    private final JpaMessageBinaryContent jpaMessageBinaryContent;

    public MessagePersistenceAdapter(JpaMessageRepository jpaMessageRepository, JpaMessageBinaryContent jpaMessageBinaryContent) {
        this.jpaMessageRepository = jpaMessageRepository;
        this.jpaMessageBinaryContent = jpaMessageBinaryContent;
    }

    @Override
    public Message save(Message message, User sender, Conversation conversation) {
        MessageEntity messageEntity = MessageEntity.from(message);
        messageEntity.setSender(UserEntity.from(sender));
        messageEntity.setConversation(ConversationEntity.from(conversation));

        if (message.getContent().type() != MessageType.TEXT) {
            jpaMessageBinaryContent.save(messageEntity.getContentBinary());
        }

        MessageEntity messageSaved = jpaMessageRepository.save(messageEntity);
        return MessageEntity.toDomain(messageSaved);
    }

    @Override
    public int updateMessageSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId, MessageSendState state) {
        return jpaMessageRepository.updateMessageSendState(conversationPublicId.value(), userPublicId.value(), state);
    }

    @Override
    public List<Message> findMessageToUpdateSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId) {
        return jpaMessageRepository.findMessageToUpdateSendState(conversationPublicId.value(), userPublicId.value())
                .stream().map(MessageEntity::toDomain).toList();
    }
}
