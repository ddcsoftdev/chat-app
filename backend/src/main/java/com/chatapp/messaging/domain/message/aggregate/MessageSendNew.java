package com.chatapp.messaging.domain.message.aggregate;

import com.chatapp.conversation.domain.vo.ConversationPublicId;
import com.chatapp.message.domain.vo.MessageContent;
import org.jilt.Builder;

@Builder
public record MessageSendNew(MessageContent messageContent,
                             ConversationPublicId conversationPublicId) {
}
