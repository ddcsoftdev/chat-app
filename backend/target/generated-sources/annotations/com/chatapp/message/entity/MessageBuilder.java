package com.chatapp.message.entity;

import com.chatapp.conversation.entity.Conversation;
import com.chatapp.user.entity.ChatUser;
import java.lang.Long;
import java.lang.String;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class MessageBuilder {
  private Long id;

  private String content;

  private Conversation conversation;

  private ChatUser user;

  private LocalDateTime postedAt;

  public static MessageBuilder message() {
    return new MessageBuilder();
  }

  public MessageBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public MessageBuilder content(String content) {
    this.content = content;
    return this;
  }

  public MessageBuilder conversation(Conversation conversation) {
    this.conversation = conversation;
    return this;
  }

  public MessageBuilder user(ChatUser user) {
    this.user = user;
    return this;
  }

  public MessageBuilder postedAt(LocalDateTime postedAt) {
    this.postedAt = postedAt;
    return this;
  }

  public Message build() {
    return new Message(id, content, conversation, user, postedAt);
  }
}
