package com.chatapp.infrastructure.primary.user;

import java.lang.String;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class RestUserBuilder {
  private UUID publicId;

  private String firstName;

  private String lastName;

  private String email;

  private String imageUrl;

  private Set<RestAuthority> authorities;

  public static RestUserBuilder restUser() {
    return new RestUserBuilder();
  }

  public RestUserBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public RestUserBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public RestUserBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public RestUserBuilder email(String email) {
    this.email = email;
    return this;
  }

  public RestUserBuilder imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public RestUserBuilder authorities(Set<RestAuthority> authorities) {
    this.authorities = authorities;
    return this;
  }

  public RestUser build() {
    return new RestUser(publicId, firstName, lastName, email, imageUrl, authorities);
  }
}
