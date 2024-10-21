package com.chatapp.message.domain.vo;

import com.chatapp.conversation.domain.vo.ConversationPublicId;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class MessageNewBuilder {
  private MessageContent messageContent;

  private ConversationPublicId conversationPublicId;

  public static MessageNewBuilder messageNew() {
    return new MessageNewBuilder();
  }

  public MessageNewBuilder messageContent(MessageContent messageContent) {
    this.messageContent = messageContent;
    return this;
  }

  public MessageNewBuilder conversationPublicId(ConversationPublicId conversationPublicId) {
    this.conversationPublicId = conversationPublicId;
    return this;
  }

  public MessageNew build() {
    return new MessageNew(messageContent, conversationPublicId);
  }
}
