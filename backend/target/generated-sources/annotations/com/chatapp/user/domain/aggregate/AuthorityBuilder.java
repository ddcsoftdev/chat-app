package com.chatapp.user.domain.aggregate;

import com.chatapp.user.domain.vo.AuthorityName;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class AuthorityBuilder {
  private AuthorityName name;

  public static AuthorityBuilder authority() {
    return new AuthorityBuilder();
  }

  public AuthorityBuilder name(AuthorityName name) {
    this.name = name;
    return this;
  }

  public Authority build() {
    return new Authority(name);
  }
}
