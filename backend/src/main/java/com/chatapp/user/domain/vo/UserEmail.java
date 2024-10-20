package com.chatapp.user.domain.vo;

import com.chatapp.shared.error.domain.Assert;

public record UserEmail(String value) {

    public UserEmail {
        Assert.field(value, value).maxLength(255);
    }
}
