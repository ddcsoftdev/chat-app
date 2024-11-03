package old.message.infrastructure.repository;

import old.conversation.domain.aggregate.Conversation;
import old.conversation.infrastructure.entity.ConversationEntity;
import old.message.infrastructure.entity.MessageEntity;
import old.user.infrastructure.entity.UserOldEntity;
import old.message.domain.aggregate.Message;
import old.message.domain.repository.MessageRepository;
import old.conversation.domain.vo.ConversationPublicId;
import old.message.domain.vo.MessageSendState;
import old.message.domain.vo.MessageType;
import old.user.domain.aggregate.User;
import old.user.domain.vo.UserPublicId;
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
        messageEntity.setSender(UserOldEntity.from(sender));
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
