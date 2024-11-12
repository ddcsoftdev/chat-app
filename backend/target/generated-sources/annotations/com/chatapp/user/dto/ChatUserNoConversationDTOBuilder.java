package com.chatapp.user.dto;

import com.chatapp.user.enums.EAuthRoles;
import java.lang.Long;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ChatUserNoConversationDTOBuilder {
  private Long id;

  private String firstName;

  private String lastName;

  private String nickname;

  private String email;

  private EAuthRoles role;

  public static ChatUserNoConversationDTOBuilder chatUserNoConversationDTO() {
    return new ChatUserNoConversationDTOBuilder();
  }

  public ChatUserNoConversationDTOBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public ChatUserNoConversationDTOBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ChatUserNoConversationDTOBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ChatUserNoConversationDTOBuilder nickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ChatUserNoConversationDTOBuilder email(String email) {
    this.email = email;
    return this;
  }

  public ChatUserNoConversationDTOBuilder role(EAuthRoles role) {
    this.role = role;
    return this;
  }

  public ChatUserNoConversationDTO build() {
    return new ChatUserNoConversationDTO(id, firstName, lastName, nickname, email, role);
  }
}
