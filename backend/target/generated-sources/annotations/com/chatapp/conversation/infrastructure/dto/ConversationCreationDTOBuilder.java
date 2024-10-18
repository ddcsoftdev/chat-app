package com.chatapp.conversation.infrastructure.dto;

import java.lang.String;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ConversationCreationDTOBuilder {
  private Set<UUID> members;

  private String name;

  public static ConversationCreationDTOBuilder conversationCreationDTO() {
    return new ConversationCreationDTOBuilder();
  }

  public ConversationCreationDTOBuilder members(Set<UUID> members) {
    this.members = members;
    return this;
  }

  public ConversationCreationDTOBuilder name(String name) {
    this.name = name;
    return this;
  }

  public ConversationCreationDTO build() {
    return new ConversationCreationDTO(members, name);
  }
}
