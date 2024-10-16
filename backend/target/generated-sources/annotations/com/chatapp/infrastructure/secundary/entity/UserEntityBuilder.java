package com.chatapp.infrastructure.secundary.entity;

import java.lang.Long;
import java.lang.String;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class UserEntityBuilder {
  private Long id;

  private String lastName;

  private String firstName;

  private String email;

  private String imageUrl;

  private UUID publicId;

  private Instant lastSeen;

  private Set<AuthorityEntity> authorities;

  private Set<ConversationEntity> conversations;

  public static UserEntityBuilder userEntity() {
    return new UserEntityBuilder();
  }

  public UserEntityBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public UserEntityBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public UserEntityBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public UserEntityBuilder email(String email) {
    this.email = email;
    return this;
  }

  public UserEntityBuilder imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public UserEntityBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public UserEntityBuilder lastSeen(Instant lastSeen) {
    this.lastSeen = lastSeen;
    return this;
  }

  public UserEntityBuilder authorities(Set<AuthorityEntity> authorities) {
    this.authorities = authorities;
    return this;
  }

  public UserEntityBuilder conversations(Set<ConversationEntity> conversations) {
    this.conversations = conversations;
    return this;
  }

  public UserEntity build() {
    return new UserEntity(id, lastName, firstName, email, imageUrl, publicId, lastSeen, authorities, conversations);
  }
}
