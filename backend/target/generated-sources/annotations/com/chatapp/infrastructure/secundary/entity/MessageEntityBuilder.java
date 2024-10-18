package com.chatapp.infrastructure.secundary.entity;

import com.chatapp.conversation.infrastructure.ConversationEntity;
import com.chatapp.messaging.domain.message.vo.MessageSendState;
import com.chatapp.messaging.domain.message.vo.MessageType;
import java.lang.Long;
import java.lang.String;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class MessageEntityBuilder {
  private Long id;

  private UUID publicId;

  private Instant sendTime;

  private String text;

  private MessageType type;

  private MessageSendState sendState;

  private UserEntity sender;

  private ConversationEntity conversation;

  private MessageContentBinaryEntity contentBinary;

  public static MessageEntityBuilder messageEntity() {
    return new MessageEntityBuilder();
  }

  public MessageEntityBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public MessageEntityBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public MessageEntityBuilder sendTime(Instant sendTime) {
    this.sendTime = sendTime;
    return this;
  }

  public MessageEntityBuilder text(String text) {
    this.text = text;
    return this;
  }

  public MessageEntityBuilder type(MessageType type) {
    this.type = type;
    return this;
  }

  public MessageEntityBuilder sendState(MessageSendState sendState) {
    this.sendState = sendState;
    return this;
  }

  public MessageEntityBuilder sender(UserEntity sender) {
    this.sender = sender;
    return this;
  }

  public MessageEntityBuilder conversation(ConversationEntity conversation) {
    this.conversation = conversation;
    return this;
  }

  public MessageEntityBuilder contentBinary(MessageContentBinaryEntity contentBinary) {
    this.contentBinary = contentBinary;
    return this;
  }

  public MessageEntity build() {
    return new MessageEntity(id, publicId, sendTime, text, type, sendState, sender, conversation, contentBinary);
  }
}
