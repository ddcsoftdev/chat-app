package com.chatapp.user.domain.vo;

import com.chatapp.shared.error.domain.Assert;

public record UserPassword (String value) {

    public UserPassword {
        Assert.field(value, value).maxLength(255);
    }
}
