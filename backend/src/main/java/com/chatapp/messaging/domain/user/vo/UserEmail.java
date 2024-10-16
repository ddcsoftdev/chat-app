package com.chatapp.messaging.domain.user.vo;

import com.chatapp.shared.error.domain.Assert;

public record UserEmail(String value) {

    public UserEmail {
        Assert.field(value, value).maxLength(255);
    }
}
