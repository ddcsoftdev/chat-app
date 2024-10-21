package com.chatapp.conversation.domain.vo;

import com.chatapp.user.domain.vo.UserPublicId;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class CreateConversationBuilder {
  private Set<UserPublicId> members;

  private ConversationName name;

  public static CreateConversationBuilder createConversation() {
    return new CreateConversationBuilder();
  }

  public CreateConversationBuilder members(Set<UserPublicId> members) {
    this.members = members;
    return this;
  }

  public CreateConversationBuilder name(ConversationName name) {
    this.name = name;
    return this;
  }

  public CreateConversation build() {
    return new CreateConversation(members, name);
  }
}
