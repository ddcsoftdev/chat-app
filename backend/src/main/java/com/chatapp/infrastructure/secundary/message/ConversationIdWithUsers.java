package com.chatapp.infrastructure.secundary.message;

import com.chatapp.messaging.domain.message.vo.ConversationPublicId;
import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;

public record ConversationIdWithUsers(ConversationPublicId conversationPublicId,
                                      List<UserPublicId> users) {
}

