package com.chatapp.message.dto;

import java.time.LocalDateTime;

public record MessageDTO (
        Long id,
        String content,
        Long conversationId,
        Long userId,
        LocalDateTime postedAt
){
}
