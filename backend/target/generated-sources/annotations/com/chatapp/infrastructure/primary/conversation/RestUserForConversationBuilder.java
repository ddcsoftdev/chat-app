package com.chatapp.infrastructure.primary.conversation;

import java.lang.String;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class RestUserForConversationBuilder {
  private String lastName;

  private String firstName;

  private UUID publicId;

  private String imageUrl;

  private Instant lastSeen;

  public static RestUserForConversationBuilder restUserForConversation() {
    return new RestUserForConversationBuilder();
  }

  public RestUserForConversationBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public RestUserForConversationBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public RestUserForConversationBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public RestUserForConversationBuilder imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public RestUserForConversationBuilder lastSeen(Instant lastSeen) {
    this.lastSeen = lastSeen;
    return this;
  }

  public UserConversationDTO build() {
    return new UserConversationDTO(lastName, firstName, publicId, imageUrl, lastSeen);
  }
}
