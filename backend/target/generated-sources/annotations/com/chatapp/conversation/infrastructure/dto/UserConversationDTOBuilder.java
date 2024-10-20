package com.chatapp.conversation.infrastructure.dto;

import java.lang.String;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class UserConversationDtoBuilder {
  private String lastName;

  private String firstName;

  private UUID publicId;

  private String imageUrl;

  private Instant lastSeen;

  public static UserConversationDtoBuilder userConversationDto() {
    return new UserConversationDtoBuilder();
  }

  public UserConversationDtoBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public UserConversationDtoBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public UserConversationDtoBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public UserConversationDtoBuilder imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public UserConversationDtoBuilder lastSeen(Instant lastSeen) {
    this.lastSeen = lastSeen;
    return this;
  }

  public UserConversationDto build() {
    return new UserConversationDto(lastName, firstName, publicId, imageUrl, lastSeen);
  }
}
