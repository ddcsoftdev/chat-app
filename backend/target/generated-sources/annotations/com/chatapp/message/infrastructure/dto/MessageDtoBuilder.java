package com.chatapp.message.infrastructure.dto;

import com.chatapp.message.domain.vo.MessageSendState;
import com.chatapp.message.domain.vo.MessageType;
import java.lang.String;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class MessageDtoBuilder {
  private String textContent;

  private Instant sendDate;

  private MessageSendState state;

  private UUID publicId;

  private UUID conversationId;

  private MessageType type;

  private byte[] mediaContent;

  private String mimeType;

  private UUID senderId;

  public static MessageDtoBuilder messageDto() {
    return new MessageDtoBuilder();
  }

  public MessageDtoBuilder textContent(String textContent) {
    this.textContent = textContent;
    return this;
  }

  public MessageDtoBuilder sendDate(Instant sendDate) {
    this.sendDate = sendDate;
    return this;
  }

  public MessageDtoBuilder state(MessageSendState state) {
    this.state = state;
    return this;
  }

  public MessageDtoBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public MessageDtoBuilder conversationId(UUID conversationId) {
    this.conversationId = conversationId;
    return this;
  }

  public MessageDtoBuilder type(MessageType type) {
    this.type = type;
    return this;
  }

  public MessageDtoBuilder mediaContent(byte[] mediaContent) {
    this.mediaContent = mediaContent;
    return this;
  }

  public MessageDtoBuilder mimeType(String mimeType) {
    this.mimeType = mimeType;
    return this;
  }

  public MessageDtoBuilder senderId(UUID senderId) {
    this.senderId = senderId;
    return this;
  }

  public MessageDto build() {
    return new MessageDto(textContent, sendDate, state, publicId, conversationId, type, mediaContent, mimeType, senderId);
  }
}
