package com.chatapp.auth;

import com.chatapp.shared.jwt.JWTUtil;
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
    private final ChatUserDTOMapper chatUserDTOMapper;
    private final JWTUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, ChatUserDTOMapper chatUserDTOMapper, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.chatUserDTOMapper = chatUserDTOMapper;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password())
        );

        ChatUser principal = (ChatUser) authentication.getPrincipal();
        ChatUserDTO chatUserDTO = chatUserDTOMapper.apply(principal);
        String token = jwtUtil.issueToken(chatUserDTO.email(), chatUserDTO.role().getRoleName());

        return new AuthenticationResponseDTO(chatUserDTO, token);
    }
}
