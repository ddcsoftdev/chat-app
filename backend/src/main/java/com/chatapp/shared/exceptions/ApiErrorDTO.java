package com.chatapp.shared.exceptions;

import java.time.LocalDateTime;

public record ApiErrorDTO(
        String path,
        String message,
        Integer statusCode,
        LocalDateTime localDateTime
) {
}
