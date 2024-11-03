package old.user.application.service;

import old.user.domain.aggregate.User;
import old.user.domain.repository.UserRepository;
import old.user.domain.vo.UserEmail;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLookupService {

    private final UserRepository userRepository;

    public UserLookupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByEmail(UserEmail userEmail) {
        return userRepository.getOneByEmail(userEmail);
    }
}
