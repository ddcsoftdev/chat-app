package com.chatapp.message.dto;

public record MessageDTO (
        Long id,
        String content,
        Long conversationId
){
}
