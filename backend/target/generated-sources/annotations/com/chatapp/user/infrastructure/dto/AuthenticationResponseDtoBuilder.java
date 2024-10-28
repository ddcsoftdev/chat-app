package com.chatapp.user.infrastructure.dto;

import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class AuthenticationResponseDtoBuilder {
  private String jwtToken;

  public static AuthenticationResponseDtoBuilder authenticationResponseDto() {
    return new AuthenticationResponseDtoBuilder();
  }

  public AuthenticationResponseDtoBuilder jwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
    return this;
  }

  public AuthenticationResponseDto build() {
    return new AuthenticationResponseDto(jwtToken);
  }
}
