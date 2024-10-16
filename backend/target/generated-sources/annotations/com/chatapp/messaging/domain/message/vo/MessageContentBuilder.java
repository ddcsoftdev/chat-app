package com.chatapp.messaging.domain.message.vo;

import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class MessageContentBuilder {
  private String text;

  private MessageType type;

  private MessageMediaContent media;

  public static MessageContentBuilder messageContent() {
    return new MessageContentBuilder();
  }

  public MessageContentBuilder text(String text) {
    this.text = text;
    return this;
  }

  public MessageContentBuilder type(MessageType type) {
    this.type = type;
    return this;
  }

  public MessageContentBuilder media(MessageMediaContent media) {
    this.media = media;
    return this;
  }

  public MessageContent build() {
    return new MessageContent(text, type, media);
  }
}
