package com.chatapp.infrastructure.secundary.message;

import com.chatapp.messaging.domain.message.aggregate.Message;
import com.chatapp.messaging.domain.user.vo.UserPublicId;

import java.util.List;

public record MessageWithUsers(Message message, List<UserPublicId> userToNotify) {
}
