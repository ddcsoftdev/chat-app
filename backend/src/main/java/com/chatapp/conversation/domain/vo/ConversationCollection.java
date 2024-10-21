package com.chatapp.conversation.domain.vo;

import com.chatapp.conversation.domain.aggregate.Conversation;
import com.chatapp.shared.error.domain.Assert;

import java.util.List;

public record ConversationCollection(List<Conversation> conversations) {

    public ConversationCollection {
        Assert.field("conversations", conversations).notNull().noNullElement();
    }
}
