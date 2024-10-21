package com.chatapp.conversation.infrastructure.entity;

import com.chatapp.infrastructure.secundary.entity.MessageEntity;
import com.chatapp.user.infrastructure.entity.UserEntity;
import java.lang.Long;
import java.lang.String;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ConversationEntityBuilder {
  private Long id;

  private UUID publicId;

  private String name;

  private Set<MessageEntity> messages;

  private Set<UserEntity> users;

  public static ConversationEntityBuilder conversationEntity() {
    return new ConversationEntityBuilder();
  }

  public ConversationEntityBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public ConversationEntityBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public ConversationEntityBuilder name(String name) {
    this.name = name;
    return this;
  }

  public ConversationEntityBuilder messages(Set<MessageEntity> messages) {
    this.messages = messages;
    return this;
  }

  public ConversationEntityBuilder users(Set<UserEntity> users) {
    this.users = users;
    return this;
  }

  public ConversationEntity build() {
    return new ConversationEntity(id, publicId, name, messages, users);
  }
}
