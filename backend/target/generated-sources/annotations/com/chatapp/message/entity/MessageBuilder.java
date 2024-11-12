package com.chatapp.message.entity;

import com.chatapp.conversation.entity.Conversation;
import java.lang.Long;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class MessageBuilder {
  private Long id;

  private String content;

  private Conversation conversation;

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

  public Message build() {
    return new Message(id, content, conversation);
  }
}
