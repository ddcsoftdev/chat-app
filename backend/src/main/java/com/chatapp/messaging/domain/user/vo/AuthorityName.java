package com.chatapp.messaging.domain.user.vo;

import com.chatapp.shared.error.domain.Assert;

public record AuthorityName(String name) {

    public AuthorityName {
        Assert.field("name", name).notNull();
    }
}
