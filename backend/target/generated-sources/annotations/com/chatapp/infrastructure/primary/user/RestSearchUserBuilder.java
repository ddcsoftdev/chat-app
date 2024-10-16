package com.chatapp.infrastructure.primary.user;

import java.lang.String;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class RestSearchUserBuilder {
  private String lastName;

  private String firstName;

  private String email;

  private UUID publicId;

  private String imageUrl;

  public static RestSearchUserBuilder restSearchUser() {
    return new RestSearchUserBuilder();
  }

  public RestSearchUserBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public RestSearchUserBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public RestSearchUserBuilder email(String email) {
    this.email = email;
    return this;
  }

  public RestSearchUserBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public RestSearchUserBuilder imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public RestSearchUser build() {
    return new RestSearchUser(lastName, firstName, email, publicId, imageUrl);
  }
}
