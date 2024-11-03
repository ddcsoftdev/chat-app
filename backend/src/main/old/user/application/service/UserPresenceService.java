package old.user.application.service;

import old.user.domain.aggregate.User;
import old.user.domain.repository.UserRepository;
import old.user.domain.vo.UserPublicId;

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