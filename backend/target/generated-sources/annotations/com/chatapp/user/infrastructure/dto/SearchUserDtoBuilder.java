package com.chatapp.user.infrastructure.dto;

import java.lang.String;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class SearchUserDtoBuilder {
  private String lastName;

  private String firstName;

  private String email;

  private UUID publicId;

  private String imageUrl;

  public static SearchUserDtoBuilder searchUserDto() {
    return new SearchUserDtoBuilder();
  }

  public SearchUserDtoBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public SearchUserDtoBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public SearchUserDtoBuilder email(String email) {
    this.email = email;
    return this;
  }

  public SearchUserDtoBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public SearchUserDtoBuilder imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public SearchUserDto build() {
    return new SearchUserDto(lastName, firstName, email, publicId, imageUrl);
  }
}
