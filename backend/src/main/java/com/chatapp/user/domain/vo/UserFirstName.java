package com.chatapp.user.domain.vo;

import com.chatapp.shared.error.domain.Assert;

public record UserFirstName(String value) {

    public UserFirstName {
        Assert.field(value, value).maxLength(255);
    }
}
