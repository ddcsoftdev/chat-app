package com.chatapp.user.infrastructure.dto;

import java.lang.String;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class UserDtoBuilder {
  private UUID publicId;

  private String firstName;

  private String lastName;

  private String email;

  private String imageUrl;

  private Set<AuthorityDto> authorities;

  public static UserDtoBuilder userDto() {
    return new UserDtoBuilder();
  }

  public UserDtoBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public UserDtoBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public UserDtoBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public UserDtoBuilder email(String email) {
    this.email = email;
    return this;
  }

  public UserDtoBuilder imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public UserDtoBuilder authorities(Set<AuthorityDto> authorities) {
    this.authorities = authorities;
    return this;
  }

  public UserDto build() {
    return new UserDto(publicId, firstName, lastName, email, imageUrl, authorities);
  }
}
