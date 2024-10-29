package com.chatapp.user.application.service;

import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.repository.UserRepository;
import com.chatapp.user.domain.vo.UserEmail;
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
