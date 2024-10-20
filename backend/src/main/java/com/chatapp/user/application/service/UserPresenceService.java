package com.chatapp.user.application.service;

import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.repository.UserRepository;
import com.chatapp.user.domain.vo.UserPublicId;

import java.time.Instant;
import java.util.Optional;

public class UserPresenceService {

    private final UserRepository userRepository;
    private final UserReaderService userReader;

    public UserPresenceService(UserRepository userRepository, UserReaderService userReader) {
        this.userRepository = userRepository;
        this.userReader = userReader;
    }

    public void updatePresence(UserPublicId userPublicId) {
        userRepository.updateLastSeenByPublicId(userPublicId, Instant.now());
    }

    public Optional<Instant> getLastSeenByPublicId(UserPublicId userPublicId) {
        Optional<User> byPublicId = userReader.getByPublicId(userPublicId);
        return byPublicId.map(User::getLastSeen);
    }
}