package com.chatapp.messaging.domain.user.vo;

import com.chatapp.shared.error.domain.Assert;

public record UserImageUrl(String value) {

    public UserImageUrl {
        Assert.field(value, value).maxLength(255);
    }
}
