package com.chatapp.user.infrastructure.dto;

import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class AuthorityDtoBuilder {
  private String name;

  public static AuthorityDtoBuilder authorityDto() {
    return new AuthorityDtoBuilder();
  }

  public AuthorityDtoBuilder name(String name) {
    this.name = name;
    return this;
  }

  public AuthorityDto build() {
    return new AuthorityDto(name);
  }
}
