package com.chatapp.message.domain.vo;

import com.chatapp.shared.error.domain.Assert;

import java.util.List;

public record MessageCollection(List<MessageCollection> messages) {
    public MessageCollection {
        Assert.field("messages", messages).notNull().noNullElement();
    }
}
