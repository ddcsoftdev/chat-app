package old.user.infrastructure.repository;

import old.user.infrastructure.entity.UserOldEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserOldEntity, Long> {

    Optional<UserOldEntity> findByEmail(String email);

    @Query("SELECT user FROM UserOldEntity user WHERE lower(user.lastName) LIKE lower(concat('%', :query, '%')) " +
            "OR lower(user.firstName) LIKE lower(concat('%', :query, '%'))")
    Page<UserOldEntity> search(Pageable pageable, String query);

    List<UserOldEntity> findByPublicIdIn(List<UUID> publicIds);

    @Modifying
    @Query("UPDATE UserOldEntity  user SET user.lastSeen = :lastSeen WHERE user.publicId = :userPublicID")
    int updateLastSeen(UUID userPublicID, Instant lastSeen);

    Optional<UserOldEntity> findOneByPublicId(UUID publicId);

    List<UserOldEntity> findByConversationsPublicIdAndPublicIdIsNot(UUID conversationsPublicId, UUID publicIdToExclude);
}
