package com.chatapp.infrastructure.secundary.entity;

import java.lang.Long;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class MessageContentBinaryEntityBuilder {
  private Long id;

  private byte[] file;

  private String fileContentType;

  private MessageEntity message;

  public static MessageContentBinaryEntityBuilder messageContentBinaryEntity() {
    return new MessageContentBinaryEntityBuilder();
  }

  public MessageContentBinaryEntityBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public MessageContentBinaryEntityBuilder file(byte[] file) {
    this.file = file;
    return this;
  }

  public MessageContentBinaryEntityBuilder fileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
    return this;
  }

  public MessageContentBinaryEntityBuilder message(MessageEntity message) {
    this.message = message;
    return this;
  }

  public MessageContentBinaryEntity build() {
    return new MessageContentBinaryEntity(id, file, fileContentType, message);
  }
}
