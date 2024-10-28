package com.chatapp.user.infrastructure.dto;

import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class LoginRequestDtoBuilder {
  private String username;

  private String password;

  public static LoginRequestDtoBuilder loginRequestDto() {
    return new LoginRequestDtoBuilder();
  }

  public LoginRequestDtoBuilder username(String username) {
    this.username = username;
    return this;
  }

  public LoginRequestDtoBuilder password(String password) {
    this.password = password;
    return this;
  }

  public LoginRequestDto build() {
    return new LoginRequestDto(username, password);
  }
}
