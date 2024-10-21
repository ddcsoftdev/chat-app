package com.chatapp.infrastructure.secundary.message;

import com.chatapp.message.domain.aggregate.Message;
import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;

public record MessageWithUsers(Message message, List<UserPublicId> userToNotify) {
}
