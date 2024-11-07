package com.chatapp.user.dto;

import com.chatapp.conversation.dto.ConversationDTO;
import com.chatapp.user.enums.EAuthRoles;
import java.lang.Long;
import java.lang.String;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ChatUserDTOBuilder {
  private Long id;

  private String firstName;

  private String lastName;

  private String nickname;

  private String email;

  private EAuthRoles role;

  private Set<ConversationDTO> conversations;

  public static ChatUserDTOBuilder chatUserDTO() {
    return new ChatUserDTOBuilder();
  }

  public ChatUserDTOBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public ChatUserDTOBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ChatUserDTOBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ChatUserDTOBuilder nickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ChatUserDTOBuilder email(String email) {
    this.email = email;
    return this;
  }

  public ChatUserDTOBuilder role(EAuthRoles role) {
    this.role = role;
    return this;
  }

  public ChatUserDTOBuilder conversations(Set<ConversationDTO> conversations) {
    this.conversations = conversations;
    return this;
  }

  public ChatUserDTO build() {
    return new ChatUserDTO(id, firstName, lastName, nickname, email, role, conversations);
  }
}
