package com.chatapp.user.application.service;

import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.vo.UserEmail;
import com.chatapp.user.infrastructure.dto.AuthenticationResponseDto;
import com.chatapp.user.infrastructure.dto.LoginRequestDto;
import com.chatapp.user.infrastructure.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAuthenticationService implements UserDetailsService {

    private final UserApplicationService userApplicationService;

    public UserAuthenticationService(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userApplicationService.getUserByEmail(new UserEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        Set<SimpleGrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail().value(),
                user.getPassword().value(),
                grantedAuthorities
        );
    }
}
