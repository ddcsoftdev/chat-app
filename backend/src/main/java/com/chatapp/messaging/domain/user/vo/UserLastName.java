package com.chatapp.messaging.domain.user.vo;

import com.chatapp.shared.error.domain.Assert;

public record UserLastName(String value) {

    public UserLastName {
        Assert.field(value, value).maxLength(255);
    }
}
