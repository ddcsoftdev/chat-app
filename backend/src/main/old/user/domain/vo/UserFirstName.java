package old.user.domain.vo;

import old.shared.error.domain.Assert;

public record UserFirstName(String value) {

    public UserFirstName {
        Assert.field(value, value).maxLength(255);
    }
}
