package com.chatapp.infrastructure.primary.conversation;

import java.lang.String;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class RestConversationToCreateBuilder {
  private Set<UUID> members;

  private String name;

  public static RestConversationToCreateBuilder restConversationToCreate() {
    return new RestConversationToCreateBuilder();
  }

  public RestConversationToCreateBuilder members(Set<UUID> members) {
    this.members = members;
    return this;
  }

  public RestConversationToCreateBuilder name(String name) {
    this.name = name;
    return this;
  }

  public RestConversationToCreate build() {
    return new RestConversationToCreate(members, name);
  }
}
