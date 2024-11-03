package com.chatapp.conversation.dto;

import java.lang.Long;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ConversationDTOBuilder {
  private Long id;

  public static ConversationDTOBuilder conversationDTO() {
    return new ConversationDTOBuilder();
  }

  public ConversationDTOBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public ConversationDTO build() {
    return new ConversationDTO(id);
  }
}
