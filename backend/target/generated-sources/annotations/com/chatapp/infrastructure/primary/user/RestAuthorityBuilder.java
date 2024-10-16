package com.chatapp.infrastructure.primary.user;

import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class RestAuthorityBuilder {
  private String name;

  public static RestAuthorityBuilder restAuthority() {
    return new RestAuthorityBuilder();
  }

  public RestAuthorityBuilder name(String name) {
    this.name = name;
    return this;
  }

  public RestAuthority build() {
    return new RestAuthority(name);
  }
}
