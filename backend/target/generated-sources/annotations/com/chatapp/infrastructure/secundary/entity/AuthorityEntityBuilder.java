package com.chatapp.infrastructure.secundary.entity;

import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class AuthorityEntityBuilder {
  private String name;

  public static AuthorityEntityBuilder authorityEntity() {
    return new AuthorityEntityBuilder();
  }

  public AuthorityEntityBuilder name(String name) {
    this.name = name;
    return this;
  }

  public AuthorityEntity build() {
    return new AuthorityEntity(name);
  }
}
