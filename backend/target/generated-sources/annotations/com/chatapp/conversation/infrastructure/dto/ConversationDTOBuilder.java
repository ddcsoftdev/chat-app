package com.chatapp.conversation.infrastructure.dto;

import com.chatapp.infrastructure.primary.message.RestMessage;
import java.lang.String;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ConversationDtoBuilder {
  private UUID publicId;

  private String name;

  private List<UserConversationDto> members;

  private List<RestMessage> messages;

  public static ConversationDtoBuilder conversationDto() {
    return new ConversationDtoBuilder();
  }

  public ConversationDtoBuilder publicId(UUID publicId) {
    this.publicId = publicId;
    return this;
  }

  public ConversationDtoBuilder name(String name) {
    this.name = name;
    return this;
  }

  public ConversationDtoBuilder members(List<UserConversationDto> members) {
    this.members = members;
    return this;
  }

  public ConversationDtoBuilder messages(List<RestMessage> messages) {
    this.messages = messages;
    return this;
  }

  public ConversationDto build() {
    return new ConversationDto(publicId, name, members, messages);
  }
}
