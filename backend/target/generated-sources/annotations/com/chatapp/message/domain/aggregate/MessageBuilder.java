package com.chatapp.message.domain.aggregate;

import com.chatapp.conversation.domain.vo.ConversationPublicId;
import com.chatapp.message.domain.vo.MessageContent;
import com.chatapp.message.domain.vo.MessagePublicId;
import com.chatapp.message.domain.vo.MessageSendState;
import com.chatapp.message.domain.vo.MessageSentTime;
import com.chatapp.user.domain.vo.UserPublicId;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class MessageBuilder {
  private MessageSentTime sentTime;

  private MessageContent content;

  private MessageSendState sendState;

  private MessagePublicId publicId;

  private UserPublicId sender;

  private ConversationPublicId conversationId;

  public static MessageBuilder message() {
    return new MessageBuilder();
  }

  public MessageBuilder sentTime(MessageSentTime sentTime) {
    this.sentTime = sentTime;
    return this;
  }

  public MessageBuilder content(MessageContent content) {
    this.content = content;
    return this;
  }

  public MessageBuilder sendState(MessageSendState sendState) {
    this.sendState = sendState;
    return this;
  }

  public MessageBuilder publicId(MessagePublicId publicId) {
    this.publicId = publicId;
    return this;
  }

  public MessageBuilder sender(UserPublicId sender) {
    this.sender = sender;
    return this;
  }

  public MessageBuilder conversationId(ConversationPublicId conversationId) {
    this.conversationId = conversationId;
    return this;
  }

  public Message build() {
    return new Message(sentTime, content, sendState, publicId, sender, conversationId);
  }
}
