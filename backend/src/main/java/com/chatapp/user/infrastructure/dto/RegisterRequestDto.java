package com.chatapp.user.infrastructure.dto;

import com.chatapp.user.domain.vo.UserEmail;
import com.chatapp.user.domain.vo.UserFirstName;
import com.chatapp.user.domain.vo.UserLastName;
import com.chatapp.user.domain.vo.UserPassword;
import org.jilt.Builder;

@Builder
public record RegisterRequestDto(
        UserEmail email,
        UserPassword password,
        UserFirstName firstName,
        UserLastName lastName
) {
    // Optional validation method
    public boolean isValid() {
        return email != null && !email.value().isBlank() &&
                password != null && !password.value().isBlank() &&
                firstName != null && !firstName.value().isBlank() &&
                lastName != null && !lastName.value().isBlank();
    }
}

