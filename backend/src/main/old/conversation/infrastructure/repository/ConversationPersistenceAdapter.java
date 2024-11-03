package old.conversation.infrastructure.repository;

import old.conversation.domain.aggregate.Conversation;
import old.conversation.domain.repository.ConversationRepository;
import old.conversation.infrastructure.entity.ConversationEntity;
import old.user.infrastructure.entity.UserOldEntity;
import old.conversation.domain.vo.CreateConversation;
import old.conversation.domain.vo.ConversationPublicId;
import old.user.domain.aggregate.User;
import old.user.domain.vo.UserPublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ConversationPersistenceAdapter implements ConversationRepository {

    private final JpaConversationRepository jpaConversationRepository;

    public ConversationPersistenceAdapter(JpaConversationRepository jpaConversationRepository) {
        this.jpaConversationRepository = jpaConversationRepository;
    }

    @Override
    public Conversation save(CreateConversation conversation, List<User> members) {
        ConversationEntity newConversationEntity = ConversationEntity.from(conversation);
        newConversationEntity.setUsers(UserOldEntity.from(members));
        ConversationEntity newConversationSaved = jpaConversationRepository.saveAndFlush(newConversationEntity);
        return ConversationEntity.toDomain(newConversationSaved);
    }

    @Override
    public Optional<Conversation> get(ConversationPublicId conversationPublicId) {
        return jpaConversationRepository.findOneByPublicId(conversationPublicId.value())
                .map(ConversationEntity::toDomain);
    }

    @Override
    public Page<Conversation> getConversationByUserPublicId(UserPublicId publicId, Pageable pageable) {
        return jpaConversationRepository.findAllByUsersPublicId(publicId.value(), pageable)
                .map(ConversationEntity::toDomain);
    }

    @Override
    public int deleteByPublicId(UserPublicId userPublicId, ConversationPublicId conversationPublicId) {
        return jpaConversationRepository
                .deleteByUsersPublicIdAndPublicId(userPublicId.value(), conversationPublicId.value());
    }

    @Override
    public Optional<Conversation> getConversationByUsersPublicIdAndPublicId(UserPublicId userPublicId, ConversationPublicId conversationPublicId) {
        return jpaConversationRepository.findOneByUsersPublicIdAndPublicId(userPublicId.value(), conversationPublicId.value())
                .map(ConversationEntity::toDomain);
    }

    @Override
    public Optional<Conversation> getConversationByUserPublicIds(List<UserPublicId> publicIds) {
        List<UUID> userUUIDs = publicIds.stream().map(UserPublicId::value).toList();
        return jpaConversationRepository.findOneByUsersPublicIdIn(userUUIDs, userUUIDs.size())
                .map(ConversationEntity::toDomain);
    }

    @Override
    public Optional<Conversation> getOneByPublicId(ConversationPublicId conversationPublicId) {
        return jpaConversationRepository.findOneByPublicId(conversationPublicId.value())
                .map(ConversationEntity::toDomain);
    }
}
