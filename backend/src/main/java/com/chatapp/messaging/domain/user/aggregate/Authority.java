package com.chatapp.messaging.domain.user.aggregate;

import com.chatapp.messaging.domain.user.vo.AuthorityName;
import com.chatapp.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public class Authority {

    private AuthorityName name;

    public Authority(AuthorityName name) {
        Assert.notNull("name", name);
        this.name = name;
    }

    public AuthorityName getName() {
        return name;
    }
}
