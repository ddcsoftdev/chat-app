package old.user.infrastructure.repository;

import old.user.infrastructure.entity.UserOldEntity;
import old.conversation.domain.vo.ConversationPublicId;
import old.user.domain.aggregate.User;
import old.user.domain.repository.UserRepository;
import old.user.domain.vo.UserEmail;
import old.user.domain.vo.UserPublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class UserPersistenceAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserPersistenceAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        if (user.getDbId() != null) {
            Optional<UserOldEntity> userToUpdateOpt = jpaUserRepository.findById(user.getDbId());
            if (userToUpdateOpt.isPresent()) {
                UserOldEntity userToUpdate = userToUpdateOpt.get();
                userToUpdate.updateFromUser(user);
                jpaUserRepository.saveAndFlush(userToUpdate);
            }
        } else {
            jpaUserRepository.save(UserOldEntity.from(user));
        }
        return user;
    }

    @Override
    public Optional<User> get(UserPublicId userPublicId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getOneByEmail(UserEmail userEmail) {
        return jpaUserRepository.findByEmail(userEmail.value())
                .map(UserOldEntity::toDomain);
    }

    @Override
    public List<User> getByPublicIds(Set<UserPublicId> userPublicIds) {
        List<UUID> rawPublicIds = userPublicIds.stream().map(UserPublicId::value).toList();
        return jpaUserRepository.findByPublicIdIn(rawPublicIds)
                .stream()
                .map(UserOldEntity::toDomain)
                .toList();
    }

    @Override
    public Page<User> search(Pageable pageable, String query) {
        return jpaUserRepository.search(pageable, query).map(UserOldEntity::toDomain);
    }

    @Override
    public int updateLastSeenByPublicId(UserPublicId userPublicId, Instant lastSeen) {
        return jpaUserRepository.updateLastSeen(userPublicId.value(), lastSeen);
    }

    @Override
    public List<User> getRecipientByConversationIdExcludingReader(ConversationPublicId conversationPublicId, UserPublicId readerPublicId) {
        return jpaUserRepository.findByConversationsPublicIdAndPublicIdIsNot(conversationPublicId.value(), readerPublicId.value())
                .stream().map(UserOldEntity::toDomain).toList();
    }

    @Override
    public Optional<User> getOneByPublicId(UserPublicId userPublicId) {
        return jpaUserRepository.findOneByPublicId(userPublicId.value())
                .map(UserOldEntity::toDomain);
    }
}
