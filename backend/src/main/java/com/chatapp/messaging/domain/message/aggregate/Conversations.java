package com.chatapp.messaging.domain.message.aggregate;

import com.chatapp.conversation.domain.Conversation;
import com.chatapp.shared.error.domain.Assert;

import java.util.List;

public record Conversations(List<Conversation> conversations) {

    public Conversations {
        Assert.field("conversations", conversations).notNull().noNullElement();
    }
}
