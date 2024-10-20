package com.chatapp.conversation.infrastructure.dto;

import java.lang.String;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ConversationCreationDtoBuilder {
  private Set<UUID> members;

  private String name;

  public static ConversationCreationDtoBuilder conversationCreationDto() {
    return new ConversationCreationDtoBuilder();
  }

  public ConversationCreationDtoBuilder members(Set<UUID> members) {
    this.members = members;
    return this;
  }

  public ConversationCreationDtoBuilder name(String name) {
    this.name = name;
    return this;
  }

  public ConversationCreationDto build() {
    return new ConversationCreationDto(members, name);
  }
}
