package com.chatapp.user.application.service;

import com.chatapp.shared.authentication.application.AuthenticatedUser;
import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.repository.UserRepository;
import com.chatapp.user.domain.vo.UserEmail;
import com.chatapp.user.domain.vo.UserPublicId;
import com.chatapp.user.infrastructure.dto.AuthenticationResponseDto;
import com.chatapp.user.infrastructure.dto.LoginRequestDto;
import com.chatapp.user.infrastructure.dto.RegisterRequestDto;
import com.chatapp.user.infrastructure.security.JwtUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserApplicationService(UserRepository userRepository,
                                  AuthenticationManager authenticationManager,
                                  JwtUtil jwtUtil,
                                  PasswordEncoder passwordEncoder) {
        this.userSynchronizer = new UserSynchronizerService(userRepository);
        this.userReader = new UserReaderService(userRepository);
        this.userPresence = new UserPresenceService(userRepository, userReader);
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AuthenticationResponseDto login(LoginRequestDto loginRequest) {
        // Authenticate the user using the AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        // Generate a JWT token
        String jwtToken = jwtUtil.generateToken(authentication.getName());
        return new AuthenticationResponseDto(jwtToken);
    }

    @Transactional
    public User register(RegisterRequestDto registerRequest) {

        User newUser = new User(
                registerRequest.lastName(),
                registerRequest.firstName(),
                registerRequest.email(),
                registerRequest.password()
        );
        return userRepository.save(newUser);
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
