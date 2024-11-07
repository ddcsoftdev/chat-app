package com.chatapp.infraestructure.exceptions;

import java.time.LocalDateTime;

public record ExceptionResponseDTO(
        String path,
        String message,
        Integer statusCode,
        LocalDateTime localDateTime
) {
}
