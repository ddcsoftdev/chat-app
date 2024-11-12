package com.chatapp.conversation.dto;

import com.chatapp.message.dto.MessageDTO;
import com.chatapp.user.dto.ChatUserNoConversationDTO;
import java.lang.Long;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ConversationDTOBuilder {
  private Long id;

  private Set<ChatUserNoConversationDTO> users;

  private Set<MessageDTO> messages;

  public static ConversationDTOBuilder conversationDTO() {
    return new ConversationDTOBuilder();
  }

  public ConversationDTOBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public ConversationDTOBuilder users(Set<ChatUserNoConversationDTO> users) {
    this.users = users;
    return this;
  }

  public ConversationDTOBuilder messages(Set<MessageDTO> messages) {
    this.messages = messages;
    return this;
  }

  public ConversationDTO build() {
    return new ConversationDTO(id, users, messages);
  }
}
