package com.chatapp.user.infrastructure.dto;

import com.chatapp.user.domain.vo.UserEmail;
import com.chatapp.user.domain.vo.UserFirstName;
import com.chatapp.user.domain.vo.UserLastName;
import com.chatapp.user.domain.vo.UserPassword;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class RegisterRequestDtoBuilder {
  private UserEmail email;

  private UserPassword password;

  private UserFirstName firstName;

  private UserLastName lastName;

  public static RegisterRequestDtoBuilder registerRequestDto() {
    return new RegisterRequestDtoBuilder();
  }

  public RegisterRequestDtoBuilder email(UserEmail email) {
    this.email = email;
    return this;
  }

  public RegisterRequestDtoBuilder password(UserPassword password) {
    this.password = password;
    return this;
  }

  public RegisterRequestDtoBuilder firstName(UserFirstName firstName) {
    this.firstName = firstName;
    return this;
  }

  public RegisterRequestDtoBuilder lastName(UserLastName lastName) {
    this.lastName = lastName;
    return this;
  }

  public RegisterRequestDto build() {
    return new RegisterRequestDto(email, password, firstName, lastName);
  }
}
