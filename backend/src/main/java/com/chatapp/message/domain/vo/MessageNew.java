package com.chatapp.message.domain.vo;

import com.chatapp.conversation.domain.vo.ConversationPublicId;
import org.jilt.Builder;

@Builder
public record MessageNew(MessageContent messageContent,
                         ConversationPublicId conversationPublicId) {
}
