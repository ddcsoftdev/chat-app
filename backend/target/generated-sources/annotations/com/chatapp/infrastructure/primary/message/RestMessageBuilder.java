package com.chatapp.infrastructure.primary.message;

import com.chatapp.messaging.domain.message.vo.MessageSendState;
import com.chatapp.messaging.domain.message.vo.MessageType;
import java.lang.String;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class RestMessageBuilder {
  private String textContent;

  private Instant sendDate;

  private MessageSendState state;

  private UUID publicId;

  private UUID conversationId;

  private MessageType type;

  private byte[] mediaContent;

  private String mimeType;

  private UUID senderId;

  public static RestMessageBuilder restMessage() {
    return new RestMessageBuilder();
  }

  public RestMessageBuilder textContent(String textContent) {
    this.textContent = textContent;
    return this;
  }

  public RestMessageBuilder sendDate(Instant sendDate) {
    this.sendDate = sendDate;
    return this;
  }

  public RestMessageBuilder state(MessageSendState state) {
    this.state = state;
    return this;
  }

  public RestMessageBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public RestMessageBuilder conversationId(UUID conversationId) {
    this.conversationId = conversationId;
    return this;
  }

  public RestMessageBuilder type(MessageType type) {
    this.type = type;
    return this;
  }

  public RestMessageBuilder mediaContent(byte[] mediaContent) {
    this.mediaContent = mediaContent;
    return this;
  }

  public RestMessageBuilder mimeType(String mimeType) {
    this.mimeType = mimeType;
    return this;
  }

  public RestMessageBuilder senderId(UUID senderId) {
    this.senderId = senderId;
    return this;
  }

  public RestMessage build() {
    return new RestMessage(textContent, sendDate, state, publicId, conversationId, type, mediaContent, mimeType, senderId);
  }
}
