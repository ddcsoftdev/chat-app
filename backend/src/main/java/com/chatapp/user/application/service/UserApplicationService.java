package com.chatapp.user.application.service;

import com.chatapp.shared.authentication.application.AuthenticatedUser;
import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.repository.UserRepository;
import com.chatapp.user.domain.vo.UserEmail;
import com.chatapp.user.domain.vo.UserPublicId;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationService {

    private final UserSynchronizerService userSynchronizer;
    private final UserReaderService userReader;
    private final UserPresenceService userPresence;

    public UserApplicationService(UserRepository userRepository) {
        this.userSynchronizer = new UserSynchronizerService(userRepository);
        this.userReader = new UserReaderService(userRepository);
        this.userPresence = new UserPresenceService(userRepository, userReader);
    }

    @Transactional
    public User getAuthenticatedUserWithSync(Jwt oauth2User, boolean forceResync) {
        userSynchronizer.syncWithIdp(oauth2User, forceResync);
        return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<User> search(Pageable pageable, String query) {
        return userReader.search(pageable, query).stream().toList();
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(UserEmail userEmail) {
        return userReader.getByEmail(userEmail);
    }

    @Transactional
    public void updatePresence(UserPublicId userPublicId) {
        userPresence.updatePresence(userPublicId);
    }

    @Transactional(readOnly = true)
    public Optional<Instant> getLastSeen(UserPublicId userPublicId) {
        return userPresence.getLastSeenByPublicId(userPublicId);
    }
}
