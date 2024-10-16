package com.chatapp.messaging.domain.user.vo;

import com.chatapp.shared.error.domain.Assert;

public record UserFirstname(String value) {

    public UserFirstname {
        Assert.field(value, value).maxLength(255);
    }
}
