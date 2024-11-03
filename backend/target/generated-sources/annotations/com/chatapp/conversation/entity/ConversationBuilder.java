package com.chatapp.conversation.entity;

import java.lang.Long;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ConversationBuilder {
  private Long id;

  public static ConversationBuilder conversation() {
    return new ConversationBuilder();
  }

  public ConversationBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public Conversation build() {
    return new Conversation(id);
  }
}
