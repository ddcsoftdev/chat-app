package com.chatapp.user.domain.vo;

import com.chatapp.shared.error.domain.Assert;

public record UserLastName(String value) {

    public UserLastName {
        Assert.field(value, value).maxLength(255);
    }
}
