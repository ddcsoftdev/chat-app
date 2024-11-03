package old.user.domain.vo;

import old.shared.error.domain.Assert;

public record UserPassword (String value) {

    public UserPassword {
        Assert.field(value, value).maxLength(255);
    }
}
