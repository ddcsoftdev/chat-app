package old.message.domain.vo;

import old.conversation.domain.vo.ConversationViewedForNotification;
import old.user.domain.vo.UserPublicId;

import java.util.List;

public record MessageIdWithUsers(ConversationViewedForNotification conversationViewedForNotification,
                                 List<UserPublicId> usersToNotify) {
}
