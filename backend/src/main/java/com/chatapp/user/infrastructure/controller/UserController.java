package com.chatapp.user.infrastructure.controller;

import com.chatapp.user.application.service.UserApplicationService;
import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.vo.UserPublicId;
import com.chatapp.user.infrastructure.dto.LoginRequestDto;
import com.chatapp.user.infrastructure.dto.AuthenticationResponseDto;
import com.chatapp.user.infrastructure.dto.RegisterRequestDto;
import com.chatapp.user.infrastructure.dto.SearchUserDto;
import com.chatapp.user.infrastructure.dto.UserDto;
import com.chatapp.user.infrastructure.security.JwtUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserApplicationService usersApplicationService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(UserApplicationService usersApplicationService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.usersApplicationService = usersApplicationService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        // Generate JWT token if authentication is successful
        String jwtToken = jwtUtil.generateToken(authentication.getName());

        return ResponseEntity.ok(new AuthenticationResponseDto(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequestDto registerRequest) {
        // Create and save a new user
        User newUser =  usersApplicationService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDto.from(newUser));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // For JWT-based auth, logout is handled on the client by deleting the JWT
        // Optionally implement token blacklisting if needed
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/get-authenticated-user")
    public ResponseEntity<UserDto> getAuthenticatedUser(@AuthenticationPrincipal Jwt user, @RequestParam boolean forceResync) {
        User authenticatedUser = usersApplicationService.getAuthenticatedUserWithSync(user, forceResync);
        UserDto userDto = UserDto.from(authenticatedUser);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchUserDto>> search(Pageable pageable, @RequestParam String query) {
        List<SearchUserDto> searchResults = usersApplicationService.search(pageable, query)
                .stream().map(SearchUserDto::from)
                .toList();
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/get-last-seen")
    public ResponseEntity<Instant> getLastSeen(@RequestParam UUID publicId) {
        Optional<Instant> lastSeen = usersApplicationService.getLastSeen(new UserPublicId(publicId));
        if (lastSeen.isPresent()) {
            return ResponseEntity.ok(lastSeen.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Instant.now());
        }
    }
}
