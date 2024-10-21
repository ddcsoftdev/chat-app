package com.chatapp.conversation.domain.vo;

import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;

public record ConversationIdWithUsers(ConversationPublicId conversationPublicId,
                                      List<UserPublicId> users) {
}

