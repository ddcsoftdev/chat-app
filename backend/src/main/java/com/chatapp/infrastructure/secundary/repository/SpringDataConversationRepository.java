package com.chatapp.infrastructure.secundary.repository;

import com.chatapp.infrastructure.secundary.entity.ConversationEntity;
import com.chatapp.infrastructure.secundary.entity.UserEntity;
import com.chatapp.messaging.domain.message.aggregate.Conversation;
import com.chatapp.messaging.domain.message.aggregate.ConversationToCreate;
import com.chatapp.messaging.domain.message.repository.ConversationRepository;
import com.chatapp.messaging.domain.message.vo.ConversationPublicId;
import com.chatapp.messaging.domain.user.aggregate.User;
import com.chatapp.messaging.domain.user.vo.UserPublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SpringDataConversationRepository implements ConversationRepository {

    private final JpaConversationRepository jpaConversationRepository;

    public SpringDataConversationRepository(JpaConversationRepository jpaConversationRepository) {
        this.jpaConversationRepository = jpaConversationRepository;
    }

    @Override
    public Conversation save(ConversationToCreate conversation, List<User> members) {
        ConversationEntity newConversatioEntity = ConversationEntity.from(conversation);
        newConversatioEntity.setUsers(UserEntity.from(members));
        ConversationEntity newConversationSaved = jpaConversationRepository.saveAndFlush(newConversatioEntity);
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
