package com.chatapp.conversation.entity;

import com.chatapp.message.entity.Message;
import com.chatapp.user.entity.ChatUser;
import java.lang.Long;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ConversationBuilder {
  private Long id;

  private Set<ChatUser> users;

  private Set<Message> messages;

  public static ConversationBuilder conversation() {
    return new ConversationBuilder();
  }

  public ConversationBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public ConversationBuilder users(Set<ChatUser> users) {
    this.users = users;
    return this;
  }

  public ConversationBuilder messages(Set<Message> messages) {
    this.messages = messages;
    return this;
  }

  public Conversation build() {
    return new Conversation(id, users, messages);
  }
}
