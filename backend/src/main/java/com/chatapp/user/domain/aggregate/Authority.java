package com.chatapp.user.domain.aggregate;

import com.chatapp.shared.error.domain.Assert;
import com.chatapp.user.domain.vo.AuthorityName;
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
