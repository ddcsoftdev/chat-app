package old.user.application.service;

import old.shared.authentication.application.AuthenticatedUser;
import old.user.domain.aggregate.User;
import old.user.domain.repository.UserRepository;
import com.chatapp.user.domain.vo.*;
import old.user.infrastructure.dto.RegisterRequestDto;
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
    private final UserLookupService userLookupService;
    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository, UserLookupService userLookupService) {
        this.userSynchronizer = new UserSynchronizerService(userRepository);
        this.userReader = new UserReaderService(userRepository);
        this.userPresence = new UserPresenceService(userRepository, userReader);
        this.userLookupService = userLookupService;
        this.userRepository = userRepository;
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
    @Transactional
    public User register(RegisterRequestDto registerRequest) {
        // Check if the email is already registered
        if (userLookupService.getUserByEmail(new UserEmail(registerRequest.email())).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Create a new user
        User newUser = new User(
                new UserLastName(registerRequest.lastName()),
                new UserFirstName(registerRequest.firstName()),
                new UserEmail(registerRequest.email()),
                new UserPassword(registerRequest.password())
        );

        // Save user to the database
        return userRepository.save(newUser);
    }

}
