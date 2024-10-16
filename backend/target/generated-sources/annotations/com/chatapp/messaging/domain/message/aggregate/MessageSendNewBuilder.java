package com.chatapp.messaging.domain.message.aggregate;

import com.chatapp.messaging.domain.message.vo.ConversationPublicId;
import com.chatapp.messaging.domain.message.vo.MessageContent;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class MessageSendNewBuilder {
  private MessageContent messageContent;

  private ConversationPublicId conversationPublicId;

  public static MessageSendNewBuilder messageSendNew() {
    return new MessageSendNewBuilder();
  }

  public MessageSendNewBuilder messageContent(MessageContent messageContent) {
    this.messageContent = messageContent;
    return this;
  }

  public MessageSendNewBuilder conversationPublicId(ConversationPublicId conversationPublicId) {
    this.conversationPublicId = conversationPublicId;
    return this;
  }

  public MessageSendNew build() {
    return new MessageSendNew(messageContent, conversationPublicId);
  }
}
