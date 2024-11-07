package com.chatapp.auth;

import com.chatapp.jwt.JWTUtil;
import com.chatapp.user.dto.ChatUserDTO;
import com.chatapp.user.dto.ChatUserDTOMapper;
import com.chatapp.user.entity.ChatUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final ChatUserDTOMapper customerDTOMapper;
    private final JWTUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, ChatUserDTOMapper customerDTOMapper, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerDTOMapper = customerDTOMapper;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password())
        );

        ChatUser principal = (ChatUser) authentication.getPrincipal();
        ChatUserDTO chatUserDTO = customerDTOMapper.apply(principal);
        String token = jwtUtil.issueToken(chatUserDTO.email(), chatUserDTO.role().getRoleName());

        return new AuthenticationResponseDTO(chatUserDTO, token);
    }
}
