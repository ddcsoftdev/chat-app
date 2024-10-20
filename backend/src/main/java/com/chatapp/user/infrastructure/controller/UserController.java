package com.chatapp.user.infrastructure.controller;

import com.chatapp.user.application.service.UserApplicationService;
import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.vo.UserPublicId;
import com.chatapp.user.infrastructure.dto.SearchUserDto;
import com.chatapp.user.infrastructure.dto.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserApplicationService usersApplicationService;

    public UserController(UserApplicationService usersApplicationService) {
        this.usersApplicationService = usersApplicationService;
    }

    @GetMapping("/get-authenticated-user")
    public ResponseEntity<UserDto> getAuthenticatedUser(@AuthenticationPrincipal Jwt user,
                                                        @RequestParam boolean forceResync) {
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
    ResponseEntity<Instant> getLastSeen(@RequestParam UUID publicId) {
        Optional<Instant> lastSeen = usersApplicationService.getLastSeen(new UserPublicId(publicId));
        if (lastSeen.isPresent()) {
            return ResponseEntity.ok(lastSeen.get());
        } else {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Unable to fetch the presence of the user " + publicId);
            return ResponseEntity.of(problemDetail).build();
        }
    }
}
