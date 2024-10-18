package com.chatapp.conversation.infrastructure.dto;

import java.lang.String;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class UserConversationDTOBuilder {
  private String lastName;

  private String firstName;

  private UUID publicId;

  private String imageUrl;

  private Instant lastSeen;

  public static UserConversationDTOBuilder userConversationDTO() {
    return new UserConversationDTOBuilder();
  }

  public UserConversationDTOBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public UserConversationDTOBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public UserConversationDTOBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public UserConversationDTOBuilder imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public UserConversationDTOBuilder lastSeen(Instant lastSeen) {
    this.lastSeen = lastSeen;
    return this;
  }

  public UserConversationDTO build() {
    return new UserConversationDTO(lastName, firstName, publicId, imageUrl, lastSeen);
  }
}
