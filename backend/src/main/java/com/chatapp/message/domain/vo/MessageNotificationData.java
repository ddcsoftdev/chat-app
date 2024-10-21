package com.chatapp.message.domain.vo;

import com.chatapp.message.domain.aggregate.Message;
import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;

public record MessageNotificationData(Message message, List<UserPublicId> userToNotify) {
}
