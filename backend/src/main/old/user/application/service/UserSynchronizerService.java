package old.user.application.service;

import old.shared.authentication.application.AuthenticatedUser;
import old.user.domain.aggregate.User;
import old.user.domain.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserSynchronizerService {

    private final UserRepository userRepository;

    private static final String UPDATE_AT_KEY = "updated_at";

    public UserSynchronizerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void syncWithIdp(Jwt jwtToken, boolean forceResync) {
        Map<String, Object> attributes = jwtToken.getClaims();
        List<String> rolesFromToken = AuthenticatedUser.extractRolesFromToken(jwtToken);
        User user = User.fromTokenAttributes(attributes, rolesFromToken);
        Optional<User> existingUser = userRepository.getOneByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            if (attributes.get(UPDATE_AT_KEY) != null) {
                Instant lastModifiedDate = existingUser.orElseThrow().getLastModifiedDate();
                Instant idpModifiedDate;
                if (attributes.get(UPDATE_AT_KEY) instanceof Instant instant) {
                    idpModifiedDate = instant;
                } else {
                    idpModifiedDate = Instant.ofEpochSecond((Integer) attributes.get(UPDATE_AT_KEY));
                }
                if (idpModifiedDate.isAfter(lastModifiedDate) || forceResync) {
                    updateUser(user, existingUser.get());
                }
            }
        } else {
            user.initFieldForSignup();
            userRepository.save(user);
        }
    }

    private void updateUser(User user, User existingUser) {
        existingUser.updateFromUser(user);
        userRepository.save(existingUser);
    }
}