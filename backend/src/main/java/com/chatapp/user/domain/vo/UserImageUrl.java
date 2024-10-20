package com.chatapp.user.domain.vo;

import com.chatapp.shared.error.domain.Assert;

public record UserImageUrl(String value) {

    public UserImageUrl {
        Assert.field(value, value).maxLength(255);
    }
}
