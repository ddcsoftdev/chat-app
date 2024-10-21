package com.chatapp.messaging.domain.message.aggregate;

import com.chatapp.conversation.domain.vo.ConversationName;
import com.chatapp.user.domain.vo.UserPublicId;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class ConversationToCreateBuilder {
  private Set<UserPublicId> members;

  private ConversationName name;

  public static ConversationToCreateBuilder conversationToCreate() {
    return new ConversationToCreateBuilder();
  }

  public ConversationToCreateBuilder members(Set<UserPublicId> members) {
    this.members = members;
    return this;
  }

  public ConversationToCreateBuilder name(ConversationName name) {
    this.name = name;
    return this;
  }

  public ConversationToCreate build() {
    return new ConversationToCreate(members, name);
  }
}
