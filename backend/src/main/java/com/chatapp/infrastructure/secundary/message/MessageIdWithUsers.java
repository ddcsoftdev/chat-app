package com.chatapp.infrastructure.secundary.message;

import com.chatapp.conversation.domain.vo.ConversationViewedForNotification;
import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;

public record MessageIdWithUsers(ConversationViewedForNotification conversationViewedForNotification,
                                 List<UserPublicId> usersToNotify) {
}
