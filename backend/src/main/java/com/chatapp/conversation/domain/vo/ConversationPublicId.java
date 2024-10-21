package com.chatapp.conversation.domain.vo;

import org.springframework.util.Assert;

import java.util.UUID;

public record ConversationPublicId(UUID value) {

    public ConversationPublicId {
        Assert.notNull(value, "conversation cannot be null");
    }
}
